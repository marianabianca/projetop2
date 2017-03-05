package gastos;

public class Bolsas extends Gasto{
	private int quantidadeDeBolsas;
	
	public Bolsas(int valorGasto, int quantidadeDeBolsas) {
		
		//TRATAMENTO
		
		super(valorGasto);
		setQuantidadeDeBolsas(quantidadeDeBolsas);
	}

	public int getQuantidadeDeBolsas() {
		return quantidadeDeBolsas;
	}

	public void setQuantidadeDeBolsas(int quantidadeDeBolsas) {
		
		// TRATAMENTO
		
		this.quantidadeDeBolsas = quantidadeDeBolsas;
	}

	@Override
	public int getValorTotalGasto() {
		return quantidadeDeBolsas * getValorGasto();
	}
}
