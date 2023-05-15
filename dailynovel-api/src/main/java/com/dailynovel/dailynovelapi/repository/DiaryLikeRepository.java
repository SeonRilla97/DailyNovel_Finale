package com.dailynovel.dailynovelapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DiaryLikeId;

public interface DiaryLikeRepository extends JpaRepository<DiaryLike, DiaryLikeId> {

}
