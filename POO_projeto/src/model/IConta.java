package model;


import java.util.List;

public interface IConta { // criação de uma interface para futura manipulação
	
	public TipoConta getConta();
	public void setConta(TipoConta conta);
	public abstract int getNumeroConta();

	public abstract void depositar(int quantia);

	public abstract void transferir(IConta contaDestino, int quantia);

	public abstract void desativarConta();

	public abstract void ativarConta();

	public abstract void sacar(int quantia);

	public abstract List<RegistroTransacao> getTransacoes();

	public void imprimirExtratoConta(int mes, int ano);

	// Aluno: Davi Souza de Luna
	// Curso: TSI
}
