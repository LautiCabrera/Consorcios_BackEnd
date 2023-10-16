package com.consorcios.gestion.Service;

import java.util.List;
import java.util.Optional;
import com.consorcios.gestion.Entity.Consorcio;
import org.springframework.stereotype.Service;
import com.consorcios.gestion.Repository.ConsorcioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ImplementConsorcioService {
    
    @Autowired ConsorcioRepository consorcioRepository;
    
    public List<Consorcio> list(){
        return consorcioRepository.findAll();
    }
    
    public Optional<Consorcio> getOne(int id){
        return consorcioRepository.findById(id);
    }
    
    public Optional<Consorcio> getByNombre(String nombre){
        return consorcioRepository.findByNombre(nombre);
    }
    
    public void save(Consorcio consorcio){
        consorcioRepository.save(consorcio);
    }
    
    public void delete(int id){
        consorcioRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return consorcioRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return consorcioRepository.existsByNombre(nombre);
    }
    
}
