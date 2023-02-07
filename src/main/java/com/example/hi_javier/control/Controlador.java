package com.example.hi_javier.control;


import com.example.hi_javier.jpa.Usuario;
import com.example.hi_javier.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Controlador {

    @Autowired
    UsuarioService usuarios;
     @RequestMapping("/")
        public ModelAndView peticionRaiz(Authentication aut){
         ModelAndView mv = new ModelAndView();
//         System.out.println("Usuario: " + aut.getName());
//         System.out.println(aut.getAuthorities());
         if(aut==null)
             mv.addObject("user", "No se ha iniciado sesión");
         else
             mv.addObject("user", aut.getName());
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
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("perfil");
        return mv;
    }
    @RequestMapping("/user/tareas/nueva")
    public ModelAndView peticioNuevaTarea(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("nuevatarea");
        return mv;
    }
    @RequestMapping("/user/tareas/listado")
    public ModelAndView peticioListdoTareas(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("perfil");
        mv.setViewName("listadotareas");
        return mv;
    }
    @RequestMapping("/admin/dashboard")
    public ModelAndView peticioDashboard(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("perfil");
        mv.setViewName("dashboard");
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
    public ModelAndView peticioUsuariosEditar(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("perfil");
        mv.setViewName("editarusuarios");
        return mv;
    }
    @RequestMapping("/admin")
    public ModelAndView peticioAdmin(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", aut.getName());
        mv.setViewName("perfil");
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
    /*
/login
/user/perfil
/user/tareas/nueva
/user/tareas/listado
/admin/dashboard
/admin/usuarios/mostrar
/admin/usuarios/editar
*/
}
