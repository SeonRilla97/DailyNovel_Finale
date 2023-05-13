package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.DisplayView;
import com.dailynovel.dailynovelapi.repository.DisplayRepository;

@Service
public class DefaultDisplayService implements DisplayService {

    @Autowired
    private DisplayRepository repository;

    @Override
    public List<DisplayView> getByList() {
        return repository.findAll();
    }



    
}
