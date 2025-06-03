package fatec.dao;

import fatec.classes.Aquatico;
import fatec.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AquaticoDAO implements BaseDAO<Aquatico> {
    @Override
    public void inserir(Aquatico aquatico) throws Exception {
        String sql = "INSERT INTO aquaticos (nome, idade, peso, profundidade_maxima) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, aquatico.getNome());
            stmt.setInt(2, aquatico.getIdade());
            stmt.setDouble(3, aquatico.getPeso());
            stmt.setDouble(4, aquatico.getProfundidadeMaxima());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    aquatico.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Aquatico aquatico) throws Exception {
        String sql = "UPDATE aquaticos SET nome = ?, idade = ?, peso = ?, profundidade_maxima = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aquatico.getNome());
            stmt.setInt(2, aquatico.getIdade());
            stmt.setDouble(3, aquatico.getPeso());
            stmt.setDouble(4, aquatico.getProfundidadeMaxima());
            stmt.setInt(5, aquatico.getId());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM aquaticos WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Aquatico buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM aquaticos WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarAquaticoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Aquatico> listarTodos() throws Exception {
        String sql = "SELECT * FROM aquaticos ORDER BY nome";
        List<Aquatico> aquaticos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                aquaticos.add(criarAquaticoDoResultSet(rs));
            }
        }
        return aquaticos;
    }

    private Aquatico criarAquaticoDoResultSet(ResultSet rs) throws SQLException {
        Aquatico aquatico = new Aquatico(
            rs.getString("nome"),
            rs.getInt("idade"),
            rs.getDouble("peso"),
            rs.getDouble("profundidade_maxima")
        );
        aquatico.setId(rs.getInt("id"));
        return aquatico;
    }
} 