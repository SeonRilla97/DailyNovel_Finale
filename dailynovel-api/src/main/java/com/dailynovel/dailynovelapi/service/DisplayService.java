package com.dailynovel.dailynovelapi.service;

import java.util.List;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DisplayService {

    List<Diary> getBySharedDiaryList();
    
}
