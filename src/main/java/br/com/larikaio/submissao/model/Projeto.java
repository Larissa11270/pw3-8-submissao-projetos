package br.com.larikaio.submissao.model;

import  jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entidade")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String areaConhecimento;
    private Double notaInovacao;
    private Double notaApresentacao;
    private Double mediaFinal;
    private String status;
}
