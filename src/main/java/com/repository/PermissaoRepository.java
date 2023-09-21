package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Permissao;

public interface PermissaoRepository extends JpaRepository <Permissao , Long> {
    
}
