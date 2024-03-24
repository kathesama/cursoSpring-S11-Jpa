package com.kathesama.demo.cursoSpringS11Jpa.repository;

import com.kathesama.demo.cursoSpringS11Jpa.entitiy.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select c from Client c left join fetch c.addresses where c.id=?1")
    Optional<Client> findOneWithAddresses(Long id);

    @Query("select c from Client c left join fetch c.invoices where c.id=?1")
    Optional<Client> findOneWithInvoices(Long id);
    @Query("select c from Client c join fetch c.addresses")
    Optional<Client> findOne(Long id);

}
