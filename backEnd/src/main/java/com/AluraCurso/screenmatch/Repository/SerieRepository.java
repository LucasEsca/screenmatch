/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.AluraCurso.screenmatch.Repository;

import com.AluraCurso.screenmatch.Model.Categoria;
import com.AluraCurso.screenmatch.Model.Episodio;
import com.AluraCurso.screenmatch.Model.Serie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SerieRepository extends JpaRepository<Serie,Long> {
     Optional<Serie> findByTituloContainingIgnoreCase(String nombreSerie);
    List<Serie> findTop5ByOrderByEvaluacionDesc();
    List<Serie> findByGenero(Categoria categoria);
    @Query("select s from Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluacion")
    List<Serie> seriesPorTemporadaYEvaluacion(int totalTemporadas, double evaluacion);
    // List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalTemporadas, Double evaluacion);
    @Query("SELECT s FROM Serie s " + "JOIN s.episodios e " + "GROUP BY s " + "ORDER BY MAX(e.fechaDeLanzamiento) DESC LIMIT 5")
    List<Serie> lanzamientosMasRecientes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numeroTemporada")
    List<Episodio> obtenerTemporadasPorNumero(Long id, Long numeroTemporada);
}
