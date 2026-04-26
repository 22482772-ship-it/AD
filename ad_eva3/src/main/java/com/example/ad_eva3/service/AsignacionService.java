package com.example.ad_eva3.service;

import com.example.ad_eva3.model.Asignacion;
import com.example.ad_eva3.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class AsignacionService {
    @Autowired
    private AsignacionRepository asignacionRepository;

    public List<Asignacion> getAllAsignaciones(){
        return asignacionRepository.findAll();
    }

    public Optional<Asignacion> getById(Long id){
        return asignacionRepository.findById(id);
    }

    public Asignacion agregarAsignacion(Asignacion asignacion){
        return asignacionRepository.save(asignacion);
    }

    public boolean borrarAsignacion(Long id){
        if (getById(id).isPresent()) {
            asignacionRepository.deleteById(id);
            return true;
        }
    return false;
    }

    public Asignacion actualizarAsignacion(Long id, Asignacion asignacionActualizado){
        Optional<Asignacion> asignacionExistente = asignacionRepository.findById(id);
        if(asignacionExistente.isPresent()){
            Asignacion asignacion = asignacionExistente.get();

            //Actualizamos todos los datos con los valores nuevos
            asignacion.setUsuario(asignacionActualizado.getUsuario());
            asignacion.setHardware(asignacionActualizado.getHardware());
            asignacion.setAccesorio(asignacionActualizado.getAccesorio());

            return asignacionRepository.save(asignacion);
        }
        return null;
    }


}
