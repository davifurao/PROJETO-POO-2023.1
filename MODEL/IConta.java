package MODEL;

import java.math.BigDecimal;
import java.util.List;

public interface IConta { // criação de uma interface para futura manipulação

	public abstract void depositar(BigDecimal quantia);

	public abstract void transferir(IConta contaDestino, BigDecimal quantia);

	public abstract void imprimirExtratoConta();

	public abstract void desativarConta();

	public abstract void ativarConta();

	public abstract void sacar(BigDecimal quantia);

	public abstract List<RegistroTransacao> getTransacoes();

	// Aluno: Davi Souza de Luna
	// Curso: TSI
}
