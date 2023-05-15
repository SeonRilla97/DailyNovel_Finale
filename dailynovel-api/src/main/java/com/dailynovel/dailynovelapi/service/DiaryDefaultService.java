package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.repository.DiaryRepository;

@Service
public class DiaryDefaultService implements DiaryService{



    @Autowired
    private DiaryRepository repository;

    @Override
    public List<Object[]> getByFeeling(){
        return repository.findByFeeling(null);
    }

    @Override
    public List<Object[]> getByTag(Integer memberId) {
        // TODO Auto-generated method stub
        return repository.findByTag(memberId);
    }

    @Override
    public List<Object[]> getByHonesty(Integer memberId) {
        // TODO Auto-generated method stub
        return repository.findByHonesty(memberId);
    }


    
}
