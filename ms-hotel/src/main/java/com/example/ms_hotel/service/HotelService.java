package com.example.ms_hotel.service;

import com.example.ms_hotel.entity.Hotel;
import com.example.ms_hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository repository;

    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public List<Hotel> listar() {
        return repository.findAll();
    }

    public Hotel guardar(Hotel hotel) {
        return repository.save(hotel);
    }

    public Hotel actualizar(Long id, Hotel hotel) {
        hotel.setId(id);
        return repository.save(hotel);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}