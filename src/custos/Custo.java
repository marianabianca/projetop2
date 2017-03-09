package custos;

import exceptions.InteiroNegativoException;
import validacao.ModuloDeValidacao;

public abstract class Custo {
	private int valor;

	public Custo(int valor) throws InteiroNegativoException {
		ModuloDeValidacao.inteiroNegativo(valor);
		this.valor = valor;
	}
	
	public int getValorGasto() {
		return valor;
	}

	public void setValorGasto(int valor) throws InteiroNegativoException {
		ModuloDeValidacao.inteiroNegativo(valor);
		this.valor = valor;
	}

	public int getValorTotalGasto(){
		return this.valor;
	}
	
}
