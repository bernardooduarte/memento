package com.bernardoduarte.memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorTaxaCambio {

	private final List<TaxaCambio> taxas = new ArrayList<>();
	private final Map<String, TaxaCambioFactory> fabricas = new HashMap<>();
	private final Map<String, List<TaxaCambioEstado>> historicos = new HashMap<>();

	public GerenciadorTaxaCambio() {
		fabricas.put("USD", new DolarAmericanoFactory());
		fabricas.put("EUR", new EuroFactory());
		fabricas.put("GBP", new LibraEsterlinaFactory());
	}

	public TaxaCambio registrarTaxa(String moeda, double valorEmReais) {
		TaxaCambio taxa = criarTaxa(moeda, valorEmReais);
		taxas.add(taxa);
		return taxa;
	}

	public void atualizarTaxa(String moeda, double novoValorEmReais) {
		for (TaxaCambio taxa : taxas) {
			if (taxa.getMoeda().equalsIgnoreCase(moeda)) {
				historicos.computeIfAbsent(taxa.getMoeda(), chave -> new ArrayList<>()).add(taxa.salvarEstado());
				taxa.setValorEmReais(novoValorEmReais);
				return;
			}
		}

		System.out.println("Moeda nao encontrada no gerenciador: " + moeda);
	}

	public void desfazerUltimaAlteracao(String moeda) {
		List<TaxaCambioEstado> historico = historicos.get(moeda.toUpperCase());
		if (historico == null || historico.isEmpty()) {
			System.out.println("Nao ha historico para a moeda: " + moeda);
			return;
		}

		TaxaCambioEstado estadoAnterior = historico.remove(historico.size() - 1);
		for (TaxaCambio taxa : taxas) {
			if (taxa.getMoeda().equalsIgnoreCase(moeda)) {
				taxa.restaurarEstado(estadoAnterior);
				return;
			}
		}
	}

	public List<TaxaCambio> listarTaxas() {
		return List.copyOf(taxas);
	}

	public void exibirPainel() {
		System.out.println("=== Gerenciador de Taxa de Cambio (Memento) ===");
		for (TaxaCambio taxa : taxas) {
			System.out.println("Moeda: " + taxa.getMoeda() + " | Taxa: " + taxa.getValorFormatado());
		}
		System.out.println("==============================================");
	}

	private TaxaCambio criarTaxa(String moeda, double valorEmReais) {
		TaxaCambioFactory fabrica = fabricas.get(moeda.toUpperCase());
		if (fabrica == null) {
			throw new IllegalArgumentException("Moeda nao suportada: " + moeda);
		}
		return fabrica.criarTaxa(valorEmReais);
	}
}
