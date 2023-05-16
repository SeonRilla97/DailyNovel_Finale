package com.dailynovel.dailynovelapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.GuestBook;
import com.dailynovel.dailynovelapi.entity.GuestBookComment;
import com.dailynovel.dailynovelapi.service.GuestBookCommentService;
import com.dailynovel.dailynovelapi.service.GuestBookService;

@RestController
@RequestMapping("members/guestbooks")
public class GuestBookController {

  @Autowired
  private GuestBookService service;

  @Autowired
  private GuestBookCommentService commentService;

  @PostMapping("all")
  public ResponseEntity<List<GuestBook>> viewGuestbooks() {
    List<GuestBook> list = service.viewGuestBooks();

    return new ResponseEntity<List<GuestBook>>(list, HttpStatus.OK);
  }

  @PostMapping("comment")
  public ResponseEntity<GuestBookComment> viewGuestbookComment() {
    GuestBookComment comment = commentService.viewGuestBookComment();

    return new ResponseEntity<GuestBookComment>(comment, HttpStatus.OK);
  }
}
