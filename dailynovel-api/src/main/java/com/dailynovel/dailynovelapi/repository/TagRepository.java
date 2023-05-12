package com.dailynovel.dailynovelapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dailynovel.dailynovelapi.entity.diary;

public interface TagRepository extends JpaRepository<diary, Integer>{
    @Query("select memberId, tag, count(tag) "
            + "from diary "
            + "where memberId = :memberId "
            + "group by tag")
    List<diary> findByTag(Integer memberId);
}