package projeto;

public class ProjetoPED extends Projeto {
	private String categoria;
	private int prodTecnica, prodAcademica, parentes;

	public ProjetoPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.categoria = categoria;
		this.prodTecnica = prodTecnica;
		this.prodAcademica = prodAcademica;
		this.parentes = patentes;
	}

}
