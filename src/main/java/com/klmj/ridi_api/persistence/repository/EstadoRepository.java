package com.klmj.ridi_api.persistence.repository;

import com.klmj.ridi_api.persistence.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}