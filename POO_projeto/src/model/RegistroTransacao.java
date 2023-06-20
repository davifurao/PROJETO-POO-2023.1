package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class RegistroTransacao  {

	
	private int id;
	private float valor;
	private TipoTransacao tipo;
	private LocalDateTime data;
	private TipoConta tipo_conta;
	
	public RegistroTransacao(float valor,TipoTransacao tipo_transacao, TipoConta tipo_conta,LocalDateTime data) {
		this.id = new Random().nextInt(999999999);
		this.valor = valor;
		this.tipo = tipo_transacao;
		this.data = data;
		this.tipo_conta = tipo_conta;
	}
	
	//sobrecarga do construtor sem a data
	public RegistroTransacao(float valor2,TipoTransacao tipo_transacao2, TipoConta tipo_conta2) {
		this.id = new Random().nextInt(999999999);
		this.valor = valor2;
		this.tipo = tipo_transacao2;
		this.tipo_conta = tipo_conta2;
	}
	
	


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroTransacao other = (RegistroTransacao) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "RegistroTransacao [id=" + id + ", valor=" + valor + ", tipo=" + tipo + ", data=" + data
				+ ", tipo_conta=" + tipo_conta + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	public TipoTransacao getTipo() {
		return tipo;
	}


	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}


	public LocalDateTime getData() {
		return data;
	}


	public void setData(LocalDateTime data) {
		this.data = data;
	}


	public TipoConta getTipo_conta() {
		return tipo_conta;
	}


	public void setTipo_conta(TipoConta tipo_conta) {
		this.tipo_conta = tipo_conta;
	}

	
	
}
