package com.dailynovel.dailynovelapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.GuestBookComment;
import com.dailynovel.dailynovelapi.repository.GuestBookCommentRepository;

@Service
public class DefaultGuestBookCommentService implements GuestBookCommentService {

  @Autowired
  private GuestBookCommentRepository repository;

  @Override
  public GuestBookComment viewGuestBookComment() {
    return repository.findByGuestbookId(1);
  }
}
