package fatec.dao;

import fatec.classes.Bicicleta;
import fatec.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BicicletaDAO implements BaseDAO<Bicicleta> {
    @Override
    public void inserir(Bicicleta bicicleta) throws Exception {
        String sql = "INSERT INTO bicicletas (marca, modelo, ano, peso, num_marchas, tem_freio_disco) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, bicicleta.getMarca());
            stmt.setString(2, bicicleta.getModelo());
            stmt.setInt(3, bicicleta.getAno());
            stmt.setDouble(4, bicicleta.getPeso());
            stmt.setInt(5, bicicleta.getNumMarchas());
            stmt.setBoolean(6, bicicleta.isTemFreioDisco());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bicicleta.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Bicicleta bicicleta) throws Exception {
        String sql = "UPDATE bicicletas SET marca = ?, modelo = ?, ano = ?, peso = ?, num_marchas = ?, tem_freio_disco = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bicicleta.getMarca());
            stmt.setString(2, bicicleta.getModelo());
            stmt.setInt(3, bicicleta.getAno());
            stmt.setDouble(4, bicicleta.getPeso());
            stmt.setInt(5, bicicleta.getNumMarchas());
            stmt.setBoolean(6, bicicleta.isTemFreioDisco());
            stmt.setInt(7, bicicleta.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM bicicletas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Bicicleta buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM bicicletas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarBicicletaDoResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Bicicleta> listarTodos() throws Exception {
        String sql = "SELECT * FROM bicicletas ORDER BY marca";
        List<Bicicleta> bicicletas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bicicletas.add(criarBicicletaDoResultSet(rs));
            }
        }
        return bicicletas;
    }

    private Bicicleta criarBicicletaDoResultSet(ResultSet rs) throws SQLException {
        Bicicleta bicicleta = new Bicicleta(
            rs.getString("marca"),
            rs.getString("modelo"),
            rs.getInt("ano"),
            rs.getDouble("peso"),
            rs.getInt("num_marchas"),
            rs.getBoolean("tem_freio_disco")
        );
        bicicleta.setId(rs.getInt("id"));
        return bicicleta;
    }
} 