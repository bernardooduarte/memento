package com.bernardoduarte.bridge;

public class TesteApp {

    public static void main(String[] args) {
        GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();

        TaxaCambio dolar = new DolarAmericano(5.17, new FormatadorDolarAmericano());
        TaxaCambio euro = new Euro(5.62, new FormatadorEuro());
        TaxaCambio libra = new LibraEsterlina(6.55, new FormatadorLibraEsterlina());

        gerenciador.adicionarTaxa(dolar);
        gerenciador.adicionarTaxa(euro);
        gerenciador.adicionarTaxa(libra);

        gerenciador.exibirPainel();

        System.out.println("\nAlterando implementador de formatacao em tempo de execucao...");
        dolar.setFormatador(new FormatadorRealBrasileiro());
        gerenciador.exibirPainel();
    }

}

