package projeto;

public class ProjetoPED extends Projeto {
	
	// TODO Classe produtividade faz mais sentido aqui

	private String categoria;
	private int prodTecnica, prodAcademica, patentes;

	public ProjetoPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.categoria = categoria;
		this.prodTecnica = prodTecnica;
		this.prodAcademica = prodAcademica;
		this.patentes = patentes;
	}

}
