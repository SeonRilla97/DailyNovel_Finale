package com.dailynovel.dailynovelapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DisplayRepository extends JpaRepository<Diary, Integer> {

    @Query("SELECT d.* FROM Diary d INNER JOIN DiaryDisplay dd ON d.id = dd.diary_id;")
    public
    List<Diary> findSharedDiaries;
    
}
