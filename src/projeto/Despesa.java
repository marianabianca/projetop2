package projeto;

import java.io.Serializable;

public class Despesa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double valor;

	public Despesa(double valor) {
		this.valor = valor;
	}

	/**
	 * Metodo responsavel por retornar o valor da despesa.
	 * 
	 * @return - Retornara o valor da despesa.
	 */
	public double getValor() {
		return this.valor;
	}
	
	public void setValor(double novoValor){
		this.valor = novoValor;
	}

}