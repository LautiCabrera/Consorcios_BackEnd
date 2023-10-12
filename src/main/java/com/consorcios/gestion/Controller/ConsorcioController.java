package com.consorcios.gestion.Controller;

import java.util.List;
import org.springframework.http.*;
import org.apache.commons.lang3.StringUtils;
import com.consorcios.gestion.Entity.Consorcio;
import com.consorcios.gestion.Dto.ConsorcioDto;
import com.consorcios.gestion.Security.Controller.Mensaje;
import com.consorcios.gestion.Service.ImplementConsorcioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consorcio")
public class ConsorcioController {
    
    @Autowired
    ImplementConsorcioService implementConsorcioService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Consorcio>> list(){
        List<Consorcio> list = implementConsorcioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Consorcio> getById(@PathVariable("id")int id){
        if(!implementConsorcioService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        Consorcio consorcio = implementConsorcioService.getOne(id).get();
        return new ResponseEntity(consorcio, HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!implementConsorcioService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        implementConsorcioService.delete(id);
        return new ResponseEntity(new Mensaje("Consorcio eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ConsorcioDto consorcioDto){
        if(StringUtils.isBlank(consorcioDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(implementConsorcioService.existsByNombre(consorcioDto.getNombre()))
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Consorcio consorcio = new Consorcio(consorcioDto.getId_servicio(), consorcioDto.getCuit(), consorcioDto.getNombre(),consorcioDto.getLocalidad(), consorcioDto.getDireccion(), consorcioDto.getCant_usuarios());
        implementConsorcioService.save(consorcio);
        return new ResponseEntity(new Mensaje("Consorcio creado"), HttpStatus.OK);
                
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ConsorcioDto userdto){
        if(!implementConsorcioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        if(implementConsorcioService.existsByNombre(userdto.getNombre()) && implementConsorcioService.getByNombre(userdto.getNombre()).get().getId_consorcio() != id)
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(userdto.getNombre()))
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        
        Consorcio consorcio = implementConsorcioService.getOne(id).get();
        consorcio.setId_servicio(userdto.getId_servicio());
        consorcio.setCuit(userdto.getCuit());
        consorcio.setNombre(userdto.getNombre());
        consorcio.setLocalidad(userdto.getLocalidad());
        consorcio.setDireccion(userdto.getDireccion());
        consorcio.setCant_usuarios(userdto.getCant_usuarios());
        
        implementConsorcioService.save(consorcio);
        
        return new ResponseEntity(new Mensaje("Consorcio actualizado"), HttpStatus.OK);
    }
    
}
