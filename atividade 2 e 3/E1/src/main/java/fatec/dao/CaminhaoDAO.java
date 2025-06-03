package fatec.dao;

import fatec.classes.Caminhao;
import fatec.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaminhaoDAO implements BaseDAO<Caminhao> {
    @Override
    public void inserir(Caminhao caminhao) throws Exception {
        String sql = "INSERT INTO caminhoes (marca, modelo, ano, peso, capacidade_carga) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, caminhao.getMarca());
            stmt.setString(2, caminhao.getModelo());
            stmt.setInt(3, caminhao.getAno());
            stmt.setDouble(4, caminhao.getPeso());
            stmt.setDouble(5, caminhao.getCapacidadeCarga());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    caminhao.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Caminhao caminhao) throws Exception {
        String sql = "UPDATE caminhoes SET marca = ?, modelo = ?, ano = ?, peso = ?, capacidade_carga = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caminhao.getMarca());
            stmt.setString(2, caminhao.getModelo());
            stmt.setInt(3, caminhao.getAno());
            stmt.setDouble(4, caminhao.getPeso());
            stmt.setDouble(5, caminhao.getCapacidadeCarga());
            stmt.setInt(6, caminhao.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM caminhoes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Caminhao buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM caminhoes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarCaminhaoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Caminhao> listarTodos() throws Exception {
        String sql = "SELECT * FROM caminhoes ORDER BY marca";
        List<Caminhao> caminhoes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                caminhoes.add(criarCaminhaoDoResultSet(rs));
            }
        }
        return caminhoes;
    }

    private Caminhao criarCaminhaoDoResultSet(ResultSet rs) throws SQLException {
        Caminhao caminhao = new Caminhao(
            rs.getString("marca"),
            rs.getString("modelo"),
            rs.getInt("ano"),
            rs.getDouble("peso"),
            rs.getDouble("capacidade_carga")
        );
        caminhao.setId(rs.getInt("id"));
        return caminhao;
    }
} 