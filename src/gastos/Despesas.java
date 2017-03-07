package gastos;

public abstract class Despesas {
	private int valorGasto;

	public int getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(int valorGasto) {
		this.valorGasto = valorGasto;
	}

	public Despesas(int valorGasto) {
		/// TRATAMENTO
		this.valorGasto = valorGasto;
	}
	
	public abstract int getValorTotalGasto();
	
}
