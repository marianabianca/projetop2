package projeto;

import exception.ParametroInvalidoException;

public class ProjetoMonitoria extends Projeto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String disciplina, periodo;
	private int rendimento;

	public ProjetoMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.disciplina = disciplina;
		this.periodo = periodo;
		this.rendimento = rendimento;
	}

	/**
	 * Metodo responsavel por oferecer determinada informacao do projeto.
	 */
	@Override
	public String getInfoProjeto(String atributo) throws Exception {
		switch (atributo.toLowerCase()) {
		case "nome":
			return super.getNome();
		case "disciplina":
			return this.disciplina;
		case "rendimento":
			return Integer.toString(this.rendimento);
		case "objetivo":
			return super.getObjetivo();
		case "periodo":
			return this.periodo;
		case "data de inicio":
			return super.getDataInicio();
		case "duracao":
			return Integer.toString(super.getDuracao());
		case "participacoes":
			return super.getParticipacoes();
		default:
			throw new Exception("Monitoria nao possui " + atributo);
		}
	}

	/**
	 * Metodo responsavel por mudar o atributo para o valor especificado.
	 */
	@Override
	public void editaProjeto(String atributo, String valor) throws Exception {
		switch (atributo.toLowerCase()) {
		case "nome":
			super.setNome(valor);
			break;
		case "disciplina":
			this.disciplina = valor;
			break;
		case "rendimento":
			this.rendimento = Integer.parseInt(valor);
			break;
		case "objetivo":
			super.setObjetivo(valor);
			break;
		case "periodo":
			this.periodo = valor;
			break;
		case "data de inicio":
			super.setDataInicio(valor);
			break;
		case "duracao":
			super.setDuracao(Integer.parseInt(valor));
			break;
		default:
			throw new Exception("Monitoria nao possui " + atributo);
		}
	}

	/**
	 * Metodo responsavel por quebrar o default da classe pai.
	 */
	@Override
	public boolean isMonitoria() {
		return true;
	}
	
	@Override
	public double calculaColaboracaoUASC(){
		return 0;
	}
	
	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ParametroInvalidoException {
		if (montanteCusteio > 0 || montanteCapital > 0) {
			throw new ParametroInvalidoException("projeto do tipo monitoria nao permite despesas de custeio ou capital");
		}
		
		super.atualizaBolsas(montanteBolsas);
	}

}