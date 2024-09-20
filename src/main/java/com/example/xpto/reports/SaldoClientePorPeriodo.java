package com.example.xpto.reports;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class SaldoClientePorPeriodo {

    private BigDecimal saldoInicial;
    private BigDecimal saldoAtual;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xonew";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "";
    private BigDecimal valorPago;


    public void chamarRelatorioSaldoCliente(
            Long clienteId,
            Date dataInicio,
            Date dataFim
    ) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String callProcedure = "{CALL relatorio_por_periodo(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try (CallableStatement callableStatement = connection.prepareCall(callProcedure)) {
                // Parâmetros de entrada
                callableStatement.setLong(1, clienteId);
                callableStatement.setDate(2, dataInicio);
                callableStatement.setDate(3, dataFim);

                // Parâmetros de saída
                callableStatement.registerOutParameter(4, java.sql.Types.NUMERIC); // p_saldo_inicial
                callableStatement.registerOutParameter(5, java.sql.Types.NUMERIC); // p_saldo_atual
                callableStatement.registerOutParameter(6, java.sql.Types.NUMERIC); // p_movimentacoes_credito
                callableStatement.registerOutParameter(7, java.sql.Types.NUMERIC); // p_movimentacoes_debito
                callableStatement.registerOutParameter(8, java.sql.Types.NUMERIC); // p_total_movimentacoes
                callableStatement.registerOutParameter(9, java.sql.Types.NUMERIC); // p_valor_pago

                // Executa a procedure
                callableStatement.execute();

                // Obtém os resultados
                this.saldoInicial = callableStatement.getBigDecimal(4);
                this.saldoAtual = callableStatement.getBigDecimal(5);
                this.valorPago = callableStatement.getBigDecimal(9);

                // Faça o que desejar com os resultados
                System.out.println("Saldo Inicial: " + this.saldoInicial);
                System.out.println("Saldo Atual: " + this.saldoAtual);
                System.out.println("Valor Pago pelas Movimentações: " + this.valorPago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trate a exceção conforme necessário
        }
    }
}