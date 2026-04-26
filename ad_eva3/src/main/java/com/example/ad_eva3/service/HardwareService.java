package com.example.ad_eva3.service;

import com.example.ad_eva3.model.Hardware;
import com.example.ad_eva3.repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class HardwareService {
    @Autowired
    private HardwareRepository hardwareRepository;

    //getAllHardware
    public List<Hardware> getAllHardware() {
        return hardwareRepository.findAll();
    }

    //obtener todos los datos de un hardware -> id
    public Optional<Hardware> getById(Long id){
        return hardwareRepository.findById(id);
    }

    //añadir un hardware
    public Hardware agregarHardware(Hardware hardware) {
        //decide logicamente si puedes hacer una llamada al elemento
        return hardwareRepository.save(hardware);
    }

    //borrar un hardware
public boolean borrarHardware(Long id){
        if (getById(id).isPresent()) {
            hardwareRepository.deleteById(id);
            return true;
        }
    return false;
}

//Actualizar un hardware existente
    public Hardware actualizarHardware(Long id, Hardware hardwareActualizado) {
        Optional<Hardware> hardware = hardwareRepository.findById(id);

        if (hardware.isPresent()) {
            Hardware hwActualizado = hardware.get();

            //Actualizamos todos los datos con los valores nuevos
            hwActualizado.setSn(hardwareActualizado.getSn());
            hwActualizado.setModelo(hardwareActualizado.getModelo());
            hwActualizado.setTipoDispositivo(hardwareActualizado.getTipoDispositivo());
            hwActualizado.setEstado(hardwareActualizado.getEstado());
            return hardwareRepository.save(hwActualizado);
        }
        return null;
    }


}
