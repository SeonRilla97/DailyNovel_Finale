package com.dailynovel.dailynovelapi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
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
@Table(name = "diary")
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

//    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
//    private List<DiaryCollection> collections;
}