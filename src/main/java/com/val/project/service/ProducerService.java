package com.val.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.val.project.entity.Producer;
import com.val.project.repository.ProducerRepository;

@Service
public class ProducerService {
  @Autowired
  private ProducerRepository producerRepository;

  public Producer save(Producer producer) {
    return producerRepository.save(producer);
  }

  public Producer findByCnpj(String cnpj) {
    return producerRepository.findByCnpj(cnpj);
  }
}
