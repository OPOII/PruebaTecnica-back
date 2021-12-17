package com.prueba.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Cadena")
public class Cadena {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String input;
    private String output;
    private int repeats;


}
