package fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fatec.classes.Reptil;
import fatec.database.DatabaseConnection;

public class ReptilDAO implements BaseDAO<Reptil> {
    
    @Override
    public void inserir(Reptil reptil) throws SQLException {
        String sql = "INSERT INTO repteis (nome, idade, peso, tem_escamas, tipo_pele) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, reptil.getNome());
            stmt.setInt(2, reptil.getIdade());
            stmt.setDouble(3, reptil.getPeso());
            stmt.setBoolean(4, reptil.isTemEscamas());
            stmt.setString(5, reptil.getTipoPele());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void atualizar(Reptil reptil) throws SQLException {
        String sql = "UPDATE repteis SET nome = ?, idade = ?, peso = ?, tem_escamas = ?, tipo_pele = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, reptil.getNome());
            stmt.setInt(2, reptil.getIdade());
            stmt.setDouble(3, reptil.getPeso());
            stmt.setBoolean(4, reptil.isTemEscamas());
            stmt.setString(5, reptil.getTipoPele());
            stmt.setInt(6, reptil.getId());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM repteis WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    @Override
    public Reptil buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM repteis WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarReptilDoResultSet(rs);
            }
        }
        
        return null;
    }
    
    @Override
    public List<Reptil> listarTodos() throws SQLException {
        String sql = "SELECT * FROM repteis";
        List<Reptil> repteis = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                repteis.add(criarReptilDoResultSet(rs));
            }
        }
        
        return repteis;
    }
    
    private Reptil criarReptilDoResultSet(ResultSet rs) throws SQLException {
        Reptil reptil = new Reptil(
            rs.getString("nome"),
            rs.getInt("idade"),
            rs.getDouble("peso"),
            rs.getBoolean("tem_escamas"),
            rs.getString("tipo_pele")
        );
        reptil.setId(rs.getInt("id"));
        return reptil;
    }
} 