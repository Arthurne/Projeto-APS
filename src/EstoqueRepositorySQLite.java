package com.padaria.repository;

import com.padaria.db.DatabaseManager;
import com.padaria.model.Estoque;
import com.padaria.model.Unidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UnidadeRepositorySQLite implements UnidadeRepository {

    private final EstoqueRepository estoqueRepository = new EstoqueRepositorySQLite();

    @Override
    public void salvar(Unidade unidade) {
        String sql = "INSERT OR REPLACE INTO unidades (id, nome, endereco, estoque_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, unidade.getId());
            ps.setString(2, unidade.getNome());
            ps.setString(3, unidade.getEndereco());
            if (unidade.getEstoque() != null) {
                ps.setInt(4, unidade.getEstoque().getId());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar unidade", e);
        }
    }

    @Override
    public void atualizar(Unidade unidade) {
        String sql = "UPDATE unidades SET nome = ?, endereco = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, unidade.getNome());
            ps.setString(2, unidade.getEndereco());
            ps.setInt(3, unidade.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar unidade", e);
        }
    }

    @Override
    public Unidade buscarPorId(int id) {
        String sql = "SELECT * FROM unidades WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar unidade", e);
        }
        return null;
    }

    @Override
    public List<Unidade> listarTodos() {
        List<Unidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM unidades ORDER BY id";
        try (Statement st = DatabaseManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar unidades", e);
        }
        return lista;
    }

    private Unidade mapear(ResultSet rs) throws SQLException {
        int estoqueId = rs.getInt("estoque_id");
        Estoque estoque = rs.wasNull() ? null : estoqueRepository.buscarPorId(estoqueId);
        return new Unidade(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("endereco"),
                estoque
        );
    }
}
