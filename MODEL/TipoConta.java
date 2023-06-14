package MODEL;

public enum TipoConta {

	CONTA_CORRENTE(1), CONTA_POUPANCA(2);

	private final int valor;

	private TipoConta(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static TipoConta transacaoFromValor(int valor) {// busca do tipo de transação
		for (TipoConta t : values()) {
			if (t.getValor() == valor)
				return t;
		}
		return null;
	}
}
//Aluno: Davi Souza de Luna
//Curso: TSI