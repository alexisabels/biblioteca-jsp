<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">



    <head>
        <title>Biblioteca</title>
        <!-- Required meta tags -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS v5.2.1 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <style>
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            body {
                background-image: url(https://i.pinimg.com/736x/9a/d0/74/9ad0744eceb927550b7eea7b705b587d.jpg);
                background-repeat: no-repeat;
                background-size: cover;
            }

            header {
                background-color: #343a40;
                color: #ffffff;
                padding: 20px 0;

            }

            main {
                margin-top: 50px;
            }

            .card {
                border: none;
                transition: transform 0.2s;
            }

            .card:hover {
                transform: scale(1.02);
            }

            form {
                max-width: 400px;
                margin: auto;
            }

            label {
                font-weight: bold;
                color: #495057;
            }

            button {
                width: 100%;
            }

            .rojo {
               border-color: red;
            }
        </style>
    </head>

    <body>

        <header class="py-4 d-flex justify-content-between align-items-center">
            <div class="d-flex" style="width: 33%;">
                <a href="/biblioteca/Prestamo.jsp" class="me-2 ms-2"><button type="button"
                        class="btn btn-primary">Prestar</button></a>
                <a href="/biblioteca/Devolucion.jsp"><button type="button" class="btn btn-success">Devolver</button></a>
            </div>
            <div class="text-center" style="width: 33%;">
                <h1 class="text-light">Biblioteca</h1>
            </div>
            <div class="" style="width: 33%;"></div>
        </header>



        <main class="container w-50">
            <div class="row">
                <!-- Prestar -->
                <div class="col-md-12">
                    <div class="card h-100 shadow">
                        <div class="card-body">
                            <h3 class="card-title text-center mb-4">Prestar</h3>
                            <form action="ControladorTest" method="post">
                                <c:choose>
                                    <c:when test="${not empty resultadoError}">
                                        <div class="mb-3">
                                            <label for="idSocio" class="form-label">ID Socio:</label>
                                            <input type="number" class="form-control rojo" id="idSocio" name="idSocio"
                                                placeholder="Inserta tu numero de identificacion de socio." required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="idEjemplar" class="form-label">ID Ejemplar:</label>
                                            <input type="number" class="form-control rojo" id="idEjemplar" name="idEjemplar"
                                                placeholder="Inserta el numero de identificacion del ejemplar." required>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="mb-3">
                                            <label for="idSocio" class="form-label">ID Socio:</label>
                                            <input type="number" class="form-control" id="idSocio" name="idSocio"
                                                placeholder="Inserta tu numero de identificacion de socio." required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="idEjemplar" class="form-label">ID Ejemplar:</label>
                                            <input type="number" class="form-control" id="idEjemplar" name="idEjemplar"
                                                placeholder="Inserta el numero de identificacion del ejemplar." required>
                                        </div>
                                    </c:otherwise>
                                </c:choose>


                                <c:if test="${not empty resultadoError}">
                                    <div class="alert alert-danger" role="alert">
                                        Error en la solicitud de prestamo. <br>
                                     <a href="" data-bs-toggle="modal" data-bs-target="#modalId">Haz click aqui para mas detalles.</a>   
                                    </div>
                                    
                                </c:if>

                                <c:if test="${not empty resultado}">
                                    <div class="alert alert-success" role="alert">
                                        Prestado correctamente
                                    </div>
                                </c:if>

                                <input type="hidden" name="operacion" value="prestarLibro">
                                <button type="submit" class="btn btn-primary">Prestar</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!--MODAL-->
                <div class="modal fade" id="modalId" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false"
                    role="dialog" aria-labelledby="modalTitleId" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalTitleId">Error en la solicitud</h5>
                            </div>
                            <!--ESTO SI DA ERROR-->
                            <div class="modal-body">
                                <c:if test="${not empty resultadoError}">
                                    <div class="alert alert-danger mb-0" role="alert">
                                        <c:out value="${resultadoError}"></c:out>
                                    </div>
                                </c:if>
                                <!--ESTO SI VA BIEN--> 
                                <c:if test="${not empty resultado}">
                                    <div class="alert alert-success" role="alert">
                                        <c:out value="${resultado}"></c:out>
                                    </div>
                                </c:if>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Optional: Place to the bottom of scripts -->
                <script>
                    const myModal = new bootstrap.Modal(document.getElementById('modalId'), options)

                </script>
            </div>
        </main>

        <!-- Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous"></script>




    </body>

    </html>