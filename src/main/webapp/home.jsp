<!doctype html>
<html lang="en">

<head>
    <title>T�tulo</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS v5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

</head>

<body>
    <header>
        <!-- place navbar here -->
    </header>
    <main>

        <div class="container mt-5">
            <h2>Formulario</h2>
            <form action="ControladorTest" method="post">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre: </label>
                    <input type="text" class="form-control" id="nombre" placeholder="Inserta tu nombre" required>
                </div>
                <div class="mb-3">
                    <label for="apellido1" class="form-label">Primer apellido: </label>
                    <input type="text" class="form-control" id="apellido1" placeholder="Inserta tu primer apellido"
                        required>
                </div>
                <div class="mb-3">
                    <label for="apellido2" class="form-label">Segundo apellido: </label>
                    <input type="text" class="form-control" id="apellido2" placeholder="Inserta tu segundo apellido"
                        required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Correo electr�nico: </label>
                    <input type="email" class="form-control" id="email" placeholder="Inserta tu correo" required>
                </div>
                <div class="mb-3">
                    <label for="genero" class="form-label">Genero:</label><br>
                    <input type="radio" id="genero-masculino" name="genero" value="Masculino">
                    <label for="genero-masculino">Masculino</label><br>
                    <input type="radio" id="genero-femenino" name="genero" value="Femenino">
                    <label for="genero-femenino">Femenino</label><br>

                </div>
                <div class="mb-3">
                    <label for="ciudad">Ciudad</label>
                    <select name="ciudad" id="ciudad">
                        <option value="Toledo">Toledo</option>
                        <option value="Zaragoza">Zaragoza</option>
                        <option value="Alicante">Alicante</option>
                        <option value="Valencia">Valencia   </option>
                    </select>
                </div>

                <fieldset>
                    <legend>Modulos</legend>
                    <label for="labelleft">
                        <input type="checkbox" name="modulos" id="modulodwes" value="DWES" checked>
                        Desarrollo web en entorno servidor
                    </label>
                    <label for="labelleft">
                        <input type="checkbox" name="modulos" id="modulodwec" value="DWEC" checked>
                        Desarrollo web en entorno cliente
                    </label>
                    <label for="labelleft">
                        <input type="checkbox" name="modulos" id="modulodaw" value="DAW" checked>
                        Desarrollo aplicaciones web
                    </label>
                </fieldset>

                <input type="hidden" name="operacion" value="recuperarDatosUsuario">
                <button type="submit" class="btn btn-primary">Guardar</button>

            </form>
        </div>

    </main>
    <footer>
        <!-- place footer here -->
    </footer>
    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
        </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
        </script>
</body>

</html>