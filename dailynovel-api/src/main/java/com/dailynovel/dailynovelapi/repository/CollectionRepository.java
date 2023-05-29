package com.dailynovel.dailynovelapi.repository;

import com.dailynovel.dailynovelapi.entity.Collection;
import com.dailynovel.dailynovelapi.entity.Diary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {
    
    List<Collection> findAllByMemberId(int memberId);

}
