<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.uninove.projetoprofessor.dao.AlunoDAO" %>

<%
    AlunoDAO dao = new AlunoDAO();
    List<String[]> historico = dao.listarHistoricoBimestres();
    int limiteFaltas = dao.getLimiteFaltas();

    // Agrupar registros por bimestre e ordenar em ordem crescente
    Map<Integer, List<String[]>> historicoPorBimestre = new TreeMap<>();
    for (String[] reg : historico) {
        int bimestre = Integer.parseInt(reg[0]);
        historicoPorBimestre.putIfAbsent(bimestre, new ArrayList<>());
        historicoPorBimestre.get(bimestre).add(reg);
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Histórico de Bimestres</title>
    <link rel="stylesheet" href="css/styles.css">
    <style>
        .alerta {
            background-color: #ffcccc; /* vermelho claro para destaque */
        }
        h2 {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="painel-wrapper">
        <h1>Histórico de Bimestres</h1>

        <!-- Mostrar limite de faltas atual -->
        <p style="color:blue; font-weight:bold;">
            Limite de faltas atual: <%= limiteFaltas %>
        </p>

        <% if (historico.isEmpty()) { %>
            <p style="color:gray;">Nenhum histórico registrado ainda. Clique em "Novo Bimestre" no painel para começar a registrar.</p>
        <% } else { %>
            <% for (Map.Entry<Integer, List<String[]>> entry : historicoPorBimestre.entrySet()) { %>
                <h2>Bimestre <%= entry.getKey() %></h2>
                <table>
                    <thead>
                        <tr>
                            <th>Aluno</th>
                            <th>Faltas</th>
                            <th>Data Registro</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (String[] reg : entry.getValue()) { 
                               int faltas = Integer.parseInt(reg[1]);
                               String classe = (faltas >= limiteFaltas) ? "alerta" : "";
                        %>
                            <tr class="<%= classe %>">
                                <td><%= reg[3] %></td>
                                <td><%= reg[1] %></td>
                                <td><%= reg[2] %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
                <br>
            <% } %>
        <% } %>

        <!-- Botão para voltar ao painel -->
        <form action="painel.jsp" method="get" style="margin-top: 15px;">
            <button type="submit" class="btn btn-secondary">Voltar ao Painel</button>
        </form>
    </div>
</body>
</html>