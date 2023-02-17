package com.example.hi_javier.servicios;
import com.example.hi_javier.jpa.Tarea;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;
@Service
@ApplicationScope
public class TareaService {
    private TareaRepositorio tareas;
    public TareaService(TareaRepositorio tareas) {
        this.tareas = tareas;
    }
    public void guardarTarea(Tarea tarea) {
        tareas.save(tarea);
    }
    public List<Tarea> listaTareas() {
        return tareas.findAll();
    }
    public void borrar(Tarea t){
        tareas.delete(t);
    }
    public Optional<Tarea> buscarTarea(String usuario) {
        return tareas.findById(usuario);
    }
   public void deleteByNif(String nif) {
        tareas.deleteByNif(nif);
    }
    public List<Tarea> findByNif(String nif) {
        return tareas.findByNif(nif);
    }
    public Integer cuentaTareas() {
        return tareas.findByEstado();
    }
    public Integer cuentaTareasProc() {
        return tareas.findByEstado2();
    }
    public Integer cuentaTareasTer() {
        return tareas.findByEstado3();
    }
    public Integer cuentaTareasElimina() {
        return tareas.findByEstado4();
    }
}
