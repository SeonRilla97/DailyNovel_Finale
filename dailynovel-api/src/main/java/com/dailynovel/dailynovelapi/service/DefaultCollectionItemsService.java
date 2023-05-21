package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.mbentity.MbCollectionItems;
import com.dailynovel.dailynovelapi.mbrepository.MbCollectionItemsRepository;

@Service
public class DefaultCollectionItemsService  implements CollectionItemsService {

    @Autowired
    private MbCollectionItemsRepository repository ;

    @Override
    public List<MbCollectionItems> getListById(Integer diaryId, Integer collectionId) {
        List<MbCollectionItems> list = repository.findAllbyId(diaryId,collectionId);

        return list;        
    }
    
}
