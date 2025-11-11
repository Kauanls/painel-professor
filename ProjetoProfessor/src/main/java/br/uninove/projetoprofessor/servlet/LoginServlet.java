package br.uninove.projetoprofessor.servlet;

import br.uninove.projetoprofessor.dao.AlunoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email != null) email = email.trim();
        if (senha != null) senha = senha.trim();

        System.out.println("Email recebido: " + email);
        System.out.println("Senha recebida: " + senha);

        AlunoDAO dao = new AlunoDAO();
        boolean loginValido = dao.validarLogin(email, senha);

        if (loginValido) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", email);
            response.sendRedirect("painel.jsp");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<p style='color:red; text-align:center;'>Login inválido</p>");
        }
    }
}
