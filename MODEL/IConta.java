package MODEL;

import java.math.BigDecimal;

public interface IConta { //criação de uma interface para futura manipulação

	public abstract void depositar(BigDecimal quantia);
	
	public abstract void transferir(IConta contaDestino,BigDecimal quantia);
	public abstract void imprimirExtratoConta();
	public abstract void desativarConta();
	public abstract void ativarConta();
	public abstract void sacar(BigDecimal quantia);
	
	//Aluno: Davi Souza de Luna
	//Curso: TSI
}
