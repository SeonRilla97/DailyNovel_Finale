package com.dailynovel.dailynovelapi.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Query("from Member where email = :email")
    Member findByEmail(String email);
    // email만 들고오는지

    
}
