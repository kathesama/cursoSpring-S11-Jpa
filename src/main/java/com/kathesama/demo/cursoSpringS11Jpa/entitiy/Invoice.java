package com.kathesama.demo.cursoSpringS11Jpa.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id") // si tiene la configuración por default no hace falta, acá lo dejamos
    private Client client;

    private String description;
    private Double total;

    @CreationTimestamp
    @Column(name = "create_at")
    private Date createAt; // Se generará automáticamente al crear la entidad

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt; // Se actualizará automáticamente al modificar la entidad

    public Invoice(String description, Double total) {
        this.description = description;
        this.total = total;
    }
}
