/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author william.marin
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Diccionario {
    
    private ArrayList<String> palabras = new ArrayList<>();
    
    public Diccionario(InputStream archivo) throws IOException {
        
        try (BufferedReader lector = new BufferedReader(
        new InputStreamReader(archivo, StandardCharsets.UTF_8))) {
            
            String linea;
            
            while ((linea = lector.readLine()) != null) {
                
                linea = linea.trim();
                
                if (!linea.isEmpty()) {
                    palabras.add(linea);
                }
                
            }
            
        }
        
        if (palabras.isEmpty()) {
            throw new IOException("El diccionario está vacío.");
        }
        
    }
    
    public int getNumeroPalabras() {
        return palabras.size();
    }
    
    public String elegirPalabra() {
        int posicion = (int) (Math.random() * palabras.size());
        
        return palabras.get(posicion);
    }
    
}
