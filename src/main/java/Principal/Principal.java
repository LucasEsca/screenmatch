/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import com.AluraCurso.screenmatch.Model.DatosEpisodios;
import com.AluraCurso.screenmatch.Model.DatosSeries;
import com.AluraCurso.screenmatch.Model.DatosTemporadas;
import com.AluraCurso.screenmatch.Model.Episodio;
import com.AluraCurso.screenmatch.Service.ConsumoAPI;
import com.AluraCurso.screenmatch.Service.ConvierteDatos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=d3e0c101";
    private ConvierteDatos conversor = new ConvierteDatos();
   
    public void muestraElMenu(){
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar");
        //BUSCA LOS DATOS GENERALES DE LAS SERIES
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, DatosSeries.class);
        System.out.println(datos);
        
         // busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
            for (int i = 1; i  <= datos.totalTemporadas(); i++){
                json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" +i+ API_KEY);
                var datosTemporadas = conversor.obtenerDatos(json,DatosTemporadas.class);
                temporadas.add(datosTemporadas);
            }
            //temporadas.forEach(System.out::println);        
                
            //mostrar solo el titulo para las temporadas
           // for (int l = 0; l< datos.totalTemporadas(); l++){
             //  List<DatosEpisodios> episodiosTemporada = temporadas.get(l).episodios();
              //  for(int j = 0; j< episodiosTemporada.size(); j++){
                //    System.out.println(episodiosTemporada.get(j).titulo());
                
          //} 
    //}
          //  temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
            
            //convertir todas las informaciones a una lista del tipo datos episodios
            List<DatosEpisodios> datosEpisodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream())
                    .collect(Collectors.toList());
            
            //top 5
           System.out.println("Top 5");
           datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodios::evaluacion).reversed())
                   .limit(5)
                   .forEach(System.out::println);
            
            
            //convirtiendo los datos a una lista del tipo Episodio
           List<Episodio> episodios = temporadas.stream()
                   .flatMap(t -> t.episodios().stream()
                    .map(d -> new Episodio( t.numero(),d)))
                   .collect(Collectors.toList());
           
           episodios.forEach(System.out::println);
           
           
           System.out.println("Fecha minima de episodios");
           var fecha =teclado.nextInt();
           teclado.nextLine();
           LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
           
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           episodios.stream()
                   .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                   .forEach(e -> System.out.println(
                        "Temporada " + e.getTemporada()+
                           "Episodio " + e.getTitulo()+
                           "Fecha de Lanzamiento " + e.getFechaDeLanzamiento().format(dtf)
                           ));
    }
    
}
