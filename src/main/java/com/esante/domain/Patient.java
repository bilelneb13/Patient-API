package com.esante.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
public class Patient extends PanacheEntity{
    @Column(nullable = false)
    public String firstName;
    @Column(nullable = false)
    public String lastName;
}
