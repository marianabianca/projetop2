package projeto;

public class ProjetoMonitoria extends Projeto {
	private String disciplina;
	private int rendimento;
		
	public ProjetoMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.disciplina = disciplina;
		this.rendimento = rendimento;
	}	
}
