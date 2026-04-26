package com.example.ad_eva3.service;

import com.example.ad_eva3.model.Accesorio;
import com.example.ad_eva3.repository.AccesorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class AccesorioService {
@Autowired
private AccesorioRepository accesorioRepository;

public List<Accesorio> getAllAccesorios(){
    return accesorioRepository.findAll();
}

public Optional<Accesorio> getById(Long id){
    return accesorioRepository.findById(id);
}

public Accesorio agregarAccesorio(Accesorio accesorio){
    return accesorioRepository.save(accesorio);
}
public boolean borrarAccesorio(Long id){
    if(getById(id).isPresent()){
        accesorioRepository.deleteById(id);
        return true;
    }
    return false;
}

public Accesorio actualizarAccesorio(Long id, Accesorio accesorioActualizado){
    Optional<Accesorio> accesorio = accesorioRepository.findById(id);
    if(accesorio.isPresent()){
        Accesorio acc = accesorio.get();

        //Actualizamos todos los datos con los valores nuevos
        acc.setNombre(accesorioActualizado.getNombre());
        acc.setTipoAccesorio(accesorioActualizado.getTipoAccesorio());
        acc.setEstado(accesorioActualizado.getEstado());
        return accesorioRepository.save(acc);
    }
    return null;
}

}
