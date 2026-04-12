package com.bernardoduarte.decorator;

public class FormatadorRealBrasileiro extends FormatadorDecorator {

    public FormatadorRealBrasileiro(FormatadorValor formatadorInterno) {
        super(formatadorInterno);
    }

    @Override
    public String formatar(double valor) {
        return "R$ " + formatadorInterno.formatar(valor);
    }
}
