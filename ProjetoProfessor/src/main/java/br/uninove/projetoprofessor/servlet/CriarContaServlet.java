package br.uninove.projetoprofessor.servlet;

import br.uninove.projetoprofessor.dao.AlunoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/CriarContaServlet")
public class CriarContaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email != null) email = email.trim();
        if (senha != null) senha = senha.trim();

        AlunoDAO dao = new AlunoDAO();

        // Verifica se já existe usuário com esse e-mail
        if (dao.usuarioExiste(email)) {
            response.sendRedirect("criarConta.jsp?status=existe");
            return;
        }

        boolean criado = dao.criarUsuario(email, senha);

        if (criado) {
            // Redireciona para login.jsp com mensagem de sucesso
            response.sendRedirect("login.jsp?status=sucesso");
        } else {
            response.sendRedirect("criarConta.jsp?status=erro");
        }
    }
}
