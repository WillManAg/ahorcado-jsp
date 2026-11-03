/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author william.marin
 */

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Diccionario;

@WebServlet("/jugar")
public class JuegoServlet extends HttpServlet {

    private Diccionario diccionario;

    @Override
    public void init() throws ServletException {

        InputStream archivo = getServletContext()
                .getResourceAsStream("/WEB-INF/palabras.txt");

        if (archivo == null) {
            throw new ServletException("No se encuentra el archivo con las palabras");
        }

        try {

            diccionario = new Diccionario(archivo);

        } catch (IOException e) {

            throw new ServletException(
                    "Error al cargar Diccionario.java", e
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String palabra = diccionario.elegirPalabra();

        request.setAttribute(
                "totalPalabras", diccionario.getNumeroPalabras()
        );

        request.setAttribute("palabraPrueba", palabra);

        request.getRequestDispatcher("/WEB-INF/vistas/juego.jsp")
                .forward(request, response);
    }
}
