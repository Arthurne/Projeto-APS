package com.padaria.repository;

import com.padaria.db.DatabaseManager;
import com.padaria.model.Estoque;
import com.padaria.model.Insumo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstoqueRepositorySQLite implements EstoqueRepository {

    @Override
    public void salvar(Estoque estoque) {
        String sql = "INSERT OR REPLACE INTO estoques (id, quantidade_atual) VALUES (?, ?)";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, estoque.getId());
            ps.setDouble(2, estoque.consultarQuantidade());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar estoque", e);
        }
        salvarInsumosVinculados(estoque);
    }

    @Override
    public void atualizar(Estoque estoque) {
        String sql = "UPDATE estoques SET quantidade_atual = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setDouble(1, estoque.consultarQuantidade());
            ps.setInt(2, estoque.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar estoque", e);
        }
        salvarInsumosVinculados(estoque);
    }

    // Mantém os insumos do estoque (e suas quantidades atuais) sincronizados
    // com as tabelas "insumos" e "estoque_insumo".
    private void salvarInsumosVinculados(Estoque estoque) {
        String upsertInsumo = "INSERT OR REPLACE INTO insumos (id, nome, unidade_medida, quantidade) VALUES (?, ?, ?, ?)";
        String vincular = "INSERT OR IGNORE INTO estoque_insumo (estoque_id, insumo_id) VALUES (?, ?)";
        try (PreparedStatement psInsumo = DatabaseManager.getConnection().prepareStatement(upsertInsumo);
             PreparedStatement psVinculo = DatabaseManager.getConnection().prepareStatement(vincular)) {
            for (Insumo insumo : estoque.getInsumos()) {
                psInsumo.setInt(1, insumo.getId());
                psInsumo.setString(2, insumo.getNome());
                psInsumo.setString(3, insumo.getUnidadeMedida());
                psInsumo.setDouble(4, insumo.getQuantidade());
                psInsumo.executeUpdate();

                psVinculo.setInt(1, estoque.getId());
                psVinculo.setInt(2, insumo.getId());
                psVinculo.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao vincular insumos ao estoque", e);
        }
    }

    @Override
    public Estoque buscarPorId(int id) {
        String sql = "SELECT * FROM estoques WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar estoque", e);
        }
        return null;
    }

    @Override
    public List<Estoque> listarTodos() {
        List<Estoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM estoques ORDER BY id";
        try (Statement st = DatabaseManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar estoques", e);
        }
        return lista;
    }

    private Estoque mapear(ResultSet rs) throws SQLException {
        Estoque estoque = new Estoque(rs.getInt("id"), rs.getDouble("quantidade_atual"));
        estoque.getInsumos().addAll(buscarInsumosDoEstoque(estoque.getId()));
        return estoque;
    }

    private List<Insumo> buscarInsumosDoEstoque(int estoqueId) throws SQLException {
        List<Insumo> insumos = new ArrayList<>();
        String sql = "SELECT i.* FROM insumos i " +
                "JOIN estoque_insumo ei ON ei.insumo_id = i.id " +
                "WHERE ei.estoque_id = ? ORDER BY i.id";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, estoqueId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    insumos.add(new Insumo(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("unidade_medida"),
                            rs.getDouble("quantidade")
                    ));
                }
            }
        }
        return insumos;
    }
}
