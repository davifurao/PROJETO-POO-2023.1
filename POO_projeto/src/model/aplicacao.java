package model;

public class aplicacao {

	public static void main(String[] args) {
		ContaCorrente c = new ContaCorrente(123456);
		ContaCorrente d = new ContaCorrente(234567);
		
		c.depositar(100);
		c.depositar(200);
		c.transferir(d, 50);
		
		c.imprimirExtratoConta(6, 2023);;
		System.out.println(c.getSaldo());
		System.out.println(d.getSaldo());

	}

}
