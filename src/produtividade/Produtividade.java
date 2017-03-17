package produtividade;

public abstract class Produtividade {
	
	protected int quantidade;
	
	public Produtividade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void adicionaQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
