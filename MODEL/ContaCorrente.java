package MODEL;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ContaCorrente implements IConta {

	
	private Integer numeroConta;
	private BigDecimal saldo;
	private  LocalDateTime dataAbertura;
	public Integer getNumeroConta() {
		return numeroConta;
	}


	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}


	public BigDecimal getSaldo() {
		return saldo;
	}


	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}


	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}


	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public List<RegistroTransacao> getTransacoes() {
		return transacoes;
	}


	public void setTransacoes(List<RegistroTransacao> transacoes) {
		this.transacoes = transacoes;
	}


	public float getTAXA_TRANSACAO_CORRENTE() {
		return TAXA_TRANSACAO_CORRENTE;
	}


	private boolean status;
	private List<RegistroTransacao> transacoes;
	public final float TAXA_TRANSACAO_CORRENTE=0.05f;//adicionando uma taxa pra ganhar dinheiro hehe
	
	public ContaCorrente() {
		this.dataAbertura = LocalDateTime.now();
		this.numeroConta = new Random().nextInt(999999999);
		saldo.setScale(4, RoundingMode.HALF_UP);//arredondamento para caso o saldo passe de 4 casas decimais
		this.status = true;
		transacoes = new ArrayList<>();
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(numeroConta);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaCorrente other = (ContaCorrente) obj;
		return Objects.equals(numeroConta, other.numeroConta);
	}


	@Override
	public void depositar() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void transferir(IConta contaOrigem, IConta contaDestino, float quantia) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void imprimirExtratoConta() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void desativarConta() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ativarConta() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sacar(BigDecimal quantia) {
		// TODO Auto-generated method stub
		
	}


	
//Aluno: Davi Souza de Luna
//Curso: TSI
}
