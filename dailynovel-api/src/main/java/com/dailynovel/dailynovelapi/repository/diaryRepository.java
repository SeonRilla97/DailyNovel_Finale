package com.dailynovel.dailynovelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DiaryRepository  extends JpaRepository<Diary, Integer>{
    

    
}
