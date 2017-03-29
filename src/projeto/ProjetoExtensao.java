package projeto;

public class ProjetoExtensao extends Projeto {

	private int impacto;

	public ProjetoExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.impacto = impacto;
	}

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
}
