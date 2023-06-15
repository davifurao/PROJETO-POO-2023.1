package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ContaPoupanca implements IConta {

	private Integer numeroConta;
	private BigDecimal saldo;
	private LocalDateTime dataAbertura;
	private boolean status;
	private List<RegistroTransacao> transacoes;
	public final BigDecimal TAXA_TRANSACAO_POUPANCA_SAQUE = new BigDecimal("0.01");// Dinheiroooo $$
	public final BigDecimal TAXA_TRANSACAO_POUPANCA_TRANSFERENCIA = new BigDecimal("0.02");// Money money $$$

	public ContaPoupanca() {
		this.dataAbertura = LocalDateTime.now();
		this.numeroConta = new Random().nextInt(999999999);
		this.saldo = BigDecimal.ZERO;
		saldo.setScale(4, RoundingMode.HALF_UP);
		this.status = true;
		this.transacoes = new ArrayList<>();
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
		return Objects.equals(numeroConta, other.numeroConta);
	}

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

	public BigDecimal getTAXA_TRANSACAO_POUPANCA() {
		return TAXA_TRANSACAO_POUPANCA_SAQUE;
	}

	// =======================================================================
	// Métodos relativos à classe
	// =======================================================================
	@Override
	public void depositar(BigDecimal quantia) {
		if (status) {
			if (quantia.compareTo(BigDecimal.ZERO) > 0) {// verifica se a quantia é maior que zero
				this.saldo = this.saldo.add(quantia);
				transacoes.add(new RegistroTransacao(quantia, TipoTransacao.DEPOSITO, TipoConta.CONTA_POUPANCA,
						LocalDateTime.now()));
				System.out.println("Deposito realizado com sucesso!");
			} else {
				System.err.println("Valor invalido para deposito.");
			}
		} else {
			System.err.println("Operação não permitida. Conta desativada.");
		}

	}

	@Override
	public void sacar(BigDecimal quantia) {
		if (status) {
			if (quantia.compareTo(BigDecimal.ZERO) > 0) {
				if (this.saldo.compareTo(quantia) > 0) {
					this.saldo = this.saldo.subtract(quantia.add(TAXA_TRANSACAO_POUPANCA_SAQUE));// aqui eu adiciono a
																									// taxa de transação
																									// no momento em que
																									// é feito o saque
					transacoes.add(new RegistroTransacao(quantia, TipoTransacao.SAQUE, TipoConta.CONTA_POUPANCA,
							LocalDateTime.now()));
					System.out.println("Operação realizada com sucesso");
				} else {
					System.err.println("Saldo insuficiente");// foi triste :(
				}
			} else {
				System.err.println("Valor inválido para saque");
			}
		} else {
			System.err.println("Operação não permitida. Conta desativada.");
		}
	}

	@Override
	public void transferir(IConta contaDestino, BigDecimal quantia) {
		if (status && isStatus()) {
			if (quantia.compareTo(BigDecimal.ZERO) < 0) {
				System.out.println("Valor inválido para transferência.");
			} else if (quantia.compareTo(saldo) <= 0) {// se o valor a ser enviado for menor que o saldo do dito cujo
				setSaldo(saldo.subtract(quantia));
				contaDestino.depositar(quantia);
				RegistroTransacao transacao = new RegistroTransacao(quantia, TipoTransacao.TRANSFERENCIA_CREDITO,
						TipoConta.CONTA_POUPANCA, LocalDateTime.now());
				contaDestino.getTransacoes().add(transacao);// a conta deve possuir esse método(getTransacoes)
				// Se for uma conta poupanca(especificando o parametro da conta de destino):
				// ((ContaPoupanca) contaDestino).getTransacoes().add(transacao);//a conta deve
				// possuir esse método(getTransacoes)
				transacoes.add(new RegistroTransacao(quantia, TipoTransacao.TRASNFERENCIA_DEBITO,
						TipoConta.CONTA_POUPANCA, LocalDateTime.now()));
			} else {
				System.err.println("Saldo insuficiente para realizar a transferência.");
			}
		} else {
			System.out.println("Operacao nao pode ser realizada entre contas desativadas.");
		}

	}

	@Override
	public void imprimirExtratoConta(int mes, int ano) {
		BigDecimal saldoExtrato= new BigDecimal("0");
		for(RegistroTransacao rt : transacoes) {
			if(rt.getData().getMonth().getValue() == mes && rt.getData().getYear() == ano) {
				saldoExtrato=saldoExtrato.add(rt.getValor());
				System.out.println(rt);
			}
		}
		System.out.println("Impressão do saldo referente ao extrato: "+saldoExtrato);
	}
		
	@Override
	public void desativarConta() {
		status = false;

	}

	@Override
	public void ativarConta() {
		status = true;

	}

}
