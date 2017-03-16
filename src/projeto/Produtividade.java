package projeto;

public abstract class Produtividade {
	private int numero;
	
	public Produtividade(int numero){
		this.numero = numero;
	}

	public int getValor() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
