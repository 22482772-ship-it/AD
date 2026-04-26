package com.example.ad_eva3.repository;

import com.example.ad_eva3.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //TODOS LOS METODOS QUE VAN CONTRA LA BASE DE DATOS
    //las firmas solo de aquellos metodos que no son "por defecto"


}
