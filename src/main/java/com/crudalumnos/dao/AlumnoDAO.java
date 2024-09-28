package com.crudalumnos.dao;

import com.crudalumnos.models.Alumno;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Replace with your database connection details
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    public void registerAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumnos (carnet, nombre, apellidos, fecha_nacimiento, direccion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getCarnet());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getApellidos());

            // Parse String to java.sql.Date
            String fechaNacimientoStr = alumno.getFechaNacimiento(); // Assume it's still a String in the model
            Date fechaNacimiento = parseStringToDate(fechaNacimientoStr);
            pstmt.setDate(4, fechaNacimiento);

            pstmt.setString(5, alumno.getDireccion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAlumno(Alumno alumno) {
        String sql = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, direccion = ? WHERE carnet = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getNombre());
            pstmt.setString(2, alumno.getApellidos());

            // Parse String to java.sql.Date
            String fechaNacimientoStr = alumno.getFechaNacimiento(); // Assume it's still a String in the model
            Date fechaNacimiento = parseStringToDate(fechaNacimientoStr);
            pstmt.setDate(3, fechaNacimiento);

            pstmt.setString(4, alumno.getDireccion());
            pstmt.setString(5, alumno.getCarnet());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to parse date from String
    private Date parseStringToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(sdf.parse(dateStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle parse exception
        }
    }

    public List<Alumno> listAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getString("carnet"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento").toString(), // Keep this as Date
                        rs.getString("direccion")
                );
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    public Alumno getAlumno(String carnet) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumnos WHERE carnet = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carnet);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    alumno = new Alumno(
                            rs.getString("carnet"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getDate("fecha_nacimiento").toString(), // Keep this as Date
                            rs.getString("direccion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }

    public void deleteAlumno(String carnet) {
        String sql = "DELETE FROM alumnos WHERE carnet = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carnet);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
