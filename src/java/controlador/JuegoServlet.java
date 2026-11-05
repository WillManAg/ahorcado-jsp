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
import javax.servlet.http.HttpSession;
import modelo.Partida;

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

        HttpSession sesion = request.getSession();

        Partida partida = (Partida) sesion.getAttribute("partida");

        if (partida == null) {

            String palabra = diccionario.elegirPalabra();

            partida = new Partida(palabra);

            sesion.setAttribute("partida", partida);
        }

        request.setAttribute(
                "totalPalabras", diccionario.getNumeroPalabras()
        );

        request.getRequestDispatcher("/WEB-INF/vistas/juego.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession sesion = request.getSession();

        String accion = request.getParameter("accion");

        if ("nueva".equals(accion)) {

            Partida nuevaPartida = new Partida(
                    diccionario.elegirPalabra()
            );

            sesion.setAttribute("partida", nuevaPartida);

        } else {

            Partida partida = (Partida) sesion.getAttribute("partida");

            String valor = request.getParameter("letra");

            if (partida != null && valor != null
                    && valor.length() == 1) {

                char letra = Character.toUpperCase(
                        valor.charAt(0)
                );

                String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

                if (alfabeto.indexOf(letra) >= 0) {

                    partida.probarLetra(letra);
                }
            }
        }

        response.sendRedirect(
                request.getContextPath() + "/jugar"
        );
    }

}
