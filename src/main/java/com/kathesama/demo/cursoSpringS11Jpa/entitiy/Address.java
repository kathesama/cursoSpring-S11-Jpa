package com.kathesama.demo.cursoSpringS11Jpa.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer number;

    @CreationTimestamp
    @Column(name = "create_at")
    private Date createAt; // Se generará automáticamente al crear la entidad

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt; // Se actualizará automáticamente al modificar la entidad

    public Address(String street, Integer number) {
        this.street = street;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
