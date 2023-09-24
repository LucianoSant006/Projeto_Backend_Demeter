package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.EntradaProduto;
@Repository
public interface EntradaProdutoRepository extends JpaRepository <EntradaProduto, Long> {
    
}
