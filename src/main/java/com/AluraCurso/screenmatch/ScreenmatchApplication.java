package com.AluraCurso.screenmatch;

import com.AluraCurso.screenmatch.Model.DatosSeries;
import com.AluraCurso.screenmatch.Service.ConsumoAPI;
import com.AluraCurso.screenmatch.Service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}
        
        @Override
        public void run (String... args) throws Exception{
            var consumoApi = new ConsumoAPI();
            var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=kung+fu+panda&apikey=d3e0c101");
            System.out.println(json);
            ConvierteDatos conversor = new ConvierteDatos();
            var datos = conversor.obtenerDatos(json, DatosSeries.class);
            System.out.println(datos);
        }

}
