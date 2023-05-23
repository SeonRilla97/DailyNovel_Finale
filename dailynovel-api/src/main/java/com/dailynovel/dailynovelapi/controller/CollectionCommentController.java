package com.dailynovel.dailynovelapi.controller;

import java.util.List;

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

import com.dailynovel.dailynovelapi.mbentity.MbCCommentMemberView;
import com.dailynovel.dailynovelapi.mbentity.MbCollectionComment;
import com.dailynovel.dailynovelapi.service.CollectionCommentService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("collection/comment")
public class CollectionCommentController {
    
    @Autowired
    private CollectionCommentService service;

    @GetMapping("")
    public List<MbCCommentMemberView> getList(
        @RequestParam(required=true) int collectionId,
        @RequestParam(required=false) Integer depth,
        @RequestParam(required=false) Integer refId
    ){
        // 새로운 배열 만들고
        MbCCommentMemberView comment =  new MbCCommentMemberView();
        comment.setCollectionId(collectionId);
        if(depth !=null) comment.setDepth(depth);
        else comment.setDepth(0);
        if(refId !=null) comment.setRefId(refId);
        
        List<MbCCommentMemberView> list = service.getList(comment);
        return list;
    }
    
    @PutMapping("")
    public Boolean update(
        @RequestBody MbCollectionComment comment
    ){
        Boolean isSuccess = service.update(comment);
        return isSuccess;
    }

    @DeleteMapping("{commentId}")
    public Boolean delete(
        @PathVariable("commentId") int commentId
    ){
        System.out.println(commentId);
        Boolean isSuccess = service.delete(commentId);
        return isSuccess;
    }

    @PostMapping("")
    public Boolean register(
        @RequestBody MbCollectionComment comment
    ){
        Boolean isSuccess = service.register(comment);
        return isSuccess;
    }
}

