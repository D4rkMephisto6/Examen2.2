package com.example.ms_reserva.service;

import com.example.ms_reserva.entity.Reserva;
import com.example.ms_reserva.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Reserva guardar(Reserva reserva) {

        String url =
                "http://localhost:8082/habitaciones/"
                        + reserva.getIdHabitacion();

        Object habitacion =
                restTemplate.getForObject(url, Object.class);

        if (habitacion == null) {
            throw new RuntimeException("Habitacion no encontrada");
        }

        return repository.save(reserva);
    }
}