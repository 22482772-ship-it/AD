package com.example.ad_eva3.controller;

import com.example.ad_eva3.model.Accesorio;
import com.example.ad_eva3.service.AccesorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/accesorio")
public class AccesorioController {
    @Autowired
    private AccesorioService accesorioService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAccesorioById(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Accesorio> accesorioOptional = accesorioService.getById(id);
            if (accesorioOptional.isPresent()) {
                response.put("code", 1);
                response.put("message", "Accesorio encontrado");
                response.put("data", accesorioOptional.get());
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
    public ResponseEntity<?> getAccesorios() {
     Map<String, Object> response = new HashMap<>();
     try {
         response.put("code", 1);
         response.put("message", "Accesorios encontrados");
         response.put("data", accesorioService.getAllAccesorios());
     } catch (Exception e) {
         response.put("code", 3);
         response.put("message", "Error en el endpoint");
         response.put("data", e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
     }
     return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAccesorio(@RequestBody Accesorio accesorio) {
        Map<String, Object> response = new HashMap<>();
        try {
            Accesorio accesorioAgregado = accesorioService.agregarAccesorio(accesorio);
            response.put("code", 1);
            response.put("message", "Accesorio agregado");
            response.put("data", accesorioAgregado);
        } catch (DataIntegrityViolationException e) {
            response.put("code", 3);
            response.put("message", "Error en la integridad de datos");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccesorio(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();

        // Guardamos el true o false que nos devuelve el servicio
        boolean borrado = accesorioService.borrarAccesorio(id);

        if (borrado) {
            response.put("code", 1);
            response.put("message", "Accesorio eliminado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("code", 2);
            response.put("message", "No se pudo eliminar el accesorio, ID invalido");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccesorio (@PathVariable long id, @RequestBody Accesorio accesorioActualizado) {

    Map<String, Object> response = new HashMap<>();
   Accesorio accesorio = accesorioService.actualizarAccesorio(id, accesorioActualizado);

    if (accesorio != null) {
        response.put("code", 1);
        response.put("message", "Accesorio actualizado");
        response.put("data", accesorio);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
        response.put("code", 2);
        response.put("message", "Accesorio no encontrado");
        response.put("data", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    }



}
