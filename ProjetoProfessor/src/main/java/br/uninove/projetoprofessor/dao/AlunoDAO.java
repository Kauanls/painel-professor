package br.uninove.projetoprofessor.dao;

import br.uninove.projetoprofessor.modelo.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final String URL = "jdbc:mysql://localhost:3306/projeto_professor?useSSL=false&allowPublicKeyRetrieval=true";
    private final String USER = "root";
    private final String PASSWORD = "KLdb$*00157";

    private Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do MySQL não encontrado!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Verifica se já existe usuário com o e-mail informado
public boolean usuarioExiste(String email) {
    String sql = "SELECT id FROM usuarios WHERE email = ?";
    try (Connection conn = conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        System.err.println("Erro ao verificar usuário existente:");
        e.printStackTrace();
    }
    return false;
}

// Cria novo usuário
public boolean criarUsuario(String email, String senha) {
    String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
    try (Connection conn = conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email); // aqui usamos o próprio email como "nome"
        stmt.setString(2, email);
        stmt.setString(3, senha);
        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao criar usuário:");
        e.printStackTrace();
    }
    return false;
}

    public boolean validarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Se encontrou, login válido
            }
        } catch (SQLException e) {
            System.err.println("Erro ao validar login:");
            e.printStackTrace();
        }
        return false;
    }

    // ============================
    // Alunos
    // ============================

    public List<Aluno> listarAlunosOrdenados() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos ORDER BY nome ASC";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNumeroChamada(rs.getInt("numero_chamada"));
                aluno.setFaltas(rs.getInt("faltas"));
                lista.add(aluno);
            }

            System.out.println("Alunos carregados: " + lista.size());
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos:");
            e.printStackTrace();
        }

        return lista;
    }

    public void adicionarAluno(String nome, int numeroChamada) {
        String sql = "INSERT INTO alunos (nome, numero_chamada, faltas) VALUES (?, ?, 0)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, numeroChamada);
            int linhasAfetadas = stmt.executeUpdate();

            System.out.println("Aluno inserido: " + nome + " | Linhas afetadas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar aluno:");
            e.printStackTrace();
        }
    }

    public void removerAluno(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            System.out.println("Aluno removido | ID: " + id + " | Linhas afetadas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.err.println("Erro ao remover aluno:");
            e.printStackTrace();
        }
    }

    public void registrarFalta(int id) {
        String sql = "UPDATE alunos SET faltas = faltas + 1 WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            System.out.println("Falta registrada | ID: " + id + " | Linhas afetadas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.err.println("Erro ao registrar falta:");
            e.printStackTrace();
        }
    }

    public void zerarTodasFaltas() {
        String sql = "UPDATE alunos SET faltas = 0";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Faltas zeradas para todos os alunos | Linhas afetadas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.err.println("Erro ao zerar faltas:");
            e.printStackTrace();
        }
    }

    // ============================
    // Configurações
    // ============================

    public int getLimiteFaltas() {
        String sql = "SELECT limite_faltas FROM configuracoes WHERE id = 1";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int limite = rs.getInt("limite_faltas");
                System.out.println("Limite de faltas atual: " + limite);
                return limite;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter limite de faltas:");
            e.printStackTrace();
        }

        return 0;
    }

    public void setLimiteFaltas(int limite) {
        String sql = "UPDATE configuracoes SET limite_faltas = ? WHERE id = 1";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limite);
            int linhasAfetadas = stmt.executeUpdate();

            System.out.println("Limite de faltas atualizado para: " + limite + " | Linhas afetadas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar limite de faltas:");
            e.printStackTrace();
        }
    }

    // ============================
    // Bimestres e histórico
    // ============================

    public int getBimestreAtual() {
        String sql = "SELECT bimestre_atual FROM controle_bimestre WHERE id = 1";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("bimestre_atual");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter bimestre atual:");
            e.printStackTrace();
        }
        return 1;
    }

    public void registrarHistoricoBimestre(int bimestre) {
        String sql = "INSERT INTO historico_bimestres (aluno_id, bimestre, faltas) " +
                     "SELECT id, ?, faltas FROM alunos";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bimestre);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Histórico registrado para " + linhasAfetadas + " alunos no bimestre " + bimestre);
        } catch (SQLException e) {
            System.err.println("Erro ao registrar histórico:");
            e.printStackTrace();
        }
    }

    public void incrementarBimestre() {
        String sql = "UPDATE controle_bimestre " +
                     "SET bimestre_atual = CASE " +
                     "WHEN bimestre_atual >= 4 THEN 1 " +
                     "ELSE bimestre_atual + 1 END " +
                     "WHERE id = 1";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Bimestre atualizado | Linhas afetadas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar bimestre:");
            e.printStackTrace();
        }
    }

    public List<String[]> listarHistoricoBimestres() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT h.bimestre, h.faltas, h.data_registro, a.nome " +
                     "FROM historico_bimestres h " +
                     "JOIN alunos a ON h.aluno_id = a.id " +
                     "ORDER BY h.data_registro DESC";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] registro = new String[4];
                registro[0] = rs.getString("bimestre");
                registro[1] = rs.getString("faltas");
                registro[2] = rs.getString("data_registro");
                registro[3] = rs.getString("nome");
                lista.add(registro);
            }

            System.out.println("Histórico de bimestres carregado: " + lista.size());
        } catch (SQLException e) {
            System.err.println("Erro ao listar histórico de bimestres:");
            e.printStackTrace();
        }

        return lista;
    }
    
    public void resetarAnoLetivo() {
    String apagarHistorico = "DELETE FROM historico_bimestres";
    String apagarAlunos = "DELETE FROM alunos";
    String resetarBimestre = "UPDATE controle_bimestre SET bimestre_atual = 1 WHERE id = 1";

    try (Connection conn = conectar()) {
        conn.setAutoCommit(false);

        try (Statement stmt = conn.createStatement()) {
            // Apaga o histórico primeiro para não violar FK ao apagar alunos
            stmt.executeUpdate(apagarHistorico);
            // Apaga todos os alunos
            stmt.executeUpdate(apagarAlunos);
            // Reinicia o bimestre para 1
            stmt.executeUpdate(resetarBimestre);

            conn.commit();
            System.out.println("Ano letivo resetado com sucesso.");
        } catch (SQLException e) {
            conn.rollback();
            System.err.println("Erro durante reset do ano letivo. Transação revertida.");
            e.printStackTrace();
        }
    } catch (SQLException e) {
        System.err.println("Erro ao conectar ou finalizar reset do ano letivo:");
        e.printStackTrace();
    }
}

}
