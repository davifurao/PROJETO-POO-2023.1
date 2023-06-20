package model;

public enum TipoTransacao {
	DEPOSITO(1), SAQUE(2), TRANSFERENCIA_CREDITO(3), TRASNFERENCIA_DEBITO(4);

	private final int valor;

	private TipoTransacao(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static TipoTransacao transacaoFromValor(int valor) {// busca do tipo de transação
		for (TipoTransacao t : values()) {
			if (t.getValor() == valor)
				return t;
		}
		return null;
	}
	
	//transformar string no tipo do enumerator. Eu usei para poder criar os objetos a partir do BD
	public static TipoTransacao fromString(String tipoTransacao) {
	    for (TipoTransacao t : values()) {
	        if (t.name().equalsIgnoreCase(tipoTransacao)) {
	            return t;
	        }
	    }
	    return null;
	}


}
//Aluno: Davi Souza de Luna
//Curso: TSI