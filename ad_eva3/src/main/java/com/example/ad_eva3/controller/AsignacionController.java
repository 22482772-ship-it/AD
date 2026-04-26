package com.example.ad_eva3.controller;

import com.example.ad_eva3.model.Asignacion;
import com.example.ad_eva3.service.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/asignacion")
public class AsignacionController {
    @Autowired
    private AsignacionService asignacionService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAsignacionById(@PathVariable long id){
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Asignacion> asignacionOptional = asignacionService.getById(id);
            if (asignacionOptional.isPresent()) {
                response.put("code", 1);
                response.put("message", "Asignacion encontrada");
                response.put("data", asignacionOptional.get());
            } else {
                response.put("code", 2);
                response.put("message", "ID invalido");
                response.put("data", null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", 3);
            response.put("message", "Error en el endpoint");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAsignaciones(){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("code", 1);
            response.put("message", "Asignaciones encontradas");
            response.put("data", asignacionService.getAllAsignaciones());
        } catch (Exception e) {
            response.put("code", 3);
            response.put("message", "Error en el endpoint");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAsignacion(@RequestBody Asignacion asignacion){
        Map<String, Object> response = new HashMap<>();
        try {
            Asignacion asignacionAgregada = asignacionService.agregarAsignacion(asignacion);
            response.put("code", 1);
            response.put("message", "Asignacion agregada");
            response.put("data", asignacionAgregada);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException e) {
            response.put("code", 3);
            response.put("message", "Error en la integridad de datos");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAsignacion(@PathVariable long id, @RequestBody Asignacion asignacionActualizada){
        Map<String, Object> response = new HashMap<>();
        Asignacion asignacion = asignacionService.actualizarAsignacion(id, asignacionActualizada);
        if (asignacion != null) {
            response.put("code", 1);
            response.put("message", "Asignacion actualizada");
            response.put("data", asignacion);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("code", 2);
            response.put("message", "Asignacion no encontrada");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAsignacion(@PathVariable long id){
        Map<String, Object> response = new HashMap<>();
        boolean borrado = asignacionService.borrarAsignacion(id);
        if (borrado) {
            response.put("code", 1);
            response.put("message", "Asignacion eliminada");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("code", 2);
            response.put("message", "ID invalido");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
