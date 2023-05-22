package com.dailynovel.dailynovelapi.controller;

import com.dailynovel.dailynovelapi.entity.Collection;
import com.dailynovel.dailynovelapi.mbentity.MbCollection;
import com.dailynovel.dailynovelapi.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<MbCollection> listAll(
        @RequestParam(required = true) int memberId
    ){
        List<MbCollection> list = service.listAll(memberId);
        return list;
    }


    @PostMapping("")
    public Boolean addCollection(
        @RequestBody(required = true) MbCollection collection
    ){
        LocalDateTime curDateTime = LocalDateTime.now();
        collection.setCreateDate(curDateTime);
        Boolean isCuccess=service.add(collection);
        return isCuccess;
    }
    @PutMapping("")
    public Boolean update(
        @RequestBody(required = true) MbCollection collection
        // @RequestBody(required = true) String alterName
    ){
        System.out.println("요청은 해!");
        System.out.println(collection);
        // System.out.println(collection);
        Boolean isCuccess=service.update(collection);
        // return isCuccess;
        return isCuccess;
    }
    @DeleteMapping("{id}")
    public  Boolean delete(
        @PathVariable("id") int collectionId
    ){
        System.out.println("hi 여기는 delete");
        Boolean isCuccess=service.delete(collectionId);
        return isCuccess;
    }

}
