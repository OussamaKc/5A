package org.polytech.covidapi.resources;

import org.polytech.covidapi.entities.City;
import org.polytech.covidapi.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.polytech.covidapi.services.CityService;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok(this.cityService.getAllCities());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCity(@RequestBody City city) {
        return ResponseEntity.ok(this.cityService.addCity(city));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCity(@RequestBody City city) {
        return ResponseEntity.ok(this.cityService.updateCity(city));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCity(@RequestParam Long id) {
        this.cityService.deleteCityById(id);
        return ResponseEntity.ok().build();
    }


}
