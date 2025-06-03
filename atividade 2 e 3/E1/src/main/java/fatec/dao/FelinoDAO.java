package fatec.dao;

import fatec.classes.Felino;
import fatec.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FelinoDAO implements BaseDAO<Felino> {
    @Override
    public void inserir(Felino felino) throws Exception {
        String sql = "INSERT INTO felinos (nome, idade, peso, tamanho_garras) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, felino.getNome());
            stmt.setInt(2, felino.getIdade());
            stmt.setDouble(3, felino.getPeso());
            stmt.setDouble(4, felino.getTamanhoGarras());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    felino.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Felino felino) throws Exception {
        String sql = "UPDATE felinos SET nome = ?, idade = ?, peso = ?, tamanho_garras = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, felino.getNome());
            stmt.setInt(2, felino.getIdade());
            stmt.setDouble(3, felino.getPeso());
            stmt.setDouble(4, felino.getTamanhoGarras());
            stmt.setInt(5, felino.getId());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM felinos WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Felino buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM felinos WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarFelinoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Felino> listarTodos() throws Exception {
        String sql = "SELECT * FROM felinos ORDER BY nome";
        List<Felino> felinos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                felinos.add(criarFelinoDoResultSet(rs));
            }
        }
        return felinos;
    }

    private Felino criarFelinoDoResultSet(ResultSet rs) throws SQLException {
        Felino felino = new Felino(
            rs.getString("nome"),
            rs.getInt("idade"),
            rs.getDouble("peso"),
            rs.getDouble("tamanho_garras")
        );
        felino.setId(rs.getInt("id"));
        return felino;
    }
} 