package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.repository.DisplayRepository;

public class DefaultDisplayService implements DisplayService {

    @Autowired
    private DisplayRepository repository;


    @Override
    public List<Diary> getBySharedDiaryList() {
        
        return repository.findSharedDiaries();
    }
    
}
