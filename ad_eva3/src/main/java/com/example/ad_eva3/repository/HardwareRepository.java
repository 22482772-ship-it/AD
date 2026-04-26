package com.example.ad_eva3.repository;

import com.example.ad_eva3.model.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareRepository extends JpaRepository<Hardware, Long> {
    //TODOS LOS METODOS QUE VAN CONTRA LA BASE DE DATOS
    //las firmas solo de aquellos metodos que no son "por defecto"


}
