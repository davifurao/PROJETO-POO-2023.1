package client_model;
import java.time.LocalDateTime;
import java.util.Objects;

import model.IConta;
import model.TipoConta;

public class RegistroConta {
	
	private String cpf;//A conta deve ter um identificador do cliente
	private TipoConta tipoConta;
	private IConta conta;
	private LocalDateTime data;

	public RegistroConta(String cpf,TipoConta tipoConta,IConta conta) {
		this.conta = conta;
		this.cpf = cpf;
		this.tipoConta = tipoConta;
		this.data = LocalDateTime.now();
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
		RegistroConta other = (RegistroConta) obj;
		return Objects.equals(cpf, other.cpf);
	}

	

	@Override
	public String toString() {
		return "RegistroConta [cpf=" + cpf + ", tipoConta=" + tipoConta + ", conta=" + conta + ", data=" + data + "]";
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public IConta getConta() {
		return conta;
	}

	public void setConta(IConta conta) {
		this.conta = conta;
	}
	
	
}
