package com.crudalumnos.servlets;

import com.crudalumnos.models.Alumno;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvAlumnos", urlPatterns = {"/SvAlumnos"})
public class SvAlumnos extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name"; // Update with your DB name
    private static final String USER = "root"; // Update with your DB username
    private static final String PASS = "root"; // Update with your DB password

    @Override
    public void init() throws ServletException {
        // Optional: Add initialization logic if needed
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            String carnet = request.getParameter("carnet");
            Alumno alumno = findAlumnoByCarnet(carnet);
            request.setAttribute("alumno", alumno);
            request.getRequestDispatcher("/editAlumno.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            String carnet = request.getParameter("carnet");
            deleteAlumno(carnet);
            response.sendRedirect("SvAlumnos");
        } else {
            List<Alumno> studentList = listAlumnos();
            System.out.println(studentList);
            request.setAttribute("studentList", studentList);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String carnet = request.getParameter("carnet");
            String nombre = request.getParameter("name");
            String apellidos = request.getParameter("surname");
            String fechaNacimiento = request.getParameter("dob");
            String direccion = request.getParameter("address");
            addAlumno(new Alumno(carnet, nombre, apellidos, fechaNacimiento, direccion));
        } else if ("update".equals(action)) {
            String carnet = request.getParameter("carnet");
            String nombre = request.getParameter("name");
            String apellidos = request.getParameter("surname");
            String fechaNacimiento = request.getParameter("dob");
            String direccion = request.getParameter("address");

            updateAlumno(new Alumno(carnet, nombre, apellidos, fechaNacimiento, direccion));
        }

        response.sendRedirect("SvAlumnos");
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private void addAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumnos (carnet, nombre, apellidos, fecha_nacimiento, direccion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getCarnet());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getApellidos());
            pstmt.setString(4, alumno.getFechaNacimiento());
            pstmt.setString(5, alumno.getDireccion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAlumno(Alumno alumno) {
        String sql = "UPDATE alumnos SET nombre=?, apellidos=?, fecha_nacimiento=?, direccion=? WHERE carnet=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getNombre());
            pstmt.setString(2, alumno.getApellidos());
            pstmt.setString(3, alumno.getFechaNacimiento());
            pstmt.setString(4, alumno.getDireccion());
            pstmt.setString(5, alumno.getCarnet());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteAlumno(String carnet) {
        String sql = "DELETE FROM alumnos WHERE carnet=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carnet);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Alumno findAlumnoByCarnet(String carnet) {
        String sql = "SELECT * FROM alumnos WHERE carnet=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carnet);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Alumno(
                        rs.getString("carnet"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Alumno> listAlumnos() {
        List<Alumno> studentList = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                studentList.add(new Alumno(
                        rs.getString("carnet"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("direccion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
