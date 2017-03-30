package projeto;

public class ProjetoPED extends Projeto {

	private String categoria;
	private int prodAcademica;
	private int prodTecnica;
	private int patentes;

	public ProjetoPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.categoria = categoria;
		this.prodAcademica = prodAcademica;
		this.prodTecnica = prodTecnica;
		this.patentes = patentes;
	}

	/**
	 * Método responável por oferecer determinada informacão do projeto.
	 */
	@Override
	public String getInfoProjeto(String atributo) throws Exception {
		switch (atributo.toLowerCase()) {
		case "nome":
			return super.getNome();
		case "categoria":
			return this.categoria;
		case "producao tecnica":
			return Integer.toString(this.prodTecnica);
		case "producao academica":
			return Integer.toString(this.prodAcademica);
		case "patentes":
			return Integer.toString(this.patentes);
		case "objetivo":
			return super.getObjetivo();
		case "data de inicio":
			return super.getDataInicio();
		case "duracao":
			return Integer.toString(super.getDuracao());
		case "participacoes":
			return super.getParticipacoes();
		default:
			throw new Exception("PED nao possui " + atributo);
		}
	}

	/**
	 * Método responsável por mudar o atributo para o valor especificado.
	 */
	@Override
	public void editaProjeto(String atributo, String valor) throws Exception {
		switch (atributo.toLowerCase()) {
		case "nome":
			super.setNome(valor);
			break;
		case "categoria":
			this.categoria = valor;
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
		case "objetivo":
			super.setObjetivo(valor);
			break;
		case "data de inicio":
			super.setDataInicio(valor);
			break;
		case "duracao":
			super.setDuracao(Integer.parseInt(valor));
			break;
		default:
			throw new Exception("PED nao possui " + atributo);
		}
	}

	/**
	 * Método resposável por enviar a categoria.
	 * 
	 * @return - Retornará a categoria.
	 */
	public String getCategoria() {
		return this.categoria;
	}

	/**
	 * Método responsável por quebrar o default da classe pai.
	 */
	@Override
	public boolean isPED() {
		return true;
	}
	
	@Override
	public double calculaColaboracaoUASC(){
		double colaboracao = super.calculaColaboracaoUASC();
		if (colaboracao == 0){
			return 0;
		}
		double umPorcento = (colaboracao/10);
		if (patentes != 0){
			colaboracao += umPorcento * 3;
		}
		if (prodTecnica != 0){
			colaboracao += (umPorcento * 0.3) * prodTecnica;
		}
		if (prodAcademica != 0){
			colaboracao -= (umPorcento * 0.2) *prodAcademica;
		}
		colaboracao += Math.floorDiv((int) super.getCapital(), 100000);
		return colaboracao;
	}

}
