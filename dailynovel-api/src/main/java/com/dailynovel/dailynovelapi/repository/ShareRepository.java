package com.dailynovel.dailynovelapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.DiaryDisplay;

public interface ShareRepository extends JpaRepository<DiaryDisplay, Integer>{
    
    // @Query("select * from DiaryDisplay")
    List<DiaryDisplay> findByMemberIdAndDiaryId(int memberId, int diaryId);
}
