package com.example.ms_habitacion.repository;

import com.example.ms_habitacion.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}