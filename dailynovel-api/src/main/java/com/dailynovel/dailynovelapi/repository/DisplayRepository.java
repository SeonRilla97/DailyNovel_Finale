package com.dailynovel.dailynovelapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.DisplayView;

public interface DisplayRepository extends JpaRepository<DisplayView, Integer> {

    // @Query("")
    // List<DisplayView> findAll(int limit);

}
