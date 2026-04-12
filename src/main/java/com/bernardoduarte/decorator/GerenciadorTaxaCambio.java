package com.bernardoduarte.decorator;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorTaxaCambio {

    private final List<TaxaCambio> taxas = new ArrayList<>();

    public void adicionarTaxa(TaxaCambio taxaCambio) {
        taxas.add(taxaCambio);
    }

    public void exibirPainel() {
        System.out.println("=== Gerenciador de Taxa de Cambio (Decorator) ===");
        for (TaxaCambio taxa : taxas) {
            taxa.exibirInfo();
        }
        System.out.println("=============================================");
    }
}
