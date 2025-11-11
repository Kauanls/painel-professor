package br.uninove.projetoprofessor.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // pega a sessão atual
        if (session != null) {
            session.invalidate(); // encerra a sessão
        }

        // Redireciona para login.jsp com mensagem de sucesso
        response.sendRedirect("login.jsp?status=logout");
    }
}
