package com.dailynovel.dailynovelapi.controller;

import com.dailynovel.dailynovelapi.entity.Collection;
import com.dailynovel.dailynovelapi.mbentity.MbCollection;
import com.dailynovel.dailynovelapi.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("collection")
public class CollectionController {
    @Autowired
    private CollectionService service;

    @GetMapping("")
    public List<Collection> listAll(
        @RequestParam(required = true) int memberId
    ){
        List<Collection> list = service.listAll(memberId);
        return list;
    }


    @PostMapping("")
    public Boolean addCollection(
        @RequestBody(required = true) MbCollection collection
    ){
        LocalDateTime curDateTime = LocalDateTime.now();
        collection.setCreateDate(curDateTime);

        System.out.println(curDateTime);
        System.out.println(collection);
        System.out.println("컬렉션의 포스트 함수입니다.");
        Boolean isCuccess=service.add(collection);
        return isCuccess;
    }


}
