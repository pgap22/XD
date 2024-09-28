package com.crudalumnos.servlets;

import com.crudalumnos.dao.InscripcionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SvInscripciones")
public class SvInscripciones extends HttpServlet {
    private final InscripcionDAO inscripcionDAO = new InscripcionDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String carnetAlumno = request.getParameter("carnetAlumno");
        String codigoMateria = request.getParameter("codigoMateria");

        if ("registrar".equals(action)) {
            inscripcionDAO.registrarInscripcion(carnetAlumno, codigoMateria);
            request.setAttribute("mensaje", "Estudiante registrado exitosamente.");
        } else if ("eliminar".equals(action)) {
            inscripcionDAO.eliminarInscripcion(carnetAlumno, codigoMateria);
            request.setAttribute("mensaje", "Estudiante eliminado exitosamente.");
        }

        // Redirigir de vuelta a la página JSP
        request.getRequestDispatcher("materias.jsp").forward(request, response);
    }
}
