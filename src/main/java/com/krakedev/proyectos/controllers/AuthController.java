package com.krakedev.proyectos.controllers;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(
            @RequestBody Map<String, String> body) {
        Usuario u = usuarioService.registrar(
            body.get("username"),
            body.get("password"),
            body.get("rol")
        );
        return ResponseEntity.ok(
            Map.of("mensaje", "Usuario registrado",
                   "id", u.getId()));
    }
}