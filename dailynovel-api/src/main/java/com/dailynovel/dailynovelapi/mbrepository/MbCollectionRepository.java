package com.dailynovel.dailynovelapi.mbrepository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dailynovel.dailynovelapi.mbentity.MbCollection;

@Mapper
public interface MbCollectionRepository {
    public Boolean insert(MbCollection Collection);

    public MbCollection findByName(MbCollection collection);

    public List<MbCollection> findAllByMemberId(int memberId);

    public Boolean update(MbCollection collection);
}
