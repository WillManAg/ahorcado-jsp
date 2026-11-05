<%-- 
    Document   : juego
    Created on : 3 nov 2026, 13:26:27
    Author     : william.marin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Partida"%>

<%
    Partida partida
            = (Partida) session.getAttribute("partida");
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>El Ahorcado</title>

        <link rel="stylesheet"
              href="<%= request.getContextPath()%>/css/estilos.css">
    </head>

    <body>

        <header>
            <h1>El Ahorcado</h1>
            <p>Adivina la palabra antes de 6 trys.</p>
        </header>

        <main>

            <section class="juego">

                <div class="dibujo">

                    <svg viewBox="0 0 220 240"
                         role="img"
                         aria-label="Dibujo del ahorcado">

                    <line x1="20" y1="228"
                          x2="180" y2="228"/>

                    <line x1="60" y1="228"
                          x2="60" y2="20"/>

                    <line x1="60" y1="20"
                          x2="145" y2="20"/>

                    <line x1="145" y1="20"
                          x2="145" y2="47"/>

                    <circle cx="145" cy="69" r="22"
                            class="parte ${sessionScope.partida.errores ge 1 ? 'visible' : ''}"/>

                    <line x1="145" y1="91"
                          x2="145" y2="155"
                          class="parte ${sessionScope.partida.errores ge 2 ? 'visible' : ''}"/>

                    <line x1="145" y1="113"
                          x2="113" y2="132"
                          class="parte ${sessionScope.partida.errores ge 3 ? 'visible' : ''}"/>

                    <line x1="145" y1="113"
                          x2="177" y2="132"
                          class="parte ${sessionScope.partida.errores ge 4 ? 'visible' : ''}"/>

                    <line x1="145" y1="155"
                          x2="115" y2="198"
                          class="parte ${sessionScope.partida.errores ge 5 ? 'visible' : ''}"/>

                    <line x1="145" y1="155"
                          x2="175" y2="198"
                          class="parte ${sessionScope.partida.errores ge 6 ? 'visible' : ''}"/>

                    </svg>

                </div>

                <div class="palabra">

                    <p>Palabras disponibles: ${totalPalabras}</p>

                    <p class="palabra-oculta">
                        ${sessionScope.partida.palabraOculta}
                    </p>

                </div>

                <p class="errores">
                    Errores: ${sessionScope.partida.errores}
                    / ${sessionScope.partida.maxErrores}
                </p>

                <% if (partida.isGanada()) { %>

                <p class="resultado ganado">
                    Ganaste prr!!!.
                </p>

                <% } else if (partida.isPerdida()) {%>

                <p class="resultado perdido">
                    Perdiste pndj!!
                    La palabra era:
                    <strong><%= partida.getPalabra()%></strong>
                </p>

                <% } else {%>

                <form action="<%= request.getContextPath()%>/jugar"
                      method="post"
                      class="teclado">

                    <h2>Elige una letra</h2>

                    <div class="letras">

                        <%
                            String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

                            for (char letra : alfabeto.toCharArray()) {

                                boolean usada = partida.estaUsada(letra);
                        %>

                        <button type="submit"
                                name="letra"
                                value="<%= letra%>"
                                class="tecla"
                                <%= usada ? "disabled" : ""%>>

                            <%= letra%>

                        </button>

                        <% } %>

                    </div>

                </form>

                <% }%>

                <form action="<%= request.getContextPath()%>/jugar"
                      method="post"
                      class="nueva-partida">

                    <input type="hidden"
                           name="accion"
                           value="nueva">

                    <button type="submit">
                        Nueva partida
                    </button>

                </form>

            </section>

        </main>

    </body>
</html>
