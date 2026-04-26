package com.example.ad_eva3.repository;

import com.example.ad_eva3.model.Accesorio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccesorioRepository extends JpaRepository<Accesorio, Long> {
    //TODOS LOS METODOS QUE VAN CONTRA LA BASE DE DATOS
    //las firmas solo de aquellos metodos que no son "por defecto"


}
