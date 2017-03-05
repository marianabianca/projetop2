package gastos;

public abstract class Gasto {
	private int valorGasto;

	public int getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(int valorGasto) {
		this.valorGasto = valorGasto;
	}

	public Gasto(int valorGasto) {
		/// TRATAMENTO
		this.valorGasto = valorGasto;
	}
	
	public abstract int getValorTotalGasto();
	
}
