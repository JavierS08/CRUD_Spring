package com.example.hi_javier.servicios;

import com.example.hi_javier.jpa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

}
