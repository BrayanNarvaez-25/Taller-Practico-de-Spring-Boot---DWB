package com.krakedev.proyectos.services;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository repo;

    public Empleado guardar(Empleado e) { return repo.save(e); }
    public List<Empleado> listar() { return repo.findAll(); }
    public Optional<Empleado> buscarPorId(int id) { return repo.findById(id); }
    public Empleado actualizar(int id, Empleado e) {
        e.setId(id);
        return repo.save(e);
    }
    public void eliminar(int id) { repo.deleteById(id); }
}