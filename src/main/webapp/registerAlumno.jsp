<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register Student</title>
</head>
<body>
    <h1>Register Student</h1>
    <form action="SvAlumnos" method="post">
        <input type="hidden" name="action" value="add">
        Carnet: <input type="text" name="carnet" required><br>
        Name: <input type="text" name="name" required><br>
        Surname: <input type="text" name="surname" required><br>
        Date of Birth: <input type="date" name="dob" required><br>
        Address: <input type="text" name="address" required><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>
