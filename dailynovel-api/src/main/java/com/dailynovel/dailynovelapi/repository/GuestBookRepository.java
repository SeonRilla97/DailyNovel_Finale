package com.dailynovel.dailynovelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.GuestBook;
import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {
  List<GuestBook> findByMemberId(int memberId);
}
