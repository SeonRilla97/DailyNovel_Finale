package com.dailynovel.dailynovelapi.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diary_display")
public class DiaryDisplay {
    @Id
    private int id;
    @Column(name="member_id")
    private int memberId;
    @Column(name="diary_id")
    private int diaryId;
    private Date date;

}
