package org.polytech.covidapi.resources;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.polytech.covidapi.entities.Center;
import org.polytech.covidapi.services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/center")
public class CenterController {
    private final CenterService centerService;
    private final Bucket bucket;

    @Autowired
    public CenterController(CenterService centerService, Bucket bucket) {
        this.centerService = centerService;
        this.bucket = bucket;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCenter(@RequestParam(value = "q", required = false) String name) {
        ConsumptionProbe consumptionProbe = bucket.tryConsumeAndReturnRemaining(1);
        if (consumptionProbe.isConsumed()){
            if (name == null) {
                return ResponseEntity.ok(this.centerService.getAllCenters());
            } else {
                return ResponseEntity.ok(this.centerService.getCentersByName(name));
            }
        } else {
            return ResponseEntity.status(429).body(consumptionProbe.getNanosToWaitForRefill());
        }
    }

    //@PreAuthorize("hasAnyAuthority('center:create')")
    @PostMapping("/add")
    public ResponseEntity<?> addCenter(@RequestBody ObjectNode body) {
        String name = body.get("name").asText();
        Long cityId = body.get("cityId").asLong();
        String address = body.get("address").asText();
        String phone = body.get("phone").asText();
        String email = body.get("email").asText();
        return ResponseEntity.ok(this.centerService.addCenter(name, cityId, address, phone, email));
    }

    @PreAuthorize("hasAnyAuthority('center:update')")
    @PutMapping("/update")
    public ResponseEntity<?> updateCenter(@RequestBody Center center) {
        return ResponseEntity.ok(this.centerService.updateCenter(center));
    }

    @PreAuthorize("hasAnyAuthority('center:delete')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCenter(@RequestParam Long id) {
        this.centerService.deleteCenterById(id);
        return ResponseEntity.ok().build();
    }
}
