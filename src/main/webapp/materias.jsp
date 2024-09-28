<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.crudalumnos.dao.InscripcionDAO" %>
<%@ page import="java.util.List" %>
<%
    InscripcionDAO inscripcionDAO = new InscripcionDAO();

    // Contar estudiantes inscritos por materia
    int totalInscritos = 0;
    String codigoMateria = request.getParameter("codigoMateria");
    if (codigoMateria != null) {
        totalInscritos = inscripcionDAO.contarEstudiantesPorMateria(codigoMateria);
    }

    // Obtener lista de estudiantes inscritos en la materia
    List<String> estudiantes = null;
    if (codigoMateria != null) {
        estudiantes = inscripcionDAO.listarEstudiantesPorMateria(codigoMateria);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscripciones</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h1, h2 {
            color: #333;
        }
        form {
            background: #fff;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #4cae4c;
        }
        .success {
            color: green;
            margin-bottom: 20px;
        }
        ul {
            padding-left: 20px;
        }
    </style>
</head>
<body>
    <h1>Inscripciones de Estudiantes</h1>
    <a href="index.jsp">Volver</a>

    <!-- Mostrar total de estudiantes inscritos en la materia -->
    <h2>Total de Estudiantes Inscritos</h2>
    <form action="materias.jsp" method="get">
        <label for="codigoMateria">Código de la Materia:</label>
        <input type="text" name="codigoMateria" required>
        <input type="submit" value="Contar">
    </form>
    <p>Total de estudiantes inscritos: <%= totalInscritos %></p>

    <!-- Mostrar lista de estudiantes registrados en la materia -->
    <h2>Lista de Estudiantes Registrados</h2>
    <form action="materias.jsp" method="get">
        <label for="codigoMateria">Código de la Materia:</label>
        <input type="text" name="codigoMateria" required>
        <input type="submit" value="Ver Lista">
    </form>
    <ul>
        <%
        if (estudiantes != null && !estudiantes.isEmpty()) {
            for (String carnet : estudiantes) {
        %>
            <li><%= carnet %></li>
        <%
            }
        } else {
        %>
            <li>No hay estudiantes registrados en esta materia.</li>
        <%
        }
        %>
    </ul>

    <!-- Formulario para registrar estudiante a materia -->
    <h2>Registrar Estudiante a Materia</h2>
    <form action="SvInscripciones" method="post">
        <input type="hidden" name="action" value="registrar">
        <label for="carnetAlumno">Carnet del Estudiante:</label>
        <input type="text" name="carnetAlumno" required><br>
        <label for="codigoMateria">Código de la Materia:</label>
        <input type="text" name="codigoMateria" required><br>
        <input type="submit" value="Registrar">
    </form>

    <!-- Formulario para eliminar estudiante de materia -->
    <h2>Eliminar Estudiante de Materia</h2>
    <form action="SvInscripciones" method="post">
        <input type="hidden" name="action" value="eliminar">
        <label for="carnetAlumno">Carnet del Estudiante:</label>
        <input type="text" name="carnetAlumno" required><br>
        <label for="codigoMateria">Código de la Materia:</label>
        <input type="text" name="codigoMateria" required><br>
        <input type="submit" value="Eliminar">
    </form>
</body>
</html>
