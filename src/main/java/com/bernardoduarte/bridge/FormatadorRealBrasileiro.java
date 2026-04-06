package com.bernardoduarte.bridge;

public class FormatadorRealBrasileiro extends FormatadorValor {

    @Override
    public String formatar(double valor) {
        return String.format("R$ %.2f", valor);
    }
}
