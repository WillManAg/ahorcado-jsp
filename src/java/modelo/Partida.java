/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author william.marin
 */
public class Partida {

    private final String palabra;
    private int errores;

    public Partida(String palabra) {

        this.palabra = palabra;
        this.errores = 0;
    }

    public String getPalabraOculta() {

        StringBuilder oculta = new StringBuilder();

        for (int i = 0; i < palabra.length(); i++) {

            if (i > 0) {
                oculta.append(" ");
            }

            oculta.append("_");
        }

        return oculta.toString();
    }

    public int getErrores() {
        return errores;
    }
}
