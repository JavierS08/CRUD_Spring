package com.example.hi_javier.servicios;

import com.example.hi_javier.jpa.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface TareaRepositorio extends JpaRepository<Tarea, String> {
    @Modifying
    @Query("delete from Tarea t where t.usuario.nif =:nif")
    void deleteByNif(@Param("nif") String nif);
    @Query("select t from Tarea t where t.usuario.nif =:nif")
    List<Tarea> findByNif(@Param("nif") String nif);

    @Query("select count(t) as cuenta from Tarea t where t.estado=1")
    public Integer findByEstado();

    @Query("select count(t) as cuenta from Tarea t where t.estado=2")
    public Integer findByEstado2();

    @Query("select count(t) as cuenta from Tarea t where t.estado=3")
    public Integer findByEstado3();

    @Query("select count(t) as cuenta from Tarea t where t.estado=4")
    public Integer findByEstado4();

}
