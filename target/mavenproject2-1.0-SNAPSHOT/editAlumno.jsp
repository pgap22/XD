<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Student</title>
</head>
<body>
    <h1>Edit Student</h1>
    <form action="SvAlumnos" method="post">
        <input type="hidden" name="action" value="update">
        Carnet: <input type="text" name="carnet" value="${alumno.carnet}" readonly><br>
        Name: <input type="text" name="name" value="${alumno.nombre}" required><br>
        Surname: <input type="text" name="surname" value="${alumno.apellidos}" required><br>
        Date of Birth: <input type="date" name="dob" value="${alumno.fechaNacimiento}" required><br>
        Address: <input type="text" name="address" value="${alumno.direccion}" required><br>
        <input type="submit" value="Update">
    </form>
    <br>
    <a href="SvAlumnos">Cancel</a>
</body>
</html>
