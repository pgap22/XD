package com.crudalumnos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    // Registrar alumno a una materia
    public void registrarInscripcion(String carnetAlumno, String codigoMateria) {
        String sql = "INSERT INTO inscripciones (carnet_alumno, codigo_materia) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carnetAlumno);
            pstmt.setString(2, codigoMateria);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar estudiante de una materia
    public void eliminarInscripcion(String carnetAlumno, String codigoMateria) {
        String sql = "DELETE FROM inscripciones WHERE carnet_alumno = ? AND codigo_materia = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carnetAlumno);
            pstmt.setString(2, codigoMateria);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Conteo total de estudiantes inscritos por materia
    public int contarEstudiantesPorMateria(String codigoMateria) {
        String sql = "SELECT COUNT(*) FROM inscripciones WHERE codigo_materia = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoMateria);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Ver lista de estudiantes registrados en la materia
    public List<String> listarEstudiantesPorMateria(String codigoMateria) {
        List<String> estudiantes = new ArrayList<>();
        String sql = "SELECT carnet_alumno FROM inscripciones WHERE codigo_materia = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoMateria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                estudiantes.add(rs.getString("carnet_alumno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }
}
