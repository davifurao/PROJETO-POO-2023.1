package controller;

import model.IConta;

public interface ICliente {

	public abstract void cadastrarConta(IConta conta);
	
	public abstract void alterarConta(IConta contaAntiga,IConta contanova );
	
	public abstract void deletarConta(IConta conta);
}
