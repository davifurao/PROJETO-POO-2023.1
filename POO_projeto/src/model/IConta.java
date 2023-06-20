package model;


import java.util.List;

public interface IConta { // criação de uma interface para futura manipulação
	
	public TipoConta getConta();
	public void setConta(TipoConta conta);
	public abstract String getNumeroConta();

	public abstract void depositar(float quantia);

	public abstract void transferir(IConta contaDestino, float quantia);

	public abstract void desativarConta();

	public abstract void ativarConta();

	public abstract void sacar(float quantia);

	public abstract List<RegistroTransacao> getTransacoes();

	public void imprimirExtratoConta(int mes, int ano);

	// Aluno: Davi Souza de Luna
	// Curso: TSI
}
