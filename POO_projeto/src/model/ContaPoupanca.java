package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ContaPoupanca implements IConta {

	private int id;
	private int numeroConta;
	private float saldo;
	private LocalDateTime data;
	private boolean status;
	private List<RegistroTransacao> transacoes;
	Random random = new Random();
	private TipoConta conta;
	
	public final float TAXA_TRANSACAO_CORRENTE_POUPANCA = 0.02f;
	
	public  ContaPoupanca(int numeroConta) {
		this.id = random.nextInt(999999999);
		this.numeroConta = numeroConta;
		this.status = true;
		this.data= LocalDateTime.now();
		this.saldo = 0.0f;
		transacoes = new ArrayList<>();
		this.conta = TipoConta.CONTA_POUPANCA;
	}
	@Override
	public void depositar(int quantia) {
		if (status) {
			if(quantia >0) {
				this.saldo+=quantia;
				float valor = (float)quantia;
				transacoes.add(new RegistroTransacao(valor,TipoTransacao.TRASNFERENCIA_DEBITO,this.conta,LocalDateTime.now()));
				System.out.println("Deposito realizado com sucesso!");
			}else {
				System.err.println("Valor invalido para deposito.");
			}
		}else {
			System.err.println("Operação não permitida. Conta desativada.");
		}
		
	}

	@Override
	public void transferir(IConta contaDestino, int quantia) {
		if (status && isStatus()) {
			if(quantia+TAXA_TRANSACAO_CORRENTE_POUPANCA > this.saldo) {
				this.saldo-=quantia+TAXA_TRANSACAO_CORRENTE_POUPANCA;
				contaDestino.depositar(quantia);
				float valor = (float)quantia;
				transacoes.add(new RegistroTransacao(valor,TipoTransacao.TRASNFERENCIA_DEBITO,this.conta,LocalDateTime.now()));
				contaDestino.getTransacoes().add(new RegistroTransacao(valor,TipoTransacao.TRANSFERENCIA_CREDITO,contaDestino.getConta(),LocalDateTime.now()));
			}else {
				System.err.println("Saldo insuficiente para realizar a transferência.");
			}
		}else {
			System.out.println("Operacao nao pode ser realizada entre contas desativadas.");
		}
		
	}

	@Override
	public void desativarConta() {
		if(this.status) {
		this.status = false;
		}else {
			System.out.println("Conta já desativada");
		}
	}

	@Override
	public void ativarConta() {
		if(!this.status) {
			this.status = true;
		}else {
			System.out.println("Conta já ativada");
		}
		
	}

	@Override
	public void sacar(int quantia) {
		if(quantia+TAXA_TRANSACAO_CORRENTE_POUPANCA>this.saldo) {
		this.saldo -=quantia;
		float valor = (float)quantia;
		transacoes.add(new RegistroTransacao(valor,TipoTransacao.TRASNFERENCIA_DEBITO,this.conta,LocalDateTime.now()));
		System.out.println("Saque realizado");
		}else {
			System.err.println("Saldo insuficiente");
		}
		
	}

	@Override
	public List<RegistroTransacao> getTransacoes() {
		return transacoes;
	}

	@Override
	public void imprimirExtratoConta(int mes, int ano) {
		float saldoExtrato = 0;
		
		for(RegistroTransacao rt: transacoes) {
			if(rt.getData().getMonth().getValue() == mes && rt.getData().getYear() == ano) {
				saldoExtrato += rt.getValor();
				System.out.println(rt);
			}
		}System.out.println("Impressão do saldo referente ao extrato: "+saldoExtrato);
		
	}
	public int getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public float getTAXA_TRANSACAO_CORRENTE_SAQUE() {
		return TAXA_TRANSACAO_CORRENTE_POUPANCA;
	}
	public void setTransacoes(List<RegistroTransacao> transacoes) {
		this.transacoes = transacoes;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTAXA_TRANSACAO_CORRENTE_POUPANCA() {
		return TAXA_TRANSACAO_CORRENTE_POUPANCA;
	}
	
	public TipoConta getConta() {
		return conta;
	}
	public void setConta(TipoConta conta) {
		this.conta = conta;
	}
	@Override
	public String toString() {
		return "ContaCorrente [numeroConta=" + numeroConta + ", saldo=" + saldo + ", data=" + data + ", status="
				+ status + ", transacoes=" + transacoes + ", TAXA_TRANSACAO_CORRENTE_SAQUE="
				+ TAXA_TRANSACAO_CORRENTE_POUPANCA + "]";
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
		ContaPoupanca other = (ContaPoupanca) obj;
		return numeroConta == other.numeroConta;
	}
	
	

	
}
