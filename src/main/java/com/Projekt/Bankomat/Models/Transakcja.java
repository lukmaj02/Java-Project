package com.Projekt.Bankomat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transakcja {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idTransakcji")
    private UUID idTransakcji;
    private Integer liczba;
}
