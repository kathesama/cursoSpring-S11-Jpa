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
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String description;
    private Double total;

    @CreationTimestamp
    @Column(name = "create_at")
    private Date createAt; // Se generará automáticamente al crear la entidad

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt; // Se actualizará automáticamente al modificar la entidad
}
