package com.bernardoduarte.decorator;

public abstract class FormatadorDecorator extends FormatadorValor {

    protected final FormatadorValor formatadorInterno;

    protected FormatadorDecorator(FormatadorValor formatadorInterno) {
        this.formatadorInterno = formatadorInterno;
    }
}
