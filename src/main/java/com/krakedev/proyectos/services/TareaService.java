package com.krakedev.proyectos.services;

import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository repo;

    public Tarea guardar(Tarea t) { return repo.save(t); }
    public List<Tarea> listar() { return repo.findAll(); }
}