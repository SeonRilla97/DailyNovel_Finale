package com.dailynovel.dailynovelapi.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.data.domain.Persistable;

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
public class Member implements Persistable {
    @Id
    private long id;
    @Column(name="nickname")
    private String nickName; //무조건 user_name으로 sql으로 들어가짐
    @Column(name="password")
    private String password;
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="created_date")
    private Timestamp timestamp;
    private Date birthday;
    private int gender;
    private String message;
    private String image;
    @Column(name="password_change_period")
    private Date passwordChangePeriod;

    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isNew() {
        return email == null; 
    }

    @Override
    public Long getId() {
        return id;
    }




}
