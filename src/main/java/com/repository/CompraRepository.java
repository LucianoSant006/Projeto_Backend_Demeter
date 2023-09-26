
    package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.ItensCompra;

@Repository
public interface CompraRepository extends JpaRepository <ItensCompra, Long>{
    
}

