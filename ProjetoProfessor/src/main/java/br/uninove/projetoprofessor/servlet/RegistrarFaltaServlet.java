package br.uninove.projetoprofessor.servlet;

import br.uninove.projetoprofessor.dao.AlunoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/RegistrarFaltaServlet")
public class RegistrarFaltaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        AlunoDAO dao = new AlunoDAO();
        dao.registrarFalta(id);

        response.sendRedirect("painel.jsp");
    }
}