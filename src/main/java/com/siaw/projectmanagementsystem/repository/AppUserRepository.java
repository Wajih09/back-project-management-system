package com.siaw.projectmanagementsystem.repository;

import com.siaw.projectmanagementsystem.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}
