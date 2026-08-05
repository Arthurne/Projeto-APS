package com.padaria.repository;

import com.padaria.db.DatabaseManager;
import com.padaria.model.Insumo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsumoRepositorySQLite implements InsumoRepository {

    @Override
    public void salvar(Insumo insumo) {
        String sql = "INSERT OR REPLACE INTO insumos (id, nome, unidade_medida, quantidade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, insumo.getId());
            ps.setString(2, insumo.getNome());
            ps.setString(3, insumo.getUnidadeMedida());
            ps.setDouble(4, insumo.getQuantidade());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar insumo", e);
        }
    }

    @Override
    public void atualizar(Insumo insumo) {
        String sql = "UPDATE insumos SET nome = ?, unidade_medida = ?, quantidade = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, insumo.getNome());
            ps.setString(2, insumo.getUnidadeMedida());
            ps.setDouble(3, insumo.getQuantidade());
            ps.setInt(4, insumo.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar insumo", e);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM insumos WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir insumo", e);
        }
    }

    @Override
    public Insumo buscarPorId(int id) {
        String sql = "SELECT * FROM insumos WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar insumo", e);
        }
        return null;
    }

    @Override
    public List<Insumo> listarTodos() {
        List<Insumo> lista = new ArrayList<>();
        String sql = "SELECT * FROM insumos ORDER BY id";
        try (Statement st = DatabaseManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar insumos", e);
        }
        return lista;
    }

    private Insumo mapear(ResultSet rs) throws SQLException {
        return new Insumo(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("unidade_medida"),
                rs.getDouble("quantidade")
        );
    }
}
