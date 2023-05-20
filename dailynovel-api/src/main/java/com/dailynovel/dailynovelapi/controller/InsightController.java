package com.dailynovel.dailynovelapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.service.DiaryService;


@RestController
@RequestMapping("/insights")
public class InsightController {
    


    // 차트 부분
    @Autowired
     private DiaryService service;

         


     @GetMapping("Feeling")
    public TreeMap<String, Integer> getFList(
        @RequestParam(name = "feeling", required = false) String feeling,
        @RequestParam(name = "count", required = false) Integer count
    ){
        TreeMap<String, Integer> list = service.getByFeeling();
        System.out.println(list);
        return list;
    }

    @GetMapping("Honesty")
    public TreeMap<String, Integer> getHList(
        @RequestParam(name = "honestyRange", required = false) String honestyRange,
        @RequestParam(name = "count", required = false) Integer count
    ){
        TreeMap<String, Integer> list = service.getByHonesty();
        System.out.println(list);
        return list;
    }

    @GetMapping("Tag")
    public TreeMap<String, Integer> getTList(
        @RequestParam(name = "tag", required = false) String tag,
        @RequestParam(name = "count", required = false) Integer count
    ){
        TreeMap<String, Integer> list = service.getByTag();
        System.out.println(list);
        return list;
    }


    @GetMapping("pino")
    public List<Object> pino(
        @RequestParam(name = "pino", required = false) String pino,
        @RequestParam(name = "count", required = false) Integer count
    ){
        List<Object> list = service.getBypino();
        return list;

    }

    @GetMapping("Npino")
    public List<Object> Npino(
        @RequestParam(name = "Npino", required = false) String Npino,
        @RequestParam(name = "count", required = false) Integer count
    ){
        List<Object> list = service.getByNpino();
        return list;

    }

 }
