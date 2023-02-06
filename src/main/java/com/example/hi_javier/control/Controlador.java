package com.example.hi_javier.control;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controlador {

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
    public ModelAndView peticionPerfil() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("perfil");
        return mv;
    }
    @RequestMapping("/user/tareas/nueva")
    public ModelAndView peticioNuevaTarea() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("nuevatarea");
        return mv;
    }
    @RequestMapping("/user/tareas/listado")
    public ModelAndView peticioListdoTareas() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listadotareas");
        return mv;
    }
    @RequestMapping("/admin/dashboard")
    public ModelAndView peticioDashboard() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dashboard");
        return mv;
    }
    @RequestMapping("/admin/usuario/mostrar")
    public ModelAndView peticioUsuariosMostrar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mostrarusuarios");
        return mv;
    }
    @RequestMapping("/admin/usuario/editar")
    public ModelAndView peticioUsuariosEditar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editarusuarios");
        return mv;
    }
    @RequestMapping("/admin")
    public ModelAndView peticioAdmin() {
        ModelAndView mv = new ModelAndView();
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
