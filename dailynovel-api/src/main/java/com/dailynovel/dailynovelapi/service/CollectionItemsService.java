package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.mbentity.MbCollectionItems;

public interface CollectionItemsService {

    public List<MbCollectionItems> getListById(Integer diaryId, Integer collectionId);

    public List<MbCollectionItems> register(MbCollectionItems item);
}
