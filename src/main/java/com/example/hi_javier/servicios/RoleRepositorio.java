package com.example.hi_javier.servicios;

import com.example.hi_javier.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositorio extends JpaRepository<Role, String> {
}
