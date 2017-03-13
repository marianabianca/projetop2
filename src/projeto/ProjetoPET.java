package projeto;

public class ProjetoPET extends Projeto {

	// TODO Classe produtividade tambem

	private int impacto, rendimento, prodTecnica, prodAcademica, patentes;

	public ProjetoPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica,
			int patentes, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.impacto = impacto;
		this.rendimento = rendimento;
		this.prodTecnica = prodTecnica;
		this.prodAcademica = prodAcademica;
		this.patentes = patentes;

	}

}
