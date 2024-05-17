/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.AluraCurso.screenmatch.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEpisodios(
        
        @JsonAlias("Title") String titulo,
        
        @JsonAlias("Episode") Integer numeroEpisodio,
        
         @JsonAlias("imbdRating") String evaluacion,
        
        @JsonAlias("Relased") String fechaDeLanzamiento) {

}
