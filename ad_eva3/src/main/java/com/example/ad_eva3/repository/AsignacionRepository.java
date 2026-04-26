package com.example.ad_eva3.repository;

import com.example.ad_eva3.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    //TODOS LOS METODOS QUE VAN CONTRA LA BASE DE DATOS
    //las firmas solo de aquellos metodos que no son "por defecto"


}
