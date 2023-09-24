package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Produto;
@Repository
public interface ProdutosRepository extends JpaRepository <Produto, Long>{

}