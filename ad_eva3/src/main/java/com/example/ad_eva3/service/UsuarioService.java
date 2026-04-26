package com.example.ad_eva3.service;


import com.example.ad_eva3.model.Usuario;
import com.example.ad_eva3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UsuarioService {
    //la logica para poder decir sobre las ejecuciones de la BBDD
    @Autowired
    private UsuarioRepository usuarioRepository;


    //obteremos todos los datos de los usuarios
    public List<Usuario> obtenerAllUsuarios(){
        return usuarioRepository.findAll();
    }
    //obtener todos los datos de un usuario -> id
    public Optional<Usuario> getById(Long id){
        return usuarioRepository.findById(id);
    }

    //añadir un usuario
    public Usuario agregarUsuario(Usuario usuario) {
        //decide logicamente si puedes hacer una llamada al elemento
        // producto con stock negativo
        return usuarioRepository.save(usuario);
    }

    //borrar un usuario
public boolean borrarUsuario(Long id){
        if (getById(id).isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        }
    return false;
}


public Usuario actualizarUsuario(Long id, String nombre, String email ) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);

    if (usuario.isPresent()) {
        usuario.get().setNombre(nombre);
        usuario.get().setEmail(email);
        return usuarioRepository.save(usuario.get());
    }
    return null;

}


}
