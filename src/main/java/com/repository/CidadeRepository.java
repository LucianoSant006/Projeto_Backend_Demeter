package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Cidade;

public interface CidadeRepository extends JpaRepository <Cidade , Long> {
    
}
