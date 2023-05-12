package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.repository.DiaryRepository;

@Service
public class DiaryDefaultService implements DiaryService{



    @Autowired
    private DiaryRepository repository;

    @Override
    public List<Diary> getByFeeling(Integer memberId){
        return repository.findByFeeling(memberId);
    }

    @Override
    public List<Diary> getByTag(Integer memberId) {
        // TODO Auto-generated method stub
        return repository.findByTag(memberId);
    }

    @Override
    public List<Diary> getByHonesty(Integer memberId) {
        // TODO Auto-generated method stub
        return repository.findByHonesty(memberId);
    }


    
}
