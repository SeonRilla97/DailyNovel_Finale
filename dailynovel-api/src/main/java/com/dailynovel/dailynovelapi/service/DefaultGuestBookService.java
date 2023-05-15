package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.GuestBook;
import com.dailynovel.dailynovelapi.entity.GuestBookComment;
import com.dailynovel.dailynovelapi.repository.GuestBookRepository;

@Service
public class DefaultGuestBookService implements GuestBookService {

  @Autowired
  private GuestBookRepository repository;

  @Override
  public List<GuestBook> viewGuestBook() {
    List<GuestBook> list = repository.findByMemberId(1);

    return list;
  }

  @Override
  public GuestBookComment writeGuestBookComment() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'writeGuestBookComment'");
  }

  @Override
  public GuestBook writeGuestBook() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'writeGuestBook'");
  }

}
