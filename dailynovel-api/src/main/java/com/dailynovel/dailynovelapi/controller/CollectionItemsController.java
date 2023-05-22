package com.dailynovel.dailynovelapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.mbentity.MbCollectionItems;
import com.dailynovel.dailynovelapi.service.CollectionItemsService;

@RestController
@RequestMapping("collection/items")
public class CollectionItemsController {
    
    @Autowired
    private CollectionItemsService service;

    // 조회
    @GetMapping("")
    public List<MbCollectionItems> getListById(
        @RequestParam(required=false) Integer diaryId,
        @RequestParam(required=false) Integer collectionId
    ){
        System.out.println("이거실행됨?");
        List<MbCollectionItems> list = service.getListById(diaryId,collectionId);
        System.out.println(list);
        return list;
    }

    @PostMapping("")
    public List<MbCollectionItems> addList(
        @RequestBody MbCollectionItems item
    ){
        //일기를 컬렉션에 등록한다.
        List<MbCollectionItems> list = service.register(item);
        return list;
    }
}
