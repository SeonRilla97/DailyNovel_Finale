package com.dailynovel.dailynovelapi.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.Diary;
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

    @Override
    public Map<String, List<Diary>> getListGroupingMonthly(String feeling, String weather, Date date, String order,
            String collection, String query) {
                List<Diary> list = repository.getListWithFiltering(feeling,weather,date,order,collection,query);
                System.out.println(list);
        return null;
    }


    
}
