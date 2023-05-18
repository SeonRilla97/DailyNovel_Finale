package com.dailynovel.dailynovelapi.repository;


import java.util.List;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.Member;


public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Member findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.nickname = :nickname")
    Member findByNickName(String nickname);


    Optional<Member> findById(Integer id);

}
