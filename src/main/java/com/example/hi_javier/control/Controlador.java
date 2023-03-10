package com.example.hi_javier.control;

import com.example.hi_javier.jpa.Role;
import com.example.hi_javier.jpa.Tarea;
import com.example.hi_javier.jpa.Usuario;
import com.example.hi_javier.servicios.RoleService;
import com.example.hi_javier.servicios.TareaService;
import com.example.hi_javier.servicios.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
@Controller
public class Controlador {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UsuarioService usuarios;
    @Autowired
    RoleService roles;
    @Autowired
    TareaService tareas;
     @RequestMapping("/")
        public ModelAndView peticionRaiz(Authentication aut){
         ModelAndView mv = new ModelAndView();
         Usuario username = null;
         if(aut==null)
             mv.addObject("user", "No se ha iniciado sesión");
         else {
             mv.addObject("user", aut.getName());
             Optional<Usuario> userOptional = usuarios.buscarUsuario(aut.getName());

             if (userOptional.isPresent()) {
                 username = userOptional.get();
             }
         }
         mv.addObject("usuario", username);
         mv.setViewName("index");
         return mv;
     }
    @RequestMapping("login")
    public ModelAndView peticionSesion(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("login");
        return mv;
    }
    @RequestMapping("/denegado")
    public ModelAndView peticionDenegado(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("denegado");
        return mv;
    }
    @RequestMapping("/user/perfil")
    public ModelAndView peticionPerfil(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        Usuario user=null;
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        Optional<Usuario> userOptional= usuarios.buscarUsuario(aut.getName());
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        mv.addObject("usuario", user);
        mv.setViewName("perfil");
        return mv;
    }
    @RequestMapping("/user/tareas/nueva")
    public ModelAndView peticioNuevaTarea(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        Tarea tarea = new Tarea();
        mv.addObject("tarea", tarea);
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());

        mv.setViewName("nuevatarea");
        return mv;
    }
    @RequestMapping("/user/tareas/editar")
    public ModelAndView peticionEditarTarea(Authentication aut,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Tarea> tareaOptional= tareas.buscarTarea(String.valueOf(id));
        Tarea tarea= tareaOptional.get();
        mv.addObject("tarea", tarea);
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());

        mv.setViewName("editartareas");
        return mv;
    }
    @RequestMapping("/editartarea")
    public String peticionEditarT(Authentication aut, Tarea tarea) {
        tarea.setUsuario(usuarios.buscarUsuario(aut.getName()).get());
         tareas.guardarTarea(tarea);
         return "redirect:/user/tareas/listado";
    }
    @RequestMapping("/elminartarea")
    public String peticionEliminarT(Authentication aut, Tarea tarea,HttpServletRequest request) {
        String id = request.getParameter("id");
        tarea.setId(Integer.parseInt(id));
        tareas.borrar(tarea);

        return "redirect:/";
    }
    @RequestMapping("/elminarusuario")
    public ModelAndView peticionEliminarU(Authentication aut, Usuario user, HttpServletRequest request) {
        String nif = request.getParameter("nif");
        Optional<Usuario> userOptional= usuarios.buscarUsuario(nif);;
        List<Tarea> t = tareas.findByNif(nif);
        List <Role> r = roles.findByNif(nif);
        ModelAndView mv = new ModelAndView();
        Usuario user1= usuarios.buscarUsuario(aut.getName()).get();
        if (userOptional.isPresent() && !user1.getNif().equals("11111A")) {
            t.forEach(tarea -> tareas.borrar(tarea));
            r.forEach(rol -> roles.borrarRol(rol));
            usuarios.borrar(user);
            mv.addObject("sms", "Usuario eliminado correctamente");
        }else{
            mv.addObject("sms", "No se ha podido eliminar el usuario");
        }
        mv.setViewName("informa");
        return mv;
    }
    @RequestMapping("/user/tareas/listado")
    public ModelAndView peticioListdoTareas(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        Usuario username = null;
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else {
            mv.addObject("user", aut.getName());
            Optional<Usuario> userOptional = usuarios.buscarUsuario(aut.getName());

            if (userOptional.isPresent()) {
                username = userOptional.get();

            }
        }
        mv.addObject("usuario", username);
        mv.setViewName("listadotareas");
        return mv;
    }
    @RequestMapping("/admin/usuario/mostrar")
    public ModelAndView peticioUsuariosMostrar(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        List<Usuario> listaUsuario=usuarios.listaUsuarios();
        for (Usuario user: listaUsuario) {
            System.out.println(user.getNif()+" "+user.getNombre());
        }
        mv.addObject("listaUsuarios", listaUsuario);
        mv.setViewName("mostrarusuarios");
        return mv;
    }
    @RequestMapping("/admin/usuario/editar")
    public ModelAndView peticioUsuariosEditar(Authentication aut, HttpServletRequest request) {
        String nif = request.getParameter("nif");
        Optional<Usuario> userOptional= usuarios.buscarUsuario(nif);
        Usuario user=null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());

        mv.addObject("usuario", user);
        mv.setViewName("editarusuarios");
        return mv;
    }
    @RequestMapping("/admin/tareas/editar")
    public ModelAndView peticioTareasEditar(Authentication aut, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("nif"));
        System.out.println(id);
        Optional<Tarea> tareaOptional= tareas.buscarTarea(String.valueOf(id));
        Tarea tarea=tareaOptional.get();
        System.out.println(tareaOptional.get().getUsuario().getNif());
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());

        mv.addObject("tarea", tarea);
        mv.setViewName("editartareasadmin");
        return mv;
    }

    @RequestMapping("/admin/tareas")
    public ModelAndView peticioTareaMostrar(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        List<Usuario> listaUsuario=usuarios.listaUsuarios();
        for (Usuario user: listaUsuario) {
            System.out.println(user.getNif()+" "+user.getNombre());
        }
        mv.addObject("listaUsuarios", listaUsuario);
        mv.setViewName("mostrartareasall");
        return mv;
    }
    @RequestMapping("/admin/usuario/nuevo")
    public ModelAndView peticioUsuariosCrear() {
        ModelAndView mv = new ModelAndView();
        Usuario c = new Usuario();
        mv.addObject("usuario", c);
        mv.setViewName("nuevousuario");
        return mv;
    }
    @RequestMapping("/guardartarea")
    public ModelAndView peticionGuardarTarea(Tarea tarea,Authentication aut) {
        ModelAndView mv = new ModelAndView();
        Usuario user= usuarios.buscarUsuario(aut.getName()).get();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else{
            mv.addObject("user", aut.getName());

        }
            tarea.setUsuario(user);
            tareas.guardarTarea(tarea);
            mv.addObject("sms", "Tarea guardada");

        mv.setViewName("informa");
        return mv;
    }
    @RequestMapping("/guardar")
    public ModelAndView peticionGuardar(Usuario user,Authentication aut) {
        ModelAndView mv = new ModelAndView();
        System.out.println("Usuario: "+user);
        String encriptado = user.getPw();
        String cifrado = encoder.encode(encriptado);
        user.setPw(cifrado);
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        Optional<Usuario> usuarioBuscado = usuarios.buscarUsuario(user.getNif());
        if (usuarioBuscado.isPresent()) {
            mv.addObject("sms", "El nif " + user.getNif() + " ya está utilizado");
        } else {
            usuarios.guardarUsuario(user);
            Role rol = new Role();
            rol.setUsuario(user);
            rol.setRol("USUARIO");
            roles.guardarRol(rol);
            mv.addObject("sms", "Usuario " + user.getNombre() + " registrado con éxito");
        }
        mv.setViewName("informa");
        return mv;
    }
    @RequestMapping("/actualizar")
    public String peticionActualizar(Usuario user){
        System.out.println("Usuario: "+user);
        String encriptado = user.getPw();
        String cifrado = encoder.encode(encriptado);
        user.setPw(cifrado);
        usuarios.guardarUsuario(user);
        return "redirect:/admin";
    }
    @RequestMapping("/admin")
    public ModelAndView peticioAdmin(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        Double promedioTareas = tareas.promedioTareas();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.addObject("nuevas", tareas.cuentaTareas());
        mv.addObject("proceso", tareas.cuentaTareasProc());
        mv.addObject("terminada", tareas.cuentaTareasTer());
        mv.addObject("eliminada", tareas.cuentaTareasElimina());
        mv.addObject("cuentuser", usuarios.cuentausers());
        mv.addObject("promedioTareas", promedioTareas);
        mv.setViewName("administrador");
        return mv;
    }
    @RequestMapping("/user")
    public ModelAndView peticionUsuario(Authentication aut) {
        ModelAndView mv = new ModelAndView();

        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("usuario");
        return mv;
    }
    @RequestMapping("/admin/tarea/añadir")
    public ModelAndView peticioTareasAñadir(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        Tarea tarea = new Tarea();
        mv.addObject("tarea", tarea);
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("añadirtareaadmin");
        return mv;
    }
    @RequestMapping("/creartareaadmin")
    public String createtareasadmin(Authentication aut,Tarea tarea) {
        System.out.println(tarea.getUsuario().getNif());
        tareas.guardarTarea(tarea);
        return "redirect:/admin";
    }
    @RequestMapping("/editartareaadmin")
    public String peticionEditarTA(Authentication aut, Tarea tarea) {
        tareas.guardarTarea(tarea);
        return "redirect:/user/tareas/listado";
    }
}
