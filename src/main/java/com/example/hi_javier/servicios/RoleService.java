package com.example.hi_javier.servicios;
import com.example.hi_javier.jpa.Role;
import com.example.hi_javier.jpa.Tarea;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
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
    public void deleteByNif(String nif) {
        roles.deleteByNif(nif);
    }
    public List<Role> findByNif(String nif) {
        return roles.findByNif(nif);
    }
}
