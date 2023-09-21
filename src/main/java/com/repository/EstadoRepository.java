package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Estado;

public interface EstadoRepository extends  JpaRepository <Estado, Long>{
    
}
