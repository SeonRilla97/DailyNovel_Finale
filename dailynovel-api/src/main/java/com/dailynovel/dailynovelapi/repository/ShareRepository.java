package com.dailynovel.dailynovelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.DiaryDisplay;

public interface ShareRepository extends JpaRepository<DiaryDisplay, Integer>{
    
}
