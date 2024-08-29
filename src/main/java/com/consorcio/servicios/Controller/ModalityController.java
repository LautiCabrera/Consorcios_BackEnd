package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Entity.Modality;
import com.consorcio.servicios.Service.ModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/modalities")
public class ModalityController {

    @Autowired
    private ModalityService modalityService;

    @GetMapping
    public ResponseEntity<List<Modality>> getAllModalities() {
        return new ResponseEntity<>(modalityService.getAllModalities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modality> getModalityById(@PathVariable int id) {
        Modality modality = modalityService.getModalityById(id);
        return modality != null ? ResponseEntity.ok(modality) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Modality> createModality(@RequestBody Modality modality) {
        return new ResponseEntity<>(modalityService.createModality(modality), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modality> updateModality(@PathVariable int id, @RequestBody Modality modality) {
        modality.setId_modality(id);
        Modality updatedModality = modalityService.updateModality(modality);
        return updatedModality != null ? ResponseEntity.ok(updatedModality) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModality(@PathVariable int id) {
        modalityService.deleteModality(id);
        return ResponseEntity.noContent().build();
    }

}