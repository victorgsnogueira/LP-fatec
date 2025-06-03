package fatec.dao;

import fatec.classes.Carro;
import fatec.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO implements BaseDAO<Carro> {
    @Override
    public void inserir(Carro carro) throws Exception {
        String sql = "INSERT INTO carros (marca, modelo, ano, peso, num_portas) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getAno());
            stmt.setDouble(4, carro.getPeso());
            stmt.setInt(5, carro.getNumPortas());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    carro.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Carro carro) throws Exception {
        String sql = "UPDATE carros SET marca = ?, modelo = ?, ano = ?, peso = ?, num_portas = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getAno());
            stmt.setDouble(4, carro.getPeso());
            stmt.setInt(5, carro.getNumPortas());
            stmt.setInt(6, carro.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM carros WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Carro buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM carros WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarCarroDoResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Carro> listarTodos() throws Exception {
        String sql = "SELECT * FROM carros ORDER BY marca";
        List<Carro> carros = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                carros.add(criarCarroDoResultSet(rs));
            }
        }
        return carros;
    }

    private Carro criarCarroDoResultSet(ResultSet rs) throws SQLException {
        Carro carro = new Carro(
            rs.getString("marca"),
            rs.getString("modelo"),
            rs.getInt("ano"),
            rs.getDouble("peso"),
            rs.getInt("num_portas")
        );
        carro.setId(rs.getInt("id"));
        return carro;
    }
} 