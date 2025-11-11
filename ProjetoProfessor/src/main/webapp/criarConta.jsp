<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Criar Conta - Painel do Professor</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="login-wrapper">
        <!-- Lado esquerdo: imagem ilustrativa -->
        <div class="login-illustration">
            <img src="img/ilustracao-infantil.png" alt="Ilustração de crianças aprendendo">
        </div>

        <!-- Lado direito: formulário -->
        <div class="login-form">
            <h2>Criar Conta</h2>

            <!-- Mensagem de feedback -->
            <div class="mensagem">
                <% if ("sucesso".equals(request.getParameter("status"))) { %>
                    <p style="color:green; text-align:center;">Conta criada com sucesso! Faça login.</p>
                <% } else if ("erro".equals(request.getParameter("status"))) { %>
                    <p style="color:red; text-align:center;">Erro ao criar conta. Tente novamente.</p>
                <% } else if ("existe".equals(request.getParameter("status"))) { %>
                    <p style="color:red; text-align:center;">Este e-mail já está cadastrado.</p>
                <% } %>
            </div>

            <form action="<%= request.getContextPath() %>/CriarContaServlet" method="post">
                <label for="email">E-mail</label>
                <input type="email" id="email" name="email" placeholder="Digite seu e-mail" required>

                <label for="senha">Senha</label>
                <input type="password" id="senha" name="senha" placeholder="Digite sua senha" required>

                <button type="submit">Cadastrar</button>

                <div class="links">
                    <a href="login.jsp">← Voltar para Login</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
