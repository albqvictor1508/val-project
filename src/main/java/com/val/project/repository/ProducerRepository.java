package com.val.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.val.project.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
  public Producer findByCnpj(String cnpj);
}
