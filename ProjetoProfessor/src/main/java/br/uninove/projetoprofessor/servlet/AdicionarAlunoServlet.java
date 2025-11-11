package br.uninove.projetoprofessor.servlet;

import br.uninove.projetoprofessor.dao.AlunoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/AdicionarAlunoServlet")
public class AdicionarAlunoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String numeroStr = request.getParameter("numeroChamada");

        // Validação dos campos
        if (nome == null || nome.trim().isEmpty() || numeroStr == null || numeroStr.trim().isEmpty()) {
            response.sendRedirect("painel.jsp?erro=campos");
            return;
        }

        try {
            int numeroChamada = Integer.parseInt(numeroStr);

            AlunoDAO dao = new AlunoDAO();
            dao.adicionarAluno(nome.trim(), numeroChamada);

            response.sendRedirect("painel.jsp?sucesso=1");
        } catch (NumberFormatException e) {
            response.sendRedirect("painel.jsp?erro=numero");
        }
    }
}