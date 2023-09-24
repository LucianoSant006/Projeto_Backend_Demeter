package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.EntradaItens;
@Repository
public interface EntradaItensRepository extends JpaRepository <EntradaItens ,Long> {
    
}
