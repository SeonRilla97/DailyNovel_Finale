package com.dailynovel.dailynovelapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.MemberFollow;
import com.dailynovel.dailynovelapi.entity.MemberFollowId;


public interface MemberFollowRepository extends JpaRepository<MemberFollow, MemberFollowId> {
    

    List<MemberFollow> findByFollowId(int followId);

    List<MemberFollow> findByFollowedId(int followedId);
}