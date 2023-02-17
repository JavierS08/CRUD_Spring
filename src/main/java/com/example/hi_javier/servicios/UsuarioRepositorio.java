package com.example.hi_javier.servicios;
import com.example.hi_javier.jpa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    @Query("select count(u) as cuenta from Usuario u where u.activo=1")
    public Integer findByEstado();
}
