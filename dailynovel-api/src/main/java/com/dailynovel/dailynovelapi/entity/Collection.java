package com.dailynovel.dailynovelapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Collection {
    @Id
    private int id;
    @Column(name="member_id")
    private int memberId;
    private String name;
    @Column(name="create_date")
    private LocalDate createDate;


}
