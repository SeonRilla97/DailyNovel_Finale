package com.dailynovel.dailynovelapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Integer>, DiaryRepositoryCustom {


    @Query("SELECT d.feeling, COUNT(d.feeling) FROM Diary d where d.memberId = 1 and d.feeling IS NOT NULL GROUP BY d.feeling")
    List<Object[]> findByFeeling(@Param("memberId") Integer memberId);

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
    + "ORDER BY honestyRange ASC")
    List<Object[]> findByHonesty(Integer memberId);

    @Query("select d.memberId, d.tag, count(tag) from Diary d where d.memberId = 1  group by d.tag")
    List<Object[]> findByTag(Integer memberId);
}
