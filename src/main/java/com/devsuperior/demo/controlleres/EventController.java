package com.devsuperior.demo.controlleres;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.servicies.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService service;

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        EventDTO updatedEvent = service.update(id, eventDTO);
        return ResponseEntity.ok(updatedEvent);
    }


}
