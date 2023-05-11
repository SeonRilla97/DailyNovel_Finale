package com.dailynovel.dailynovelapi.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Diary {
    @Id
    private int id;
    @Column(name="member_id")
    private int memberId;
    private String title;
    private String content;
    private String weather;
    private String feeling;
    private String honesty;
    private String tag;
    @Column(name="reg_date")
    private Date date;
}
