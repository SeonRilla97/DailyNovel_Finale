package com.dailynovel.dailynovelapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(DiaryLikeId.class) 
public class DiaryLike {
    @Id
    @Column(name="member_id")
    private int memberId;
    
    @Id
    @Column(name="diary_id")
    private int diaryId;
    
}
