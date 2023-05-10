package com.dailynovel.dailynovelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailynovel.dailynovelapi.entity.Feeling;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public interface FeelingRepository extends JpaRepository<Feeling, Integer>{

}

