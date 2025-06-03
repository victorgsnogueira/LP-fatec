package fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fatec.classes.Mamifero;
import fatec.database.DatabaseConnection;

public class MamiferoDAO implements BaseDAO<Mamifero> {
    
    @Override
    public void inserir(Mamifero mamifero) throws SQLException {
        String sql = "INSERT INTO mamiferos (nome, idade, peso, tem_pelos, tipo_alimentacao, data_cadastro) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, mamifero.getNome());
            stmt.setInt(2, mamifero.getIdade());
            stmt.setDouble(3, mamifero.getPeso());
            stmt.setBoolean(4, mamifero.isTemPelos());
            stmt.setString(5, mamifero.getTipoAlimentacao());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void atualizar(Mamifero mamifero) throws SQLException {
        String sql = "UPDATE mamiferos SET nome = ?, idade = ?, peso = ?, tem_pelos = ?, tipo_alimentacao = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, mamifero.getNome());
            stmt.setInt(2, mamifero.getIdade());
            stmt.setDouble(3, mamifero.getPeso());
            stmt.setBoolean(4, mamifero.isTemPelos());
            stmt.setString(5, mamifero.getTipoAlimentacao());
            stmt.setInt(6, mamifero.getId());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM mamiferos WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    @Override
    public Mamifero buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM mamiferos WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarMamiferoDoResultSet(rs);
            }
        }
        
        return null;
    }
    
    @Override
    public List<Mamifero> listarTodos() throws SQLException {
        String sql = "SELECT * FROM mamiferos ORDER BY data_cadastro DESC";
        List<Mamifero> mamiferos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                mamiferos.add(criarMamiferoDoResultSet(rs));
            }
        }
        
        return mamiferos;
    }
    
    private Mamifero criarMamiferoDoResultSet(ResultSet rs) throws SQLException {
        Mamifero mamifero = new Mamifero(
            rs.getString("nome"),
            rs.getInt("idade"),
            rs.getDouble("peso"),
            rs.getBoolean("tem_pelos"),
            rs.getString("tipo_alimentacao")
        );
        mamifero.setId(rs.getInt("id"));
        return mamifero;
    }
} 