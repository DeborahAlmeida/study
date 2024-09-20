package com.example.xpto.reports;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class SaldoMovimentacao {

    private static final String URL = "jdbc:postgresql://localhost:5432/xptonew";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "";

    public BigDecimal chamarSomaMovimentacoes() {
        BigDecimal totalMovimentacoes = BigDecimal.ZERO;

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String callProcedure = "{ ? = call somar_valores_movimentacao() }";

            try (CallableStatement callableStatement = connection.prepareCall(callProcedure)) {
                // Registra o parâmetro de retorno
                callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);

                // Executa a procedure
                callableStatement.execute();

                // Obtém o resultado
                totalMovimentacoes = callableStatement.getBigDecimal(1);

                // Exibe o resultado
                System.out.println("Total de Movimentações: " + totalMovimentacoes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trate a exceção conforme necessário
        }

        return totalMovimentacoes;
    }
}
