package com.siaw.projectmanagementsystem.repository;

import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    //no need to sql query here because we have owner field as simple String in our project model
    //List<Project> findByOwner(AppUser user); //commented in 3h02min

    List<Project> findByNameContainingAndTeamContains(String partielName, AppUser user);

    //here we create a native sql request because we have field team as reference to an other model
    // which is AppUser in the project model not like owner field
//    @Query("SELECT p FROM Project p JOIN p.team t WHERE t=:user") //2h24min //commented in 3h02min
//    List<Project> findProjectByTeam(@Param("user") AppUser user);

    //I am an owner to my own project but someone invited me to another project so I will be user also 2h28min
    List<Project> findByTeamContainingOrOwner(AppUser user, AppUser Owner);

}
