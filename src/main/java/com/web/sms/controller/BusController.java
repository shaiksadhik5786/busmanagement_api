package com.web.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.sms.entity.Bus;
import com.web.sms.service.BusService;

import java.util.List;

@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        Bus savedBus = busService.addBus(bus);
        return ResponseEntity.ok(savedBus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable long id, @RequestBody Bus bus) {
        bus.setId(id);
        Bus updatedBus = busService.updateBus(bus);
        return ResponseEntity.ok(updatedBus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable long id) {
        Bus bus = busService.getBusById(id);
        return ResponseEntity.ok(bus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusById(@PathVariable long id) {
        busService.deleteBusById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }
}
