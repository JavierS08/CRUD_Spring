package com.example.hi_javier.servicios;


import com.example.hi_javier.jpa.Role;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class RoleService {
    private RoleRepositorio roles;

    public RoleService(RoleRepositorio roles) {
        this.roles = roles;
    }

    public void guardarRol(Role rol) {
        roles.save(rol);
    }

    public void borrarRol(Role rol) {
        roles.delete(rol);
    }
}
