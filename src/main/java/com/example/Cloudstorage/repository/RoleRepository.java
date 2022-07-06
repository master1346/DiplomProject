package com.example.Cloudstorage.repository;

import com.example.Cloudstorage.userdata.ERole;
import com.example.Cloudstorage.userdata.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
