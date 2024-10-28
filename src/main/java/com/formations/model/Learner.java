package com.formations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "learner")
public class Learner {
    @Id
    Long Id;
}
