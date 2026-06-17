package com.example.ms_habitacion.service;

import com.example.ms_habitacion.entity.Habitacion;
import com.example.ms_habitacion.repository.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    private final HabitacionRepository repository;

    public HabitacionService(HabitacionRepository repository) {
        this.repository = repository;
    }

    public List<Habitacion> listar() {
        return repository.findAll();
    }

    public Habitacion guardar(Habitacion habitacion) {
        return repository.save(habitacion);

    }
    public Habitacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

}