package com.padaria.repository;

import com.padaria.db.DatabaseManager;
import com.padaria.model.Estoque;
import com.padaria.model.Insumo;
import com.padaria.model.Movimentacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimentacaoRepositorySQLite implements MovimentacaoRepository {

    private static final String FORMATO_DATA = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    private final InsumoRepository insumoRepository = new InsumoRepositorySQLite();
    private final EstoqueRepository estoqueRepository = new EstoqueRepositorySQLite();

    @Override
    public void salvar(Movimentacao movimentacao) {
        String sql = "INSERT OR REPLACE INTO movimentacoes (id, tipo, quantidade, data, insumo_id, estoque_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, movimentacao.getId());
            ps.setString(2, movimentacao.getTipoMovimentacao());
            ps.setDouble(3, movimentacao.getQuantidade());
            ps.setString(4, new SimpleDateFormat(FORMATO_DATA).format(movimentacao.getData()));
            ps.setInt(5, movimentacao.getInsumo().getId());
            ps.setInt(6, movimentacao.getEstoque().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar movimentação", e);
        }
    }

    @Override
    public List<Movimentacao> listarTodos() {
        List<Movimentacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimentacoes ORDER BY id";
        try (Statement st = DatabaseManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar movimentações", e);
        }
        return lista;
    }

    private Movimentacao mapear(ResultSet rs) throws SQLException {
        Insumo insumo = insumoRepository.buscarPorId(rs.getInt("insumo_id"));
        Estoque estoque = estoqueRepository.buscarPorId(rs.getInt("estoque_id"));
        Date data;
        try {
            data = new SimpleDateFormat(FORMATO_DATA).parse(rs.getString("data"));
        } catch (ParseException e) {
            data = new Date();
        }
        return new Movimentacao(
                rs.getInt("id"),
                rs.getString("tipo"),
                rs.getDouble("quantidade"),
                data,
                insumo,
                estoque
        );
    }
}
