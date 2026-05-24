package com.bernardoduarte.memento;

public abstract class FormatadorEncadeado extends FormatadorValor {

	protected final FormatadorValor formatadorInterno;

	protected FormatadorEncadeado(FormatadorValor formatadorInterno) {
		this.formatadorInterno = formatadorInterno;
	}
}


