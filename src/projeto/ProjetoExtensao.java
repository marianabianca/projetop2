package projeto;

public class ProjetoExtensao extends Projeto {

	private int impacto;

	public ProjetoExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.setImpacto(impacto);
	}

	public int getImpacto() {
		return impacto;
	}

	public void setImpacto(int impacto) {
		this.impacto = impacto;
	}

}
