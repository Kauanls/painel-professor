<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.uninove.projetoprofessor.modelo.Aluno" %>
<%@ page import="br.uninove.projetoprofessor.dao.AlunoDAO" %>

<%
    // Proteção de sessão: só acessa se estiver logado
    HttpSession sessao = request.getSession(false);
    if (sessao == null || sessao.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp?status=erro");
        return;
    }

    AlunoDAO dao = new AlunoDAO();
    List<Aluno> listaAlunos = dao.listarAlunosOrdenados();
    int limiteFaltas = dao.getLimiteFaltas();
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Painel do Professor</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="painel-wrapper">
        <h1>Painel do Professor</h1>

        <!-- Botão de logout -->
        <form action="LogoutServlet" method="get" style="text-align:right; margin-bottom:15px;">
            <button type="submit" class="btn btn-secondary">Sair</button>
        </form>

        <!-- Mensagens de feedback -->
        <div class="mensagem">
            <% if ("campos".equals(request.getParameter("erro"))) { %>
                <p style="color:red;">Preencha todos os campos corretamente.</p>
            <% } else if ("numero".equals(request.getParameter("erro"))) { %>
                <p style="color:red;">Número de chamada inválido.</p>
            <% } else if ("falta".equals(request.getParameter("erro"))) { %>
                <p style="color:red;">Erro ao registrar falta. ID inválido.</p>
            <% } else if ("remocao".equals(request.getParameter("erro"))) { %>
                <p style="color:red;">Erro ao remover aluno. ID inválido.</p>
            <% } else if ("limite".equals(request.getParameter("erro"))) { %>
                <p style="color:red;">Informe um limite válido.</p>
            <% } else if ("1".equals(request.getParameter("sucesso"))) { %>
                <p style="color:green;">Aluno adicionado com sucesso!</p>
            <% } else if ("falta".equals(request.getParameter("sucesso"))) { %>
                <p style="color:green;">Falta registrada com sucesso!</p>
            <% } else if ("remocao".equals(request.getParameter("sucesso"))) { %>
                <p style="color:green;">Aluno removido com sucesso!</p>
            <% } else if ("limite".equals(request.getParameter("sucesso"))) { %>
                <p style="color:green;">Limite de faltas atualizado com sucesso!</p>
            <% } else if ("zerado".equals(request.getParameter("sucesso"))) { %>
                <p style="color:green;">Faltas zeradas com sucesso!</p>
            <% } else if ("anoResetado".equals(request.getParameter("sucesso"))) { %>
                <p style="color:green;">Ano letivo resetado com sucesso!</p>
            <% } %>
        </div>

        <div class="painel-container">
            <!-- Limite de faltas -->
            <section class="painel-box">
                <h2>Definir Limite de Faltas</h2>
                <form action="ConfigurarLimiteServlet" method="post">
                    <input type="number" name="limiteFaltas" value="<%= limiteFaltas %>" min="1" required>
                    <button type="submit">Salvar</button>
                </form>
            </section>

            <!-- Adicionar aluno -->
            <section class="painel-box">
                <h2>Adicionar Aluno</h2>
                <form action="AdicionarAlunoServlet" method="post">
                    <input type="text" name="nome" placeholder="Nome do aluno" required>
                    <input type="number" name="numeroChamada" placeholder="Número de chamada" required>
                    <button type="submit">Adicionar</button>
                </form>
            </section>

            <!-- Lista de alunos -->
            <section class="painel-box lista">
                <h2>Lista de Alunos</h2>

                <!-- Botão Novo Bimestre -->
                <form action="ZerarFaltasServlet" method="post" style="margin-bottom: 15px;">
                    <button type="submit" class="btn btn-warning">🔄 Novo Bimestre</button>
                </form>

                <!-- Botão para ver histórico -->
                <form action="historico.jsp" method="get" style="margin-bottom: 15px;">
                    <button type="submit" class="btn btn-info">📘 Ver Histórico de Bimestres</button>
                </form>

                <!-- Botão Resetar Ano Letivo -->
                <form action="ResetarAnoLetivoServlet" method="post" style="margin-bottom: 15px;"
                      onsubmit="return confirm('Tem certeza que deseja apagar todos os dados e iniciar um novo ano letivo?');">
                    <button type="submit" class="btn btn-danger">🗑️ Resetar Ano Letivo</button>
                </form>

                <table>
                    <thead>
                        <tr>
                            <th>Nº</th>
                            <th>Nome</th>
                            <th>Faltas</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Aluno aluno : listaAlunos) { %>
                            <tr class="<%= aluno.getFaltas() >= limiteFaltas ? "alerta" : "" %>">
                                <td><%= aluno.getNumeroChamada() %></td>
                                <td><%= aluno.getNome() %></td>
                                <td><%= aluno.getFaltas() %></td>
                                <td>
                                    <form action="RegistrarFaltaServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%= aluno.getId() %>">
                                        <button type="submit">+1 Falta</button>
                                    </form>
                                    <form action="RemoverAlunoServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%= aluno.getId() %>">
                                        <button type="submit" onclick="return confirm('Tem certeza que deseja remover este aluno?')">Remover</button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </section>
        </div>
    </div>
</body>
</html>