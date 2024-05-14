/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import com.AluraCurso.screenmatch.Model.DatosEpisodios;
import com.AluraCurso.screenmatch.Model.DatosSeries;
import com.AluraCurso.screenmatch.Model.DatosTemporadas;
import com.AluraCurso.screenmatch.Service.ConsumoAPI;
import com.AluraCurso.screenmatch.Service.ConvierteDatos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
             //   List<DatosEpisodios> episodiosTemporada = temporadas.get(l).episodios();
               // for(int j = 0; j< episodiosTemporada.size(); j++){
                 //   System.out.println(episodiosTemporada.get(j).titulo());
                
          //} 
    //}
            temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
    
}
