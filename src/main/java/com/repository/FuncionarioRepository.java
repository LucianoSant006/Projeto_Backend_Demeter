package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Funcionario;

public interface  FuncionarioRepository extends JpaRepository <Funcionario ,Long>{

    }