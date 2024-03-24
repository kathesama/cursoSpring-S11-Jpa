package com.kathesama.demo.cursoSpringS11Jpa.repository;

import com.kathesama.demo.cursoSpringS11Jpa.entitiy.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {
}
