<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.crudalumnos.dao.AlumnoDAO"%>
<%@page import="com.crudalumnos.models.Alumno"%>
<%@page import="com.crudalumnos.dao.MateriaDAO"%>
<%@page import="com.crudalumnos.models.Materia"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Estudiantes</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            h1, h2 {
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:hover {
                background-color: #f5f5f5;
            }
            a {
                text-decoration: none;
                color: #4CAF50;
            }
            a:hover {
                text-decoration: underline;
            }
            form {
                margin-bottom: 20px;
            }
            input[type="text"], input[type="date"], input[type="submit"] {
                padding: 8px;
                margin: 5px 0;
                width: 100%;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .button {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 15px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                margin: 4px 2px;
                cursor: pointer;
            }
            .button-edit {
                background-color: #008CBA; /* Azul */
            }
            .button-delete {
                background-color: #f44336; /* Rojo */
            }
            .button-edit:hover {
                background-color: #007bb5;
            }
            .button-delete:hover {
                background-color: #d32f2f;
            }
            textarea {
                width: 100%; /* Full width */
                height: 150px; /* Set a fixed height */
                padding: 10px; /* Add some padding */
                border: 1px solid #ccc; /* Light gray border */
                border-radius: 4px; /* Rounded corners */
                font-size: 16px; /* Font size */
                font-family: Arial, sans-serif; /* Font family */
                resize: vertical; /* Allow vertical resizing only */
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
                transition: border-color 0.3s; /* Smooth transition for border color */
            }

            textarea:focus {
                border-color: #007bff; /* Change border color on focus */
                outline: none; /* Remove default outline */
            }

        </style>
    </head>
    <body>
        <h1>Lista de Estudiantes</h1>
        <a href="index.jsp">Actualizar</a>
        <a href="materias.jsp">Inscripciones a Materias</a>


        <!-- Formulario de Registro -->
        <h2>Registrar Nuevo Estudiante</h2>
        <form action="SvAlumnos" method="post">
            <input type="hidden" name="action" value="add">
            Carnet: <input type="text" name="carnet" required><br>
            Nombre: <input type="text" name="name" required><br>
            Apellido: <input type="text" name="surname" required><br>
            Fecha de Nacimiento: <input type="date" name="dob" required><br>
            Dirección: <input type="text" name="address" required><br>
            <input type="submit" value="Registrar">
        </form>

        <h2>Estudiantes Existentes</h2>
        <table>
            <tr>
                <th>Carnet</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Fecha de Nacimiento</th>
                <th>Dirección</th>
                <th>Acciones</th>
            </tr>
            <%
                // Inicializar DAO y lista de estudiantes
                AlumnoDAO alumnoDAO = new AlumnoDAO();
                List<Alumno> studentList = alumnoDAO.listAlumnos();
                for (Alumno alumno : studentList) {
            %>
            <tr>
                <td><%= alumno.getCarnet()%></td>
                <td><%= alumno.getNombre()%></td>
                <td><%= alumno.getApellidos()%></td>
                <td><%= alumno.getFechaNacimiento()%></td>
                <td><%= alumno.getDireccion()%></td>
                <td>
                    <a class="button button-edit" href="SvAlumnos?action=edit&carnet=<%= alumno.getCarnet()%>">Editar</a>
                    <a class="button button-delete" href="SvAlumnos?action=delete&carnet=<%= alumno.getCarnet()%>">Eliminar</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>


        <h1>Lista de Materias</h1>
        <a href="index.jsp">Actualizar</a>

        <h2>Registrar Nueva Materia</h2>
        <form action="SvMaterias" method="post">
            <input type="hidden" name="action" value="register">
            Código Materia: <input type="text" name="codigoMateria" required><br>
            Nombre: <input type="text" name="nombre" required><br>
            Descripción: <textarea name="descripcion"></textarea><br>
            Fecha de Creación: <input type="date" name="fechaCreacion" required><br>
            <input type="submit" value="Registrar">
        </form>

        <h2>Materias Existentes</h2>

        <table border="1">
            <tr>
                <th>Código Materia</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Fecha de Creación</th>
                <th>Acciones</th>
            </tr>
            <%
                // Instancia del DAO y obtención de la lista de materias
                MateriaDAO materiaDAO = new MateriaDAO();
                List<Materia> materiaList = materiaDAO.listMaterias();

                // Iterar sobre la lista y mostrar los datos en la tabla
                for (Materia materia : materiaList) {
            %>
            <tr>
                <td><%= materia.getCodigoMateria()%></td>
                <td><%= materia.getNombre()%></td>
                <td><%= materia.getDescripcion()%></td>
                <td><%= materia.getFechaCreacion()%></td>
                <td>
                    <a href="SvMaterias?action=edit&codigoMateria=<%= materia.getCodigoMateria()%>">Editar</a>
                    <a href="SvMaterias?action=delete&codigoMateria=<%= materia.getCodigoMateria()%>">Eliminar</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
