package com.example.ms_hotel.controller;

import com.example.ms_hotel.entity.Hotel;
import com.example.ms_hotel.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping
    public List<Hotel> listar() {
        return service.listar();
    }

    @PostMapping
    public Hotel guardar(@RequestBody Hotel hotel) {
        return service.guardar(hotel);
    }
}