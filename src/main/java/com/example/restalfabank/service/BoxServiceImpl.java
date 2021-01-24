package com.example.restalfabank.service;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.repository.BoxRepository;
import com.example.restalfabank.service.interfaces.BoxService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxServiceImpl implements BoxService {

    private final BoxRepository boxRepository;

    public BoxServiceImpl(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }


    public List<Box> getAll() {
        return boxRepository.findAll();
    }

    public void save(Box box) {
        boxRepository.save(box);
    }
}
