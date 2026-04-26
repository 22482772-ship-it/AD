package com.example.ad_eva3.controller;

import com.example.ad_eva3.model.Hardware;
import com.example.ad_eva3.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/hardware")
// TODO CORS
public class HardwareController {
    //admite las peticiones entrantes desde http
    // extrae los datos, indica que parte del servicio ejecuta

    @Autowired
    private HardwareService hardwareService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getHardwareById(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Hardware> hardwareOptional = hardwareService.getById(id);
            if (hardwareOptional.isPresent()) {
                response.put("code", 1);
                response.put("message", "Hardware encontrado");
                response.put("data", hardwareOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("code", 2);
                response.put("message", "ID invalido");
                response.put("data", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("code", 3);
            response.put("message", "Error en el endpoint");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllHardware(){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("code", 1);
            response.put("message", "Hardware encontrado");
            response.put("data", hardwareService.getAllHardware());
        } catch ( Exception e) {
            response.put("code", 3);
            response.put("message", "Error en el endpoint");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHardware(@RequestBody Hardware hardware){
        Map<String, Object> response = new HashMap<>();
        try {
            Hardware hardwareAgregado = hardwareService.agregarHardware(hardware);
            response.put("code", 1);
            response.put("message", "Hardware agregado");
            response.put("data", hardwareAgregado);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException e){
            response.put("code", 3);
            response.put("message", "Error en la integridad de datos");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHardware(@PathVariable long id, @RequestBody Hardware hardwareActualizado) {
        Map<String, Object> response = new HashMap<>();
        Hardware hardware = hardwareService.actualizarHardware(id, hardwareActualizado);

        if (hardware != null) {
            response.put("code", 1);
            response.put("message", "Hardware actualizado");
            response.put("data", hardware);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("code", 2);
            response.put("message", "Hardware no encontrado");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHardware(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        boolean borrado = hardwareService.borrarHardware(id);

        if (borrado) {
            response.put("code", 1);
            response.put("message", "Hardware eliminado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("code", 2);
            response.put("message", "Hardware no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}