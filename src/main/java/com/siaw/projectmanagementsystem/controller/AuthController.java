package com.siaw.projectmanagementsystem.controller;

import com.siaw.projectmanagementsystem.config.JwtProvider;
import com.siaw.projectmanagementsystem.controller.api.AuthApi;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.repository.AppUserRepository;
import com.siaw.projectmanagementsystem.request.LoginRequest;
import com.siaw.projectmanagementsystem.response.AuthResponse;
import com.siaw.projectmanagementsystem.service.SubscriptionService;
import com.siaw.projectmanagementsystem.service.impl.CustomUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
//change in branch-a
@RestController
public class AuthController implements AuthApi {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public ResponseEntity<AuthResponse> createUserHandler(AppUser user) {
        try {
            AppUser fetchedUser = appUserRepository.findByEmail(user.getEmail());

            if (fetchedUser != null) {
                return new ResponseEntity<>(new AuthResponse(null, "Email already exists with another account!"), HttpStatus.BAD_REQUEST);
            }

            AppUser createdUser = new AppUser();
            createdUser.setEmail(user.getEmail());
            createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
            createdUser.setFullName(user.getFullName());

            // Save the user
            AppUser savedUser = appUserRepository.save(createdUser);

            // Create the subscription only if the user was saved successfully
            subscriptionService.createSubscription(savedUser);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = JwtProvider.generateToken(authentication);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("SignUp success");
            authResponse.setJwt(jwt);

            return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            // Handle the unique constraint violation
            return new ResponseEntity<>(new AuthResponse(null, "Email already exists!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>(new AuthResponse(null, "An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<AuthResponse> signin(LoginRequest loginRequest) {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            Authentication authentication = authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = JwtProvider.generateToken(authentication);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("SignIn success");
            authResponse.setJwt(jwt);

            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthResponse(null, "Invalid credentials"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponse(null, "An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username!");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
