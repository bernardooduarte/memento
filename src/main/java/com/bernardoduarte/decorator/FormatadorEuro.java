package com.bernardoduarte.decorator;

public class FormatadorEuro extends FormatadorDecorator {

    public FormatadorEuro(FormatadorValor formatadorInterno) {
        super(formatadorInterno);
    }

    @Override
    public String formatar(double valor) {
        return "EUR " + formatadorInterno.formatar(valor);
    }
}

