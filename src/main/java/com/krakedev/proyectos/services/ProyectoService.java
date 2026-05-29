package com.krakedev.proyectos.services;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.repositories.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository repo;

    public Proyecto guardar(Proyecto p) { return repo.save(p); }
    public List<Proyecto> listar() { return repo.findAll(); }
    public Optional<Proyecto> buscarPorId(int id) { return repo.findById(id); }
    public Proyecto actualizar(int id, Proyecto p) {
        p.setId(id);
        return repo.save(p);
    }
    public void eliminar(int id) { repo.deleteById(id); }
}