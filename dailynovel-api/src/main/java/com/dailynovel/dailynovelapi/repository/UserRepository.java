package com.dailynovel.dailynovelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.Member;

public interface UserRepository extends JpaRepository<Member,Integer> {
    
    
}
