package projeto;

public class ProjetoMonitoria extends Projeto {

	private String disciplina, periodo;
	private int rendimento;
	private final String[] atributosValidos = { "nome", "discilina", "rendimento", "objetivo", "periodo",
			"data de inicio", "duracao", "participacoes" };

	public ProjetoMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.disciplina = disciplina;
		this.periodo = periodo;
		this.rendimento = rendimento;
	}

	@Override
	public String getInfoProjeto(String atributo) throws Exception {
		if (!this.temAtributo(atributo)) {
			throw new Exception("Monitoria nao possui " + atributo);
		}
		if (atributo.equalsIgnoreCase("nome")) {
			return super.getNome();
		} else if (atributo.equalsIgnoreCase("disciplina")) {
			return this.getDisciplina();
		} else if (atributo.equalsIgnoreCase("rendimento")) {
			return Integer.toString(this.getRendimento());
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			return super.getObjetivo();
		} else if (atributo.equalsIgnoreCase("periodo")) {
			return this.getPeriodo();
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			return super.getDataInicio();
		} else if (atributo.equalsIgnoreCase("duracao")) {
			return Integer.toString(super.getDuracao());
		} else {
			return super.getParticipacoes();
		}
	}

	@Override
	public void editaProjeto(String atributo, String valor) throws Exception {
		if (!this.temAtributo(atributo)) {
			throw new Exception("Monitoria nao possui " + atributo);
		}

		if (atributo.equalsIgnoreCase("nome")) {
			super.setNome(valor);
		} else if (atributo.equalsIgnoreCase("disciplina")) {
			this.setDisciplina(valor);
		} else if (atributo.equalsIgnoreCase("rendimento")) {
			this.setRendimento(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			super.setObjetivo(valor);
		} else if (atributo.equalsIgnoreCase("periodo")) {
			this.setPeriodo(valor);
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			super.setDataInicio(valor);
		} else {
			super.setDuracao(Integer.parseInt(valor));
		}
	}

	private String getPeriodo() {
		return this.periodo;
	}

	private void setPeriodo(String valor) {
		this.periodo = valor;
	}

	private int getRendimento() {
		return this.rendimento;
	}

	private void setRendimento(int valor) {
		this.rendimento = valor;
	}

	private String getDisciplina() {
		return this.disciplina;
	}

	private void setDisciplina(String valor) {
		this.disciplina = valor;
	}

	private boolean temAtributo(String atributo) {
		for (String atributoValido : atributosValidos) {
			if (atributoValido.equalsIgnoreCase(atributo)) {
				return true;
			}
		}

		return false;
	}

}