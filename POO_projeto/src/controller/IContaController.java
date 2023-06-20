package controller;

import model.IConta;

public interface IContaController {

	public abstract void sacar(float quantia);
	
	public abstract void depositar(float quantia);
	
	public abstract void transferir(IConta contaDestino, float valor);
	
	public abstract void imprimirExtrato(int mes, int ano);
	
}
