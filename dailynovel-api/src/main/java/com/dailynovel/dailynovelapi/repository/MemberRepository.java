package com.dailynovel.dailynovelapi.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.dailynovel.dailynovelapi.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Query("from Member where UserName = :username")
    Member findByUserName(@Param("username") String username);
}
