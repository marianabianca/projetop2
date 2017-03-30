package projeto;

public class Despesa {

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