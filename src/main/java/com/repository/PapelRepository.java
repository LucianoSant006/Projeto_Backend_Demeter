package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Papel;

public interface PapelRepository extends JpaRepository <Papel,Long> {
    
}
