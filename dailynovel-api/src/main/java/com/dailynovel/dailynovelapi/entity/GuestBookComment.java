package com.dailynovel.dailynovelapi.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "guestbook_comment")
public class GuestBookComment {
  @Id
  private int id;

  @Column(name = "guestbook_id")
  private int guestbookId;

  @Column(name = "member_id")
  private int memberId;

  private String content;

  @Column(name = "reg_date")
  private Timestamp regDate;

}
