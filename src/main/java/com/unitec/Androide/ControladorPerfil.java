package com.unitec.Androide;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Representational State Transfer Controller 
//Los estados mas comunes son: guardar, buscar, actualizar y borrar
@RestController

//api son las siglas de Application Programming Interface 
@RequestMapping("/api")
public class ControladorPerfil {

    @Autowired
    RepoPerfil repoPerfil;

    //En los servicios REST se tiene una urlBase que consiste
    //de la IP o host seguida del puerto, despues /api/hola
    //Es decir, para este caso mi api REST es: 
    //http://localhost:8080/api/hola
    @GetMapping("/hola")
    public Saludo saludar() {
        Saludo s = new Saludo();
        s.setNombre("David Garibay");
        s.setMensaje("Mi primer mensaje en Spring rest");
        return s;
    }

    //El siguiente metodo va a servir para guardar en un back-end 
    //nuestros datos del perfil 
    //Para guardar siempre debes usar el metodo POST
    @PostMapping("/perfil")
    public Estatus guardar(@RequestBody String json) throws Exception {
        //Paso 1 para recibir ese objeto json es leerlo y convertirlo
        //en objeto JAVA a esto se le llama des-serializaci�n 

        ObjectMapper maper = new ObjectMapper();
        Perfil perfil = maper.readValue(json, Perfil.class);

        //Por experiencia antes de guardar tenemos que checar que llego bien 
        //todo el objeto y se leyo bien
        System.out.println("Perfil leido" + perfil);
        //Aqui este objeto perfil despues se guarda con una sola linea en mongodb
        //Aqui va a ir la linea para guardar
        //Despues enviamos un mensaje de estatus al cliente para que se informe 
        //Si se guardo o no su perfil

        Estatus e = new Estatus();
        e.setSuccess(true);
        e.setMensaje("Perfil guardado con exito!");
        return e;

    }

    //Vamos a generar nuestros servicios para actualizar un perfil
    @PutMapping("/perfil")

    public Estatus actualizar(@RequestBody String json) throws Exception {

        ObjectMapper maper = new ObjectMapper();

        Perfil perfil = maper.readValue(json, Perfil.class);

        repoPerfil.save(perfil);

        Estatus e = new Estatus();
        e.setSuccess(true);
        e.setMensaje("Perfil actualizado con exito!");
        return e;
       
    }
     //El metodo para borrar un perfil 
        
        @DeleteMapping("/perfil/{id}")
        
        public Estatus borrar(@PathVariable String id ){
        
        //Invocamos el repositorio
        
        repoPerfil.deleteById(id);
        
        //Generamos el mensaje de estatus para que este informado el cliente 
        
        Estatus e=new Estatus();
        e.setMensaje("Perfil borrado con exito");
        e.setSuccess(true);
        return e;
    }
        
        //El metodo par abuscar todos 
        
        @GetMapping("/perfil")
        public List<Perfil> buscarTodos(){
            return repoPerfil.findAll();
        }
        
        //Finalmente el de buscar por id
        
        @GetMapping("/perfil/{id}")
        public Perfil buscarporId(@PathVariable String id){
            
            return repoPerfil.findById(id).get();
            
        }
}

//A este tipo de controlador estilo Rest es muy poderoso y se usa en todas
//las arquitecturas estilo REST, y se denomina CONSTRUCCION DE API's
//API= Application Programming Interface. 
//Union entre clienteAandroid) y servidor (java)

//Vamos a trabajar todo en la nube 
