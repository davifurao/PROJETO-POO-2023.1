package model;

import java.math.BigDecimal;

//Essa é uma classe intermediária que serve para ajudar na implementação de
//certos métodos, como o registro de transferência por exemplo(vá na classe ContaCorrente
//ou ContaPoupanca e veja que existe um atributo com esse nome)
public abstract class AbstractConta implements IConta {

	public void transferir(IConta contaDestino, BigDecimal quantia) {
		
	}
}

//caso for necessário utilizar esse método é só utilizar o extends na classe filho
//Com isso o método irá ser herdado


//Aluno: Davi Souza de Luna
//Curso: TSI
