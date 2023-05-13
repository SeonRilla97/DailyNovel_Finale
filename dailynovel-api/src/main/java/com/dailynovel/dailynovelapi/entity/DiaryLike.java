package com.dailynovel.dailynovelapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "diary_like")
public class DiaryLike {
    @Column(name="member_id")
    private int memberId;
    @Column(name="diary_id")
    private int diaryId;
    
}
