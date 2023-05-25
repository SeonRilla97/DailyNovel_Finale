package com.dailynovel.dailynovelapi.mbrepository;

import org.apache.ibatis.annotations.Mapper;

import com.dailynovel.dailynovelapi.mbentity.MbMember;

/**
 * MbMemberRepository
 */
@Mapper
public interface MbMemberRepository {
    public MbMember findAllbyId(int memberId);

    public void updateProfileImage(String newFilename, int id);

    
}