package com.bernardoduarte.memento;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MementoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void deveRegistrarTaxaComMemento() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();

		TaxaCambio taxa = gerenciador.registrarTaxa("USD", 5.17);

		assertNotNull(taxa);
		assertEquals("USD", taxa.getMoeda());
		assertEquals(5.17, taxa.getValorEmReais());
		assertEquals(1, gerenciador.listarTaxas().size());
	}

	@Test
	void deveDesfazerUltimaAlteracao() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		TaxaCambio taxa = gerenciador.registrarTaxa("GBP", 6.55);

		gerenciador.atualizarTaxa("GBP", 6.90);
		gerenciador.desfazerUltimaAlteracao("GBP");

		assertEquals(6.55, taxa.getValorEmReais());
	}

	@Test
	void deveInformarQuandoNaoHaHistorico() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida, true));
			gerenciador.desfazerUltimaAlteracao("JPY");
		} finally {
			System.setOut(saidaOriginal);
		}

		assertEquals("Nao ha historico para a moeda: JPY", saida.toString().trim());
	}

	@Test
	void deveExibirPainelComTaxas() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		gerenciador.registrarTaxa("USD", 5.17);
		gerenciador.registrarTaxa("EUR", 5.62);
		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida, true));
			assertDoesNotThrow(gerenciador::exibirPainel);
		} finally {
			System.setOut(saidaOriginal);
		}

		String painel = saida.toString();
		assertEquals(2, gerenciador.listarTaxas().size());
		assertTrue(painel.contains("Gerenciador de Taxa de Cambio (Memento)"));
		assertTrue(painel.contains("Moeda: USD | Taxa: US$ 5.17"));
		assertTrue(painel.contains("Moeda: EUR | Taxa: EUR 5.62"));
	}

	@Test
	void deveLancarExcecaoParaMoedaNaoSuportada() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();

		assertThrows(IllegalArgumentException.class, () -> gerenciador.registrarTaxa("JPY", 1.0));
	}
}
