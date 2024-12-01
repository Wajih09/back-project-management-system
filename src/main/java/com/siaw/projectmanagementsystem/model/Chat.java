package com.siaw.projectmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //cascade = CascadeType.ALL when changing message it reflect here aswell 2h07min
    //orphanRemoval = true   whenever we delete a message it will be deleted from here aswell 2h08min
    @JsonIgnore
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    @OneToOne
    private Project project;

    @ManyToMany
    private List<AppUser> users;
}
