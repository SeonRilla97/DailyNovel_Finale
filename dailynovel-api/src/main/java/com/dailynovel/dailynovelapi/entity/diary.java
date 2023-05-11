package com.dailynovel.dailynovelapi.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class diary {
    @Id
    private int id;
    @Column(name="member_id")
    private int memberId;
    private String title;
    private String content;
    private String weather;
    private String feelString;
    private String honesty;
    private String teg;
    private int diaryId;
    private Date date;
}
