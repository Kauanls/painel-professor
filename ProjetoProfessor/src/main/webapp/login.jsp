<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login - Painel do Professor</title>
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
            <h2>Login do Professor</h2>

            <!-- Mensagem de feedback -->
            <div class="mensagem">
                <% 
                    String status = request.getParameter("status");
                    if ("erro".equals(status)) { 
                %>
                    <p style="color:red; text-align:center;">E-mail ou senha inválidos.</p>
                <% 
                    } else if ("logout".equals(status)) { 
                %>
                    <p style="color:green; text-align:center;">Logout realizado com sucesso.</p>
                <% 
                    } else if ("sucesso".equals(status)) { 
                %>
                    <p style="color:green; text-align:center;">Conta criada com sucesso! Faça login.</p>
                <% 
                    } 
                %>
            </div>

            <!-- Formulário de login -->
            <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
                <label for="email">E-mail</label>
                <input type="email" id="email" name="email" placeholder="Digite seu e-mail" required>

                <label for="senha">Senha</label>
                <input type="password" id="senha" name="senha" placeholder="Digite sua senha" required>

                <button type="submit">Entrar</button>

                <div class="links">
                    <a href="criarConta.jsp">Criar conta →</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>