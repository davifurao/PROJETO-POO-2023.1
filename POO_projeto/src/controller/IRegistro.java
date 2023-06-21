package controller;

import model.IConta;
import model.TipoConta;
import model.TipoTransacao;

public interface IRegistro {

	public abstract void adicionarRegistro(float valor,TipoTransacao tipoTransacao,IConta c);
}
