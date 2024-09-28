package com.crudalumnos.servlets;

import com.crudalumnos.dao.MateriaDAO;
import com.crudalumnos.models.Materia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "SvMaterias", urlPatterns = {"/SvMaterias"})
public class SvMaterias extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        MateriaDAO materiaDAO = new MateriaDAO();

        if ("register".equals(action)) {
            String codigoMateria = request.getParameter("codigoMateria");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            Date fechaCreacion = Date.valueOf(request.getParameter("fechaCreacion"));

            Materia materia = new Materia(codigoMateria, nombre, descripcion, fechaCreacion);
            materiaDAO.registerMateria(materia);
        } else if ("edit".equals(action)) {
            String codigoMateria = request.getParameter("codigoMateria");
            Materia materia = materiaDAO.getMateria(codigoMateria);
            request.setAttribute("materia", materia);
            request.getRequestDispatcher("editMateria.jsp").forward(request, response);
        } else if ("update".equals(action)) {
            String codigoMateria = request.getParameter("codigoMateria");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            Date fechaCreacion = Date.valueOf(request.getParameter("fechaCreacion"));

            Materia materia = new Materia(codigoMateria, nombre, descripcion, fechaCreacion);
            materiaDAO.updateMateria(materia);
        } else if ("delete".equals(action)) {
            String codigoMateria = request.getParameter("codigoMateria");
            materiaDAO.deleteMateria(codigoMateria);
        }

        // Listar materias
        List<Materia> materias = materiaDAO.listMaterias();
        request.setAttribute("materiaList", materias);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
