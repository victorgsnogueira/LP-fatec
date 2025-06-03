package fatec.dao;

import fatec.classes.Aviao;
import fatec.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AviaoDAO implements BaseDAO<Aviao> {
    @Override
    public void inserir(Aviao aviao) throws Exception {
        String sql = "INSERT INTO avioes (marca, modelo, ano, peso, num_motores, envergadura) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aviao.getMarca());
            stmt.setString(2, aviao.getModelo());
            stmt.setInt(3, aviao.getAno());
            stmt.setDouble(4, aviao.getPeso());
            stmt.setInt(5, aviao.getNumMotores());
            stmt.setDouble(6, aviao.getEnvergadura());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    aviao.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Aviao aviao) throws Exception {
        String sql = "UPDATE avioes SET marca = ?, modelo = ?, ano = ?, peso = ?, num_motores = ?, envergadura = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aviao.getMarca());
            stmt.setString(2, aviao.getModelo());
            stmt.setInt(3, aviao.getAno());
            stmt.setDouble(4, aviao.getPeso());
            stmt.setInt(5, aviao.getNumMotores());
            stmt.setDouble(6, aviao.getEnvergadura());
            stmt.setInt(7, aviao.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM avioes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Aviao buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM avioes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarAviaoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Aviao> listarTodos() throws Exception {
        String sql = "SELECT * FROM avioes ORDER BY marca";
        List<Aviao> avioes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                avioes.add(criarAviaoDoResultSet(rs));
            }
        }
        return avioes;
    }

    private Aviao criarAviaoDoResultSet(ResultSet rs) throws SQLException {
        Aviao aviao = new Aviao(
            rs.getString("marca"),
            rs.getString("modelo"),
            rs.getInt("ano"),
            rs.getDouble("peso"),
            rs.getInt("num_motores"),
            rs.getDouble("envergadura")
        );
        aviao.setId(rs.getInt("id"));
        return aviao;
    }
} 