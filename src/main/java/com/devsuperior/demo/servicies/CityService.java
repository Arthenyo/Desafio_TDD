package com.devsuperior.demo.servicies;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.servicies.exception.DateBaseException;
import com.devsuperior.demo.servicies.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;


    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> departments = repository.findAll(Sort.by("name"));
        return departments.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO save(CityDTO employeeDTO){
        City entity = new City();
        entity.setName(employeeDTO.getName());
        entity = repository.save(entity);
        return new CityDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ObjectNotFound("Cidade nao encontrada " + id);
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DateBaseException("Não foi possivel deletar a Produto %d, erro de integridade " + id);
        }
    }

}
