package com.padaria.repository;

import com.padaria.db.DatabaseManager;
import com.padaria.model.DemandaReposicao;
import com.padaria.model.Unidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemandaReposicaoRepositorySQLite implements DemandaReposicaoRepository {

    private static final String FORMATO_DATA = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    private final UnidadeRepository unidadeRepository = new UnidadeRepositorySQLite();

    @Override
    public void salvar(DemandaReposicao demanda) {
        String sql = "INSERT OR REPLACE INTO demandas (id, data_solicitacao, status, unidade_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, demanda.getId());
            ps.setString(2, new SimpleDateFormat(FORMATO_DATA).format(demanda.getDataSolicitacao()));
            ps.setString(3, demanda.getStatus());
            ps.setInt(4, demanda.getUnidade().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar demanda de reposição", e);
        }
    }

    @Override
    public void atualizar(DemandaReposicao demanda) {
        String sql = "UPDATE demandas SET status = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, demanda.getStatus());
            ps.setInt(2, demanda.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar demanda de reposição", e);
        }
    }

    @Override
    public DemandaReposicao buscarPorId(int id) {
        String sql = "SELECT * FROM demandas WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar demanda de reposição", e);
        }
        return null;
    }

    @Override
    public List<DemandaReposicao> listarTodos() {
        List<DemandaReposicao> lista = new ArrayList<>();
        String sql = "SELECT * FROM demandas ORDER BY id";
        try (Statement st = DatabaseManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar demandas de reposição", e);
        }
        return lista;
    }

    private DemandaReposicao mapear(ResultSet rs) throws SQLException {
        Unidade unidade = unidadeRepository.buscarPorId(rs.getInt("unidade_id"));
        Date data;
        try {
            data = new SimpleDateFormat(FORMATO_DATA).parse(rs.getString("data_solicitacao"));
        } catch (ParseException e) {
            data = new Date();
        }
        return new DemandaReposicao(rs.getInt("id"), data, rs.getString("status"), unidade);
    }
}
