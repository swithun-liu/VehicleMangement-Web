package com.lancer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Entitytest {
    @TableGenerator(name = "entity_test_gene", table = "entity_test", initialValue = 1000)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "entiry_test_gene")
    @Id
    private long id;

    private String name;
}