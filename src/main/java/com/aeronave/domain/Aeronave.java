package com.aeronave.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Aeronave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private int ano;
    private String descricao;
    private boolean vendido;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created", updatable=false, columnDefinition = "datetime", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "datetime")
    @UpdateTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated;

}
