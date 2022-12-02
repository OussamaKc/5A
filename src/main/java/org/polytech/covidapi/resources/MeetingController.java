package org.polytech.covidapi.resources;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.polytech.covidapi.entities.Meeting;
import org.polytech.covidapi.services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.polytech.covidapi.services.MeetingService;

import java.text.ParseException;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PreAuthorize("hasAnyAuthority('meeting:read')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllMeetings() {
        return ResponseEntity.ok(this.meetingService.getAllMeetings());
    }

    @PreAuthorize("hasAnyAuthority('meeting:read') or #id == authentication.principal.id")
    @GetMapping("/all/{id}")
    public ResponseEntity<?> getMeetingByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.meetingService.getMeetingByUserId(id));
    }

    @PreAuthorize("hasAnyAuthority('meeting:create')")
    @PostMapping("/add")
    public ResponseEntity<?> addMeeting(@RequestBody ObjectNode body) throws ParseException {
        Long user_id = body.get("user_id").asLong();
        Long center_id = body.get("center_id").asLong();
        String date = body.get("date").asText();
        return ResponseEntity.ok(this.meetingService.addMeeting(user_id, center_id, date));
    }

    @PreAuthorize("hasAnyAuthority('meeting:update')")
    @PutMapping("/update")
    public ResponseEntity<?> updateMeeting(@RequestBody Meeting meeting) {
        return ResponseEntity.ok(this.meetingService.updateMeeting(meeting));
    }

    @PreAuthorize("hasAnyAuthority('meeting:delete')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMeeting(@RequestParam Long id) {
        this.meetingService.deleteMeetingById(id);
        return ResponseEntity.ok().build();
    }
}
