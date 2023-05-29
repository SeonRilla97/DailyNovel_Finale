package com.dailynovel.dailynovelapi.mbrepository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dailynovel.dailynovelapi.mbentity.MbCollectionItems;

@Mapper
public interface MbCollectionItemsRepository {
    List<MbCollectionItems> findAllbyId(Integer diaryId, Integer collectionId);
    MbCollectionItems findByIds(MbCollectionItems item);
    Boolean delete(MbCollectionItems item);
    Boolean register(MbCollectionItems item);
}
