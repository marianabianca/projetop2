package projeto;

public class Despesa {

	private double valor;

	public Despesa(double valor) {
		this.valor = valor;
	}

	/**
	 * Método responsável por retornar o valor da despesa.
	 * 
	 * @return - Retornará o valor da despesa.
	 */
	public double getValor() {
		return this.valor;
	}

}