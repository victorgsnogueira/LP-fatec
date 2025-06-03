package fatec.dao;

import fatec.classes.Ave;
import fatec.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AveDAO implements BaseDAO<Ave> {
    @Override
    public void inserir(Ave ave) throws Exception {
        String sql = "INSERT INTO aves (nome, idade, peso, envergadura, voa) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, ave.getNome());
            stmt.setInt(2, ave.getIdade());
            stmt.setDouble(3, ave.getPeso());
            stmt.setDouble(4, ave.getEnvergadura());
            stmt.setBoolean(5, ave.isVoa());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ave.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Ave ave) throws Exception {
        String sql = "UPDATE aves SET nome = ?, idade = ?, peso = ?, envergadura = ?, voa = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ave.getNome());
            stmt.setInt(2, ave.getIdade());
            stmt.setDouble(3, ave.getPeso());
            stmt.setDouble(4, ave.getEnvergadura());
            stmt.setBoolean(5, ave.isVoa());
            stmt.setInt(6, ave.getId());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM aves WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Ave buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM aves WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarAveDoResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Ave> listarTodos() throws Exception {
        String sql = "SELECT * FROM aves ORDER BY nome";
        List<Ave> aves = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                aves.add(criarAveDoResultSet(rs));
            }
        }
        return aves;
    }

    private Ave criarAveDoResultSet(ResultSet rs) throws SQLException {
        Ave ave = new Ave(
            rs.getString("nome"),
            rs.getInt("idade"),
            rs.getDouble("peso"),
            rs.getDouble("envergadura"),
            rs.getBoolean("voa")
        );
        ave.setId(rs.getInt("id"));
        return ave;
    }
} 