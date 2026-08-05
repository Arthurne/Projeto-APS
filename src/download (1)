package com.padaria.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Responsável por abrir/manter a conexão com o banco SQLite e garantir
 * que o esquema de tabelas exista antes do sistema começar a ser usado.
 *
 * Requer o driver "sqlite-jdbc" no classpath do projeto (ver README-SQLITE.md).
 */
public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:padaria.db";

    private static Connection connection;

    private DatabaseManager() {
    }

    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                try (Statement st = connection.createStatement()) {
                    st.execute("PRAGMA foreign_keys = ON");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados SQLite (" + URL + ")", e);
        }
        return connection;
    }

    /**
     * Cria as tabelas do sistema caso ainda não existam. Deve ser chamado
     * uma vez no início da aplicação (ver Main.java).
     */
    public static void inicializar() {
        try (Statement st = getConnection().createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS insumos (
                    id INTEGER PRIMARY KEY,
                    nome TEXT NOT NULL,
                    unidade_medida TEXT,
                    quantidade REAL NOT NULL DEFAULT 0
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS estoques (
                    id INTEGER PRIMARY KEY,
                    quantidade_atual REAL NOT NULL DEFAULT 0
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS estoque_insumo (
                    estoque_id INTEGER NOT NULL,
                    insumo_id INTEGER NOT NULL,
                    PRIMARY KEY (estoque_id, insumo_id),
                    FOREIGN KEY (estoque_id) REFERENCES estoques(id),
                    FOREIGN KEY (insumo_id) REFERENCES insumos(id)
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS unidades (
                    id INTEGER PRIMARY KEY,
                    nome TEXT NOT NULL,
                    endereco TEXT,
                    estoque_id INTEGER,
                    FOREIGN KEY (estoque_id) REFERENCES estoques(id)
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS movimentacoes (
                    id INTEGER PRIMARY KEY,
                    tipo TEXT NOT NULL,
                    quantidade REAL NOT NULL,
                    data TEXT NOT NULL,
                    insumo_id INTEGER,
                    estoque_id INTEGER,
                    FOREIGN KEY (insumo_id) REFERENCES insumos(id),
                    FOREIGN KEY (estoque_id) REFERENCES estoques(id)
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS demandas (
                    id INTEGER PRIMARY KEY,
                    data_solicitacao TEXT NOT NULL,
                    status TEXT NOT NULL,
                    unidade_id INTEGER,
                    FOREIGN KEY (unidade_id) REFERENCES unidades(id)
                )
            """);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar o banco de dados SQLite", e);
        }
    }
}
