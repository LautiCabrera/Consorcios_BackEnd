package com.consorcios.gestion.Service;

import java.util.List;
import java.util.Optional;
import com.consorcios.gestion.Entity.Usuario;
import org.springframework.stereotype.Service;
import com.consorcios.gestion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ImplementUsuarioService {
    
    @Autowired UsuarioRepository usuarioRepository;
    
    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> getOne(int id){
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> getByNombre(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }
    
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
    public void delete(int id){
        usuarioRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return usuarioRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return usuarioRepository.existsByNombre(nombre);
    }
    
}
