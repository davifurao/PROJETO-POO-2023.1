package model;

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
	
	//transformar string no tipo do enumerator. Eu usei para poder criar os objetos a partir do BD
	public static TipoConta fromString(String tipoConta) {
	    for (TipoConta t : values()) {
	        if (t.name().equalsIgnoreCase(tipoConta)) {
	            return t;
	        }
	    }
	    return null;
	}

}
//Aluno: Davi Souza de Luna
//Curso: TSI