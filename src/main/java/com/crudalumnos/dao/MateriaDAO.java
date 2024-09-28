package com.crudalumnos.dao;

import com.crudalumnos.models.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

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

    public void registerMateria(Materia materia) {
        String sql = "INSERT INTO materias (codigo_materia, nombre, descripcion, fecha_creacion) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, materia.getCodigoMateria());
            pstmt.setString(2, materia.getNombre());
            pstmt.setString(3, materia.getDescripcion());
            pstmt.setDate(4, materia.getFechaCreacion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Materia> listMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materias";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Materia materia = new Materia(
                        rs.getString("codigo_materia"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_creacion")
                );
                materias.add(materia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public Materia getMateria(String codigoMateria) {
        Materia materia = null;
        String sql = "SELECT * FROM materias WHERE codigo_materia = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoMateria);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    materia = new Materia(
                            rs.getString("codigo_materia"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDate("fecha_creacion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materia;
    }

    public void updateMateria(Materia materia) {
        String sql = "UPDATE materias SET nombre = ?, descripcion = ?, fecha_creacion = ? WHERE codigo_materia = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, materia.getNombre());
            pstmt.setString(2, materia.getDescripcion());
            pstmt.setDate(3, materia.getFechaCreacion());
            pstmt.setString(4, materia.getCodigoMateria());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMateria(String codigoMateria) {
        String sql = "DELETE FROM materias WHERE codigo_materia = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoMateria);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
