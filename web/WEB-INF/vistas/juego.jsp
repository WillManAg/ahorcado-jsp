<%-- 
    Document   : juego
    Created on : 3 nov 2026, 13:26:27
    Author     : william.marin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <p>Adivina la palabra</p>
        </header>

        <main>

            <section class="juego">

                <div class="dibujo">
                    <p>Dibujo ? quizás</p>
                </div>

                <div class="palabra">
                    <p>Palabras disponibles: ${totalPalabras}</p>

                    <p class="palabra-oculta">
                        ${sessionScope.partida.palabraOculta}
                    </p>
                </div>

                <p class="errores">
                    Errores: ${sessionScope.partida.errores} / 6
                </p>

                <section class="teclado">
                    <h2>Elige una letra</h2>
                    <p>Podría meter un teclado?</p>
                </section>

            </section>

        </main>

    </body>
</html>
