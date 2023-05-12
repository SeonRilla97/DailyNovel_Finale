package com.dailynovel.dailynovelapi.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.diary;
import com.dailynovel.dailynovelapi.repository.FeelingRepository;
import com.dailynovel.dailynovelapi.repository.HonestyRepository;
import com.dailynovel.dailynovelapi.repository.TagRepository;

@Service
public class InsightDefaultService implements InsightService{
    

    @Autowired
    private FeelingRepository feelingRepository;

    @Autowired
    private HonestyRepository honestyRepository;

    @Autowired
    private TagRepository tagRepository;


   

    @Override
    public List<diary> getByFeeling(Integer memberId){
        return feelingRepository.findByFeeling(memberId);
    }

    @Override
    public List<diary> getByHonesty(Integer memberId) {
        // TODO Auto-generated method stub
        return honestyRepository.findByHonesty(memberId);
    }




    @Override
    public List<diary> getByTag(Integer memberId) {
        // TODO Auto-generated method stub
        return tagRepository.findByTag(memberId);
    }


    



}