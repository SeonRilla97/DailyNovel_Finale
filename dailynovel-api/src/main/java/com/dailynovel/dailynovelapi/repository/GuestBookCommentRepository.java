package com.dailynovel.dailynovelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.GuestBookComment;
import java.util.List;

public interface GuestBookCommentRepository extends JpaRepository<GuestBookComment, Integer> {

  GuestBookComment findByGuestbookId(int guestbookId);
}
