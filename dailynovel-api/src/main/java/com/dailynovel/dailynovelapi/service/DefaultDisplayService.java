package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DisplayView;
import com.dailynovel.dailynovelapi.repository.DisplayRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class DefaultDisplayService implements DisplayService {

    @Autowired
    private DisplayRepository repository;

    @Override
    public List<DisplayView> getByList() {
        return repository.findAll();
    }

    @Override
    public void insertLike(DiaryLike like) {
        
    }

    @Override
    public void deleteLike(DiaryLike like) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteLike'");
    }



    
}
