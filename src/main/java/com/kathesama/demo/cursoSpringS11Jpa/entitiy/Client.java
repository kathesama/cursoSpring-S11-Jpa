package com.kathesama.demo.cursoSpringS11Jpa.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_id")
    private List<Address> addresses; // Inicialización de la lista addresses
//    // en caso que se quiera vincular a una tabla intermedia cuando la relacion sea muchos a muchos: 3era forma normal
//    @JoinTable(
//            name = "tabla_intermedia_de_ejemplo",
//            joinColumns = @JoinColumn(name="id_cliente"), //one to many
//            inverseJoinColumns = @JoinColumn(name="id_direcciones"), // many to one
//            uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"})
//    )
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="client")
    private Set<Invoice> invoices;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private ClientDetails clientDetails;


    @CreationTimestamp
    @Column(name = "create_at")
    private Date createAt; // Se generará automáticamente al crear la entidad

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt; // Se actualizará automáticamente al modificar la entidad

    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
        this.addresses = new ArrayList<>(); // Inicialización de la lista addresses
        this.invoices = new HashSet<>(); // Inicialización de la lista addresses
    }

    public Client addInvoice(Invoice invoice) {
        invoices.add(invoice);
        invoice.setClient(this);
        return this;
    }
}
