package com.krakedev.proyectos.repositories;
import com.krakedev.proyectos.entidades.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TareaRepository extends JpaRepository<Tarea, Integer> {}