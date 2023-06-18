package client_model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.IConta;

public class Cliente implements ICliente {
	
	private String cpf;
	List<RegistroConta> contas;
	private LocalDateTime data;
	
	public Cliente(String cpf) {
		this.cpf = cpf;
		this.contas =new ArrayList<>();
		this.data = LocalDateTime.now();
	}

	@Override
	public void cadastrarConta(IConta c) {
		contas.add(new RegistroConta(this.cpf,c.getConta(),c));
	}

	@Override
	public void alterarConta(IConta antiga, IConta nova) {
	    // Percorre a lista de contas
	    for (int i = 0; i < contas.size(); i++) {
	        // Verifica se a conta na posição atual é igual à conta antiga
	        if (contas.get(i).getConta().equals(antiga)) {
	            // Substitui a conta antiga pela nova na lista
	            contas.set(i, new RegistroConta(this.cpf, nova.getConta(), nova));
	            System.out.println("Conta alterada com sucesso.");
	            return; // Encerra o método após alterar a conta
	        }
	    }
	    System.err.println("Conta não encontrada.");
	}


	public void deletarConta(IConta c) {
	    // Percorre a lista de contas
	    for (int i = 0; i < contas.size(); i++) {
	        // Verifica se a conta na posição atual é igual à conta a ser deletada
	        if (contas.get(i).getConta().equals(c)) {
	            // Remove a conta da lista
	            contas.remove(i);
	            System.out.println("Conta removida com sucesso.");
	            return; // Encerra o método após remover a conta
	        }
	    }
	    System.err.println("Conta não encontrada.");
	}



	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<RegistroConta> getContas() {
		return contas;
	}

	public void setContas(List<RegistroConta> contas) {
		this.contas = contas;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	
	
	

}
