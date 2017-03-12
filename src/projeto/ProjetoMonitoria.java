package projeto;

public class ProjetoMonitoria extends Projeto {

	private String disciplina, periodo;
	private int rendimento;

	public ProjetoMonitoria(String nome, String disciplina,
			int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.setDisciplina(disciplina);
		this.setPeriodo(periodo);
		this.setRendimento(rendimento);
	}

	public int getRendimento() {
		return rendimento;
	}

	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}