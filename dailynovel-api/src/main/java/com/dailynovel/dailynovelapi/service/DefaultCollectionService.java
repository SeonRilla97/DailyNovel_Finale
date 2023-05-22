package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.entity.Collection;
import com.dailynovel.dailynovelapi.mbentity.MbCollection;
import com.dailynovel.dailynovelapi.mbrepository.MbCollectionRepository;
import com.dailynovel.dailynovelapi.repository.CollectionRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultCollectionService implements CollectionService{

    @Autowired
    private CollectionRepository repository;

    @Autowired
    private MbCollectionRepository mbRepository;
    @Override
    public List<MbCollection> listAll(int memberId) {
    List<MbCollection> list = mbRepository.findAllByMemberId(memberId);
//        System.out.println(list);
        for(MbCollection a : list)
            System.out.println(a);
        return list;
    }

    @Transactional
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

    @Override
    public Boolean update(MbCollection collection) {
        // 1.중복검사
        // 새롭게 만들어진 친구가 중복인지 아닌지 확인
        MbCollection dbCheck =mbRepository.findByName(collection);
        System.out.println(dbCheck);
        if(dbCheck != null){
            System.out.println("중복 중복 중복!!!");
            return false;
        }
        Boolean isSuccess=mbRepository.update(collection);
        return isSuccess;
    }

    
    @Override
    public Boolean delete(int collectionId) {
        Boolean isSuccess=mbRepository.delete(collectionId);
        return isSuccess;
    }
}
