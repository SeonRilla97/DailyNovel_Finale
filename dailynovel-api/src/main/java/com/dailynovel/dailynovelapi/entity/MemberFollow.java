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
@Table(name = "member_follow")
@IdClass(MemberFollowId.class) 
public class MemberFollow {
    @Id
    @Column(name="followed_id")
    private int followedId;
    
    @Id
    @Column(name="follow_id")
    private int followId;
}
