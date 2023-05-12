package com.dailynovel.dailynovelapi.repository;

import java.util.List;

import org.aspectj.weaver.MemberKind;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.diary;


public interface FeelingRepository extends JpaRepository<diary, Integer>{
    // diary 테이블에서 memberId, feeling 컬럼을 조회하고 feeling별로 카운트한 값을 반환하는 메소드
    @Query("SELECT d.memberId, d.feeling, COUNT(d.feeling) FROM diary d GROUP BY d.feeling")
    List<diary> findByFeeling(Integer memberId);

}