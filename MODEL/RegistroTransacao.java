package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class RegistroTransacao {

	private Integer id;
	private BigDecimal valor;
	private TipoTransacao tipo;
	private LocalDateTime data;
	private TipoConta tipo_conta;

	public RegistroTransacao(BigDecimal valor, TipoTransacao tipo, TipoConta tipo_conta, LocalDateTime data) {
		this.id = new Random().nextInt(999999999);
		this.valor = valor;
		this.tipo = tipo;
		this.data = data;
		this.tipo_conta = tipo_conta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
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
		return Objects.equals(id, other.id);
	}

}

//Aluno: Davi Souza de Luna
//Curso: TSI