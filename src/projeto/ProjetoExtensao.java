package projeto;

import exception.ParametroInvalidoException;

public class ProjetoExtensao extends Projeto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int impacto;

	public ProjetoExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.impacto = impacto;
	}

	/**
	 * Metodo responsavel por oferecer determinada informacao do projeto.
	 */
	@Override
	public String getInfoProjeto(String atributo) throws Exception {
		switch (atributo.toLowerCase()) {
		case "nome":
			return super.getNome();
		case "objetivo":
			return super.getObjetivo();
		case "impacto":
			return Integer.toString(this.impacto);
		case "data de inicio":
			return super.getDataInicio();
		case "duracao":
			return Integer.toString(super.getDuracao());
		case "participacoes":
			return super.getParticipacoes();
		default:
			throw new Exception("Extensao nao possui " + atributo);
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
		case "objetivo":
			super.setObjetivo(valor);
			break;
		case "impacto":
			this.impacto = Integer.parseInt(valor);
			break;
		case "data de inicio":
			super.setDataInicio(valor);
			break;
		case "duracao":
			super.setDuracao(Integer.parseInt(valor));
			break;
		default:
			throw new Exception("Extensao nao possui " + atributo);
		}
	}
	
	@Override
	public double calculaColaboracaoUASC(){
		double colaboracaoDeProjeto = super.calculaColaboracaoUASC();
		if (colaboracaoDeProjeto == 0){
			return 0;
		}
		double colaboracao = super.calculaColaboracaoUASC() - 0.05*this.impacto*colaboracaoDeProjeto;
		return colaboracao;
	}

	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ParametroInvalidoException {
		if (montanteCapital > 0) {
			throw new ParametroInvalidoException("projeto do tipo Extensao nao permite despesas de capital");
		}
		
		super.atualizaBolsas(montanteBolsas);
		super.atualizaCusteio(montanteCusteio);
	}
	
}
