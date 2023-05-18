package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.entity.Collection;
import com.dailynovel.dailynovelapi.mbentity.MbCollection;
import com.dailynovel.dailynovelapi.mbrepository.MbCollectionRepository;
import com.dailynovel.dailynovelapi.repository.CollectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCollectionService implements CollectionService{

    @Autowired
    private CollectionRepository repository;

    @Autowired
    private MbCollectionRepository mbRepository;
    @Override
    public List<Collection> listAll(int memberId) {
    List<Collection> list = repository.findAllByMemberId(memberId);
//        System.out.println(list);
        for(Collection a : list)
            System.out.println(a);
        return list;
    }
    @Override
    public Boolean add(MbCollection collection) {
        // 1.중복검사
        MbCollection dbCheck =mbRepository.findByName(collection);
        System.out.println(dbCheck);
        if(dbCheck != null){
            System.out.println("중복 중복 중복!!!");
            return false;
        }
        Boolean isSuccess=mbRepository.insert(collection);
        // 2.등록
        return isSuccess;
    }
}
