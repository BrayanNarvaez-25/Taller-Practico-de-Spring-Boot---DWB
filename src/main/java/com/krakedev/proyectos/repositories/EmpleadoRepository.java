package com.krakedev.proyectos.repositories;
import com.krakedev.proyectos.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {}
