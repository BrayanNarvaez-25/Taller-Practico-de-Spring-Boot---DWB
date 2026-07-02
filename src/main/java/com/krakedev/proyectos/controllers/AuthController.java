// Reemplaza/actualiza AuthController.java completo:
package com.krakedev.proyectos.controllers;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.security.JwtUtil;
import com.krakedev.proyectos.services.TokenBlacklistService;
import com.krakedev.proyectos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = {"Authorization", "Content-Type"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private TokenBlacklistService blacklist;

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Map<String, String> body) {
		Usuario u = usuarioService.registrar(body.get("username"), body.get("password"), body.get("rol"));
		return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado", "id", u.getId()));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
		Optional<Usuario> opt = usuarioService.autenticar(body.get("username"), body.get("password"));
		if (opt.isEmpty()) {
			return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
		}
		Usuario u = opt.get();
		String token = jwtUtil.generarToken(u.getUsername(), u.getRol());
		return ResponseEntity.ok(Map.of("token", token));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		blacklist.invalidar(token);
		return ResponseEntity.ok(Map.of("mensaje", "Sesión cerrada"));
	}

	@GetMapping("/perfil")
	public ResponseEntity<?> perfil() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.ok(Map.of("username", auth.getName(), "roles", auth.getAuthorities().toString()));
	}
}