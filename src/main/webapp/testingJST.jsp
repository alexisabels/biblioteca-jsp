<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>Biblioteca</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS v5.2.1 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    </head>

    <body>
        <header class="text-center py-4">
            <h1 class="text-light">Biblioteca</h1>
        </header>

        <main class="container">
            <h2>JSTL</h2>

            <form action="Controller" method="post">
            <c:if test="${escribirCadena}">
                <c:out value="${cadena}"></c:out>

            </c:if>


            <input type="hidden" name="operacion" value="prestarLibro">
            <button type="submit" class="btn btn-primary">Prestar</button>
         
            <c:if test="${not empty socio}">
                <label for="idSocio" class="form-label">ID Socio: ${socio.idSocio}</label><br>
                <label for="nombre" class="form-label">Nombre: ${socio.nombre}</label><br>
                <label for="email" class="form-label">Email: ${socio.email}</label><br>
                <label for="direccion" class="form-label">Direccion: ${socio.direccion}</label><br>

            </c:if>
        </form>
            

        </main>

        <!-- Bootstrap JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous"></script>
    </body>

    </html>