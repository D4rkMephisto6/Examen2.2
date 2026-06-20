package com.example.ms_reserva.service;

import com.example.ms_reserva.entity.Reserva;
import com.example.ms_reserva.repository.ReservaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final RestTemplate restTemplate;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Reserva guardar(Reserva reserva) {

        String url =
                "http://localhost:8082/habitaciones/"
                        + reserva.getIdHabitacion();

        ResponseEntity<Object> response =
                restTemplate.getForEntity(url, Object.class);

        if (!response.getStatusCode().is2xxSuccessful()
                || response.getBody() == null) {

            throw new RuntimeException(
                    "Habitación no encontrada");
        }

        return repository.save(reserva);
    }

    public Reserva actualizar(Long id, Reserva reserva) {

        reserva.setId(id);

        return repository.save(reserva);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}