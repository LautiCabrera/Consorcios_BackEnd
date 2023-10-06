package com.consorcios.gestion.Controller;

import com.consorcios.gestion.Dto.UsuarioDto;
import com.consorcios.gestion.Entity.Usuario;
import com.consorcios.gestion.Security.Controller.Mensaje;
import com.consorcios.gestion.Service.ImplementUsuarioService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    ImplementUsuarioService implementUsuarioService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = implementUsuarioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id")int id){
        if(!implementUsuarioService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = implementUsuarioService.getOne(id).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!implementUsuarioService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        implementUsuarioService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody UsuarioDto userdto){
        if(StringUtils.isBlank(userdto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(implementUsuarioService.existsByNombre(userdto.getNombre()))
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Usuario educacion = new Usuario(userdto.getId_consorcio(), userdto.getId_tarifa(), userdto.getNum_conex(),userdto.getNombre(), userdto.getDni(), userdto.getDomicilio(), userdto.getEmail(), userdto.getTelefono());
        implementUsuarioService.save(educacion);
        return new ResponseEntity(new Mensaje("Usuario creado"), HttpStatus.OK);
                
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UsuarioDto userdto){
        if(!implementUsuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        if(implementUsuarioService.existsByNombre(userdto.getNombre()) && implementUsuarioService.getByNombre(userdto.getNombre()).get().getId_usuario() != id)
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(userdto.getNombre()))
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        
        Usuario usuario = implementUsuarioService.getOne(id).get();
        usuario.setId_consorcio(userdto.getId_consorcio());
        usuario.setId_tarifa(id);
        usuario.setNum_conex(userdto.getNum_conex());
        usuario.setNombre(userdto.getNombre());
        usuario.setDni(userdto.getDni());
        usuario.setDomicilio(userdto.getDomicilio());
        usuario.setEmail(userdto.getEmail());
        usuario.setTelefono(userdto.getTelefono());
        
        implementUsuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    }

    
}

