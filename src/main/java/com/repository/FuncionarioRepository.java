package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Funcionarios;

public interface  FuncionarioRepository extends JpaRepository <Funcionarios ,Long>{

    }