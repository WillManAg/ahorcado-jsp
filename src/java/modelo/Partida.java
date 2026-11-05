/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author william.marin
 */
import java.util.HashSet;
import java.util.Set;

public class Partida {

    private final String palabra;
    private final Set<Character> letrasUsadas;
    private int errores;

    private static final int MAX_ERRORES = 6;

    public Partida(String palabra) {
        this.palabra = palabra;
        this.letrasUsadas = new HashSet<>();
        this.errores = 0;
    }

    public void probarLetra(char letra) {

        if (isTerminada() || letrasUsadas.contains(letra)) {
            return;
        }

        letrasUsadas.add(letra);

        if (palabra.indexOf(letra) == -1) {
            errores++;
        }
    }

    public boolean estaUsada(char letra) {
        return letrasUsadas.contains(letra);
    }

    public String getPalabraOculta() {

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < palabra.length(); i++) {

            char letra = palabra.charAt(i);

            if (letrasUsadas.contains(letra)) {
                resultado.append(letra);
            } else {
                resultado.append("_");
            }

            resultado.append(" ");
        }

        return resultado.toString();
    }

    public boolean isGanada() {

        for (int i = 0; i < palabra.length(); i++) {

            if (!letrasUsadas.contains(palabra.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean isPerdida() {
        return errores >= MAX_ERRORES;
    }

    public boolean isTerminada() {
        return isGanada() || isPerdida();
    }

    public int getErrores() {
        return errores;
    }

    public int getMaxErrores() {
        return MAX_ERRORES;
    }

    public String getPalabra() {
        return palabra;
    }
}
