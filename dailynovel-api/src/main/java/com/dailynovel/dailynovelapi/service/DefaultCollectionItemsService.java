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

    @Override
    public List<MbCollectionItems> register(MbCollectionItems item) {
    //    1.있는지 확인
        MbCollectionItems curItem =  repository.findByIds(item);
        System.out.println(curItem);
        if(curItem !=null)
            repository.delete(item); // 1-1 있다 -> 삭제
        else
            repository.register(item); // 1-2 없다 -> 추가
        // 2.리스트 불러오기
        List<MbCollectionItems> list = repository.findAllbyId(item.getDiaryId(),null);
        System.out.println(list);
        return list;

    }
}
