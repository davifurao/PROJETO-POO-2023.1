package model;

import java.math.BigDecimal;

public class Aplicacao {

	public static void main(String[] args) {
		ContaCorrente conta = new ContaCorrente();
		conta.depositar(new BigDecimal(100));
		
		conta.imprimirExtratoConta(6, 23);

	}

}
