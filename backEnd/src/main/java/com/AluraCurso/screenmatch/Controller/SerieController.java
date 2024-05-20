/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AluraCurso.screenmatch.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerieController {
    
    @GetMapping("/series")
    public String mostrarMensaje(){
        return "este es mi primer mensaje de la aplicacion";
    }
    
    
}
