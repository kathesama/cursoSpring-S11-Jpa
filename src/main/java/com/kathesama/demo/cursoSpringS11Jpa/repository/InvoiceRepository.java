package com.kathesama.demo.cursoSpringS11Jpa.repository;

import com.kathesama.demo.cursoSpringS11Jpa.entitiy.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
