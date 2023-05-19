package com.dailynovel.dailynovelapi.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dailynovel.dailynovelapi.entity.Diary;


public interface DiaryRepository extends JpaRepository<Diary, Integer>{



    @Query("SELECT d.feeling, COUNT(d.feeling) FROM Diary d where d.memberId = 1 and d.feeling IS NOT NULL GROUP BY d.feeling ORDER BY d.feeling ASC" )
    List<Object[]> findByFeeling(@Param("memberId") Integer memberId);
    
    @Query(value = "SELECT * FROM Diary d WHERE d.memberId = :memberId", nativeQuery = true)
    List<Diary> findAllByMId(@Param("memberId") Integer memberId);


    @Query("SELECT CASE "
    + "WHEN honesty BETWEEN 0 AND 20 THEN '0-20' "
    + "WHEN honesty BETWEEN 21 AND 40 THEN '21-40' "
    + "WHEN honesty BETWEEN 41 AND 60 THEN '41-60' "
    + "WHEN honesty BETWEEN 61 AND 80 THEN '61-80' "
    + "WHEN honesty BETWEEN 81 AND 100 THEN '81-100' "
    + "END AS honestyRange, "
    + "COUNT(*) AS count "
    + "FROM Diary d "
    + "WHERE d.memberId = 1 AND d.honesty IS NOT NULL "
    + "GROUP BY honestyRange "
    + "ORDER BY honestyRange DESC")
    List<Object[]> findByHonesty(@Param("memberId") Integer memberId);

    @Query("select d.tag, count(d.tag) from Diary d where d.memberId = 1  group by d.tag")
    List<Object[]> findByTag(@Param("memberId") Integer memberId);
}
