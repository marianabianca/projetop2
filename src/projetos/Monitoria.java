package projetos;

import exceptions.*;
import gastos.Bolsas;
import gastos.Gasto;

public class Monitoria extends Projeto{

	private int expectativaDeAprovacao;
	
	public Monitoria(String nomeDoProjeto, String objetivoDoProjeto, String dataDeInicio, int duracaoTotal,
			String codigoDoProjeto, int expectativaDeAprovacao) throws StringInvalida, DuracaoInvalida, GastoNulo {
		
		/// TRATAMENTO
		
		
		
		super(nomeDoProjeto, objetivoDoProjeto, dataDeInicio, duracaoTotal, codigoDoProjeto, new Bolsas(0,0));
		this.expectativaDeAprovacao = expectativaDeAprovacao;
	}

	public int getExpectativaDeAprovacao() {
		return expectativaDeAprovacao;
	}

	public void setExpectativaDeAprovacao(int expectativaDeAprovacao) {
		this.expectativaDeAprovacao = expectativaDeAprovacao;
	}

}
