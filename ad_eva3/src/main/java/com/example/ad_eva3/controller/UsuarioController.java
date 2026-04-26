package com.example.ad_eva3.controller;
import com.example.ad_eva3.model.Usuario;
import com.example.ad_eva3.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/usuario")
// TODO CORS
public class UsuarioController {
    //admite las peticiones entrantes desde http
    // extrae los datos, indica que parte del servicio ejecuta

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Usuario> usuarioOptional = usuarioService.getById(id);
            if (usuarioOptional.isPresent()) {
                response.put("code", 1);
                response.put("message", "Usuario encontrado");
                response.put("data", usuarioOptional.get());
            } else {
                response.put("code", 2);
                response.put("message", "id invalido");
                response.put("data", null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", 3);
            response.put("message", "Error en el endpoint");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getUsuarios() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Usuario> usuarios = usuarioService.obtenerAllUsuarios();

                response.put("code", 1);
                response.put("message", "Usuarios encontrados");
                response.put("data", usuarios);
                response.put("size", usuarios.size());

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", 3);
            response.put("message", "Error en el endpoint");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioAgregado = usuarioService.agregarUsuario(usuario);

            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("message", "Usuario agregado");
            response.put("data", usuarioAgregado);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 3);
            response.put("message", "Usuario ya existe");
            response.put("data", e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable long id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioService.actualizarUsuario(id, usuarioActualizado.getNombre(), usuarioActualizado.getEmail());
         Map<String, Object> response = new HashMap<>();
         response.put("code", 1);
         response.put("message", "Usuario actualizado");
         response.put("data", usuario) ;
             return ResponseEntity.status(HttpStatus.OK).body(response);
         }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable long id) {
        usuarioService.borrarUsuario(id);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("message", "Usuario eliminado");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
