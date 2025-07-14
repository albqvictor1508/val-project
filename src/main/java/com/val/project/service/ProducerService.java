package com.val.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.val.project.repository.ProducerRepository;

@Service
public class ProducerService {
  @Autowired
  private ProducerRepository producerRepository;
}
