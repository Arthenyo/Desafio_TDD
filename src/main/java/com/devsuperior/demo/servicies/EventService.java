package com.devsuperior.demo.servicies;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.servicies.exception.ObjectNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private CityRepository cityRepository;


    public EventDTO update(Long id, EventDTO eventDTO) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Evento não encontrado com o ID: " + id));
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setUrl(eventDTO.getUrl());
        event.setCity(cityRepository.getReferenceById(eventDTO.getCityId()));

        repository.save(event);

        return new EventDTO(event);
    }

}
