package com.bernardoduarte.decorator;

public class FormatadorLibraEsterlina extends FormatadorDecorator {

    public FormatadorLibraEsterlina(FormatadorValor formatadorInterno) {
        super(formatadorInterno);
    }

    @Override
    public String formatar(double valor) {
        return "GBP " + formatadorInterno.formatar(valor);
    }
}

