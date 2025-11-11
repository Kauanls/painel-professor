package br.uninove.projetoprofessor.servlet;

import br.uninove.projetoprofessor.dao.AlunoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ResetarAnoLetivoServlet")
public class ResetarAnoLetivoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AlunoDAO dao = new AlunoDAO();
        dao.resetarAnoLetivo();

        response.sendRedirect("painel.jsp?sucesso=anoResetado");
    }
}
