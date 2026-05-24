package com.bernardoduarte.memento;

public class TaxaCambioEstadoSalvo implements TaxaCambioEstado {

	private final String moeda;
	private final double valorEmReais;

	public TaxaCambioEstadoSalvo(String moeda, double valorEmReais) {
		this.moeda = moeda;
		this.valorEmReais = valorEmReais;
	}

	@Override
	public String getMoeda() {
		return moeda;
	}

	@Override
	public double getValorEmReais() {
		return valorEmReais;
	}
}
