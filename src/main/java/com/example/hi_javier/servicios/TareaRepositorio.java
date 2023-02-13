package com.example.hi_javier.servicios;

import com.example.hi_javier.jpa.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepositorio extends JpaRepository<Tarea, String> {
}
