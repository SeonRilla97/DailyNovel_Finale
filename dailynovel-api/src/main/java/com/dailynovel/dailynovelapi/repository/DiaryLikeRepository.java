package com.dailynovel.dailynovelapi.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DiaryLikeId;
import java.util.List;


public interface DiaryLikeRepository extends JpaRepository<DiaryLike, DiaryLikeId> {

    // @Query("")
    List<DiaryLike> findByMemberId(int memberId);

}
