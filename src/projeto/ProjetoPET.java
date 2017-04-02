package projeto;

import exception.LogicaException;
import exception.ParametroInvalidoException;

public class ProjetoPET extends Projeto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int impacto, rendimento;
	private int prodAcademica;
	private int prodTecnica;
	private int patentes;

	public ProjetoPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica,
			int patentes, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.impacto = impacto;
		this.rendimento = rendimento;
		this.prodTecnica = 0;
		this.prodAcademica = 0;
		this.patentes = 0;
	}

	/**
	 * Metodo responavel por oferecer determinada informacao do projeto.
	 */
	@Override
	public String getInfoProjeto(String atributo) throws LogicaException {
		switch (atributo.toLowerCase()) {
		case "nome":
			return super.getNome();
		case "objetivo":
			return super.getObjetivo();
		case "impacto":
			return Integer.toString(this.impacto);
		case "rendimento":
			return Integer.toString(this.rendimento);
		case "producao tecnica":
			return Integer.toString(this.prodTecnica);
		case "producao academica":
			return Integer.toString(this.prodAcademica);
		case "patentes":
			return Integer.toString(this.patentes);
		case "data de inicio":
			return super.getDataInicio();
		case "duracao":
			return Integer.toString(super.getDuracao());
		case "participacoes":
			return super.getParticipacoes();
		default:
			throw new LogicaException("PET nao possui " + atributo);
		}
	}

	/**
	 * Metodo responsavel por mudar o atributo para o valor especificado.
	 */
	@Override
	public void editaProjeto(String atributo, String valor) throws LogicaException {
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
		case "rendimento":
			this.rendimento = Integer.parseInt(valor);
			break;
		case "producao tecnica":
			this.prodTecnica = Integer.parseInt(valor);
			break;
		case "producao academica":
			this.prodAcademica = Integer.parseInt(valor);
			break;
		case "patentes":
			this.patentes = Integer.parseInt(valor);
			break;
		case "data de inicio":
			super.setDataInicio(valor);
			break;
		case "duracao":
			super.setDuracao(Integer.parseInt(valor));
			break;
		default:
			throw new LogicaException("PET nao possui " + atributo);
		}
	}
	
	/**
	 * Metodo recursivo que tem como funcao calcular o valor da colaboracao UASC
	 * 
	 * @return - Valor do calculo.
	 */
	@Override
	public double calculaColaboracaoUASC(){
		return 0;
	}
	
	/**
	 * Metodo responsavel por definir a atualizacao das bolsas e custeio em PET.
	 */
	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ParametroInvalidoException {
		if (montanteCapital > 0) {
			throw new ParametroInvalidoException("projeto do tipo PET nao permite despesas de capital");
		}
		
		super.atualizaBolsas(montanteBolsas);
		super.atualizaCusteio(montanteCusteio);
	}

}
