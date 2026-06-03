package com.krakedev.proyectos.services;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

    private final Set<String> tokensInvalidados =
        Collections.newSetFromMap(
            new ConcurrentHashMap<>());

    public void invalidar(String token) {
        tokensInvalidados.add(token);
    }

    public boolean estaInvalidado(String token) {
        return tokensInvalidados.contains(token);
    }
}