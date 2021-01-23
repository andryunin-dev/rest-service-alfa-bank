package com.example.restalfabank.service;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.repository.BoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {

    private final BoxRepository boxRepository;

    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    public List<Box> getAll() {
        return boxRepository.findAll();
    }

    public Box save(Box box) {
        return boxRepository.save(box);
    }
}
