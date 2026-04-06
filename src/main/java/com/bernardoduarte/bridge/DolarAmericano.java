package com.bernardoduarte.bridge;

public class DolarAmericano extends TaxaCambio {

    public DolarAmericano(double valorEmReais, FormatadorValor formatador) {
        super("USD", valorEmReais, formatador);
    }
}

