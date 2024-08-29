package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @GetMapping
    public ResponseEntity<List<Reading>> getAllReadings() {
        return new ResponseEntity<>(readingService.getAllReadings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reading> getReadingById(@PathVariable int id) {
        Reading reading = readingService.getReadingById(id);
        return reading != null ? ResponseEntity.ok(reading) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Reading> createReading(@RequestBody Reading reading) {
        return new ResponseEntity<>(readingService.createReading(reading), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reading> updateReading(@PathVariable int id, @RequestBody Reading reading) {
        reading.setId_reading(id);
        Reading updatedReading = readingService.updateReading(reading);
        return updatedReading != null ? ResponseEntity.ok(updatedReading) : ResponseEntity.notFound().build();
    }

}