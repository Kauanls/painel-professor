package br.uninove.projetoprofessor.servlet;

import br.uninove.projetoprofessor.dao.AlunoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ZerarFaltasServlet")
public class ZerarFaltasServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AlunoDAO dao = new AlunoDAO();

        // 1️⃣ Obter bimestre atual
        int bimestreAtual = dao.getBimestreAtual();

        // 2️⃣ Registrar histórico das faltas antes de zerar
        dao.registrarHistoricoBimestre(bimestreAtual);

        // 3️⃣ Zerar todas as faltas
        dao.zerarTodasFaltas();

        // 4️⃣ Incrementar bimestre para o próximo
        dao.incrementarBimestre();

        // 5️⃣ Redirecionar com mensagem de sucesso
        response.sendRedirect("painel.jsp?sucesso=zerado");
    }
}
