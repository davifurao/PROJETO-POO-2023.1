package client_model;
import model.IConta;

public interface ICliente {
	
	
	
	public abstract void cadastrarConta(IConta c);
	
	public abstract void alterarConta(IConta antiga, IConta nova);
	
	public abstract void deletarConta(IConta c);

}
