package fatec.dao;

import fatec.classes.Moto;
import fatec.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotoDAO implements BaseDAO<Moto> {
    @Override
    public void inserir(Moto moto) throws Exception {
        String sql = "INSERT INTO motos (marca, modelo, ano, peso, cilindrada) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, moto.getMarca());
            stmt.setString(2, moto.getModelo());
            stmt.setInt(3, moto.getAno());
            stmt.setDouble(4, moto.getPeso());
            stmt.setInt(5, moto.getCilindrada());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    moto.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Moto moto) throws Exception {
        String sql = "UPDATE motos SET marca = ?, modelo = ?, ano = ?, peso = ?, cilindrada = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, moto.getMarca());
            stmt.setString(2, moto.getModelo());
            stmt.setInt(3, moto.getAno());
            stmt.setDouble(4, moto.getPeso());
            stmt.setInt(5, moto.getCilindrada());
            stmt.setInt(6, moto.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM motos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Moto buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM motos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarMotoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Moto> listarTodos() throws Exception {
        String sql = "SELECT * FROM motos ORDER BY marca";
        List<Moto> motos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                motos.add(criarMotoDoResultSet(rs));
            }
        }
        return motos;
    }

    private Moto criarMotoDoResultSet(ResultSet rs) throws SQLException {
        Moto moto = new Moto(
            rs.getString("marca"),
            rs.getString("modelo"),
            rs.getInt("ano"),
            rs.getDouble("peso"),
            rs.getInt("cilindrada")
        );
        moto.setId(rs.getInt("id"));
        return moto;
    }
} 