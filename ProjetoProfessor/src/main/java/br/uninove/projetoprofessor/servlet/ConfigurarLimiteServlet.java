package br.uninove.projetoprofessor.servlet;

import br.uninove.projetoprofessor.dao.AlunoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ConfigurarLimiteServlet")
public class ConfigurarLimiteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int limite = Integer.parseInt(request.getParameter("limiteFaltas"));

        AlunoDAO dao = new AlunoDAO();
        dao.setLimiteFaltas(limite);

        response.sendRedirect("painel.jsp");
    }
}