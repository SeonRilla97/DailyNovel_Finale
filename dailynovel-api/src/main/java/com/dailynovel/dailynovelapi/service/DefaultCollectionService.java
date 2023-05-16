package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.entity.Collection;
import com.dailynovel.dailynovelapi.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCollectionService implements CollectionService{

    @Autowired
    private CollectionRepository repository;
    @Override
    public List<Collection> listAll() {
    List<Collection> list = repository.findAll();
//        System.out.println(list);
        return list;
    }
}
