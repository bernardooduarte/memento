package com.bernardoduarte.bridge;

public class FormatadorLibraEsterlina extends FormatadorValor {

    @Override
    public String formatar(double valor) {
        return String.format("GBP %.2f", valor);
    }
}

