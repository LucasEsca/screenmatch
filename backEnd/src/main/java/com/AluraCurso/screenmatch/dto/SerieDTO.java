/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.AluraCurso.screenmatch.dto;

import com.AluraCurso.screenmatch.Model.Categoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public record SerieDTO(Long id,
         String titulo,
         Integer totalTemporadas,
         Double evaluacion,
         String poster,
         Categoria genero,
         String actores,
         String sinopsis) {

}
