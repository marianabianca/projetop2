package projeto;

public class ProjetoExtensao extends Projeto {

	private int impacto;

	public ProjetoExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.impacto = impacto;
	}

}
