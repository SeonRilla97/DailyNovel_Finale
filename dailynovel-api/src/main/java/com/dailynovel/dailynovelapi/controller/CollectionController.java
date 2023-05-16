package com.dailynovel.dailynovelapi.controller;

import com.dailynovel.dailynovelapi.entity.Collection;
import com.dailynovel.dailynovelapi.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("collection")
public class CollectionController {
    @Autowired
    private CollectionService service;

    @GetMapping("all")
    public List<Collection> listAll(){
        List<Collection> list = service.listAll();
        return list;
    }


}
