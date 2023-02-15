package com.example.hi_javier.servicios;

import com.example.hi_javier.jpa.Role;
import com.example.hi_javier.jpa.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepositorio extends JpaRepository<Role, String> {
    @Modifying
    @Query("delete from Role r where r.usuario.nif =:nif")
    void deleteByNif(@Param("nif") String nif);
    @Query("select r from Role r where r.usuario.nif =:nif")
    List<Role> findByNif(@Param("nif") String nif);
}
