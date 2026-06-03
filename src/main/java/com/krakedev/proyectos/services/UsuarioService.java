package com.krakedev.proyectos.services;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(String username,
                             String password,
                             String rol) {
        Usuario u = new Usuario();
        u.setUsername(username);
        // Hasheo unidireccional con BCrypt
        u.setPassword(BCrypt.hashpw(password,
                                    BCrypt.gensalt()));
        u.setRol(rol);
        return usuarioRepository.save(u);
    }

    public Optional<Usuario> autenticar(String username,
                                         String password) {
        Optional<Usuario> opt =
                usuarioRepository.findByUsername(username);
        if (opt.isPresent() &&
            BCrypt.checkpw(password,
                           opt.get().getPassword())) {
            return opt;
        }
        return Optional.empty();
    }
}