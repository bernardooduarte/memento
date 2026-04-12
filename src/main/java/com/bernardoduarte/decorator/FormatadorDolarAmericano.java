package com.bernardoduarte.decorator;

public class FormatadorDolarAmericano extends FormatadorDecorator {

    public FormatadorDolarAmericano(FormatadorValor formatadorInterno) {
        super(formatadorInterno);
    }

    @Override
    public String formatar(double valor) {
        return "US$ " + formatadorInterno.formatar(valor);
    }
}

