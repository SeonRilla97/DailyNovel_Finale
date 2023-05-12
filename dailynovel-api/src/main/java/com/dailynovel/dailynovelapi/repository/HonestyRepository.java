package com.dailynovel.dailynovelapi.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.diary;


public interface HonestyRepository extends JpaRepository<diary, Integer>{
    @Query("SELECT CASE "
    + "WHEN honesty BETWEEN 0 AND 20 THEN '0-20' "
    + "WHEN honesty BETWEEN 21 AND 40 THEN '21-40' "
    + "WHEN honesty BETWEEN 41 AND 60 THEN '41-60' "
    + "WHEN honesty BETWEEN 61 AND 80 THEN '61-80' "
    + "WHEN honesty BETWEEN 81 AND 100 THEN '81-100' "
    + "END AS honesty_range, "
    + "COUNT(*) AS count "
    + "FROM diary "
    + "WHERE memberId = 1 AND honesty IS NOT NULL "
    + "GROUP BY honesty_range "
    + "ORDER BY honesty_range ASC")
    List<diary> findByHonesty(Integer memberId);
}

