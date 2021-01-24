package com.example.restalfabank.service.interfaces;

import com.example.restalfabank.model.Box;

import java.util.List;

public interface BoxService {

    List<Box> getAll();

    void save(Box box);

}
