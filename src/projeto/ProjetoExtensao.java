package projeto;

public class ProjetoExtensao extends Projeto {

	private int impacto;
	private final String[] atributosValidos = { "nome", "objetivo", "impacto", "data de inicio", "duracao", "codigo",
			"participacoes" };

	public ProjetoExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.impacto = impacto;
	}

	@Override
	public String getInfoProjeto(String atributo) throws Exception {
		if (!this.temAtributo(atributo)) {
			throw new Exception("Extensao nao possui " + atributo);
		}
		if (atributo.equalsIgnoreCase("nome")) {
			return super.getNome();
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			return super.getObjetivo();
		} else if (atributo.equalsIgnoreCase("impacto")) {
			return Integer.toString(this.getImpacto());
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			return super.getDataInicio();
		} else if (atributo.equalsIgnoreCase("nome")) {
			return super.getNome();
		} else if (atributo.equalsIgnoreCase("duracao")) {
			return Integer.toString(super.getDuracao());
		} else {
			return super.getParticipacoes();
		}
	}

	@Override
	public void editaProjeto(String atributo, String valor) throws Exception {
		if (!this.temAtributo(atributo)) {
			throw new Exception("Extensao nao possui " + atributo);
		}

		if (atributo.equalsIgnoreCase("nome")) {
			super.setNome(valor);
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			super.setObjetivo(valor);
		} else if (atributo.equalsIgnoreCase("impacto")) {
			this.setImpacto(valor);
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			super.setDataInicio(valor);
		} else {
			super.setDuracao(Integer.parseInt(valor));
		}
	}

	private void setImpacto(String valor) {
		this.impacto = Integer.parseInt(valor);
	}

	private int getImpacto() {
		return this.impacto;
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
