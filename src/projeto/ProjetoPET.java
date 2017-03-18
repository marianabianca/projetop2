package projeto;

import java.util.HashMap;
import java.util.Map;

import produtividade.Patente;
import produtividade.ProdAcademica;
import produtividade.ProdTecnica;
import produtividade.Produtividade;

public class ProjetoPET extends Projeto {

	private int impacto, rendimento;
	private Map<String, Produtividade> produtividade;
	private String[] atributosValidos = { "nome", "objetivo", "impacto", "rendimento", "producao tecnica",
			"producao academica", "patentes", "data de inicio", "duracao", "participacoes" };

	public ProjetoPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica,
			int patentes, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.impacto = impacto;
		this.rendimento = rendimento;
		this.produtividade = new HashMap<>();
		this.criaColecaoProdutividade();
		this.adicionaProdTecnica(prodTecnica);
		this.adicionaProdAcademica(prodAcademica);
		this.adicionaPatentes(patentes);
	}

	@Override
	public String getInfoProjeto(String atributo) throws Exception {
		if (!this.temAtributo(atributo)) {
			throw new Exception("PET nao possui " + atributo);
		}

		if (atributo.equalsIgnoreCase("nome")) {
			return super.getNome();
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			return super.getObjetivo();
		} else if (atributo.equalsIgnoreCase("impacto")) {
			return Integer.toString(this.getImpacto());
		} else if (atributo.equalsIgnoreCase("rendimento")) {
			return Integer.toString(this.getRendimento());
		} else if (atributo.equalsIgnoreCase("producao tecnica")) {
			return Integer.toString(this.getProdTecnica());
		} else if (atributo.equalsIgnoreCase("producao academica")) {
			return Integer.toString(this.getProdAcademica());
		} else if (atributo.equalsIgnoreCase("patentes")) {
			return Integer.toString(this.getPatentes());
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
			throw new Exception("PET nao possui " + atributo);
		}

		if (atributo.equalsIgnoreCase("nome")) {
			super.setNome(valor);
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			super.setObjetivo(valor);
		} else if (atributo.equalsIgnoreCase("impacto")) {
			this.setImpacto(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("rendimento")) {
			this.setRendimento(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("producao tecnica")) {
			this.setProdTecnica(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("producao academica")) {
			this.setProdAcademica(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("patentes")) {
			this.setPatentes(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			super.setDataInicio(valor);
		} else {
			super.setDuracao(Integer.parseInt(valor));
		}
	}

	private void setProdAcademica(int prodAcademica) {
		Produtividade produtividade = this.produtividade.get("prodAcademica");
		produtividade.setQuantidade(prodAcademica);
	}

	private void setPatentes(int patentes) {
		Produtividade produtividade = this.produtividade.get("patentes");
		produtividade.setQuantidade(patentes);
	}

	private void setProdTecnica(int prodTecnica) {
		Produtividade produtividade = this.produtividade.get("prodTecnica");
		produtividade.setQuantidade(prodTecnica);
	}

	private void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

	private void setImpacto(int impacto) {
		this.impacto = impacto;
	}

	private int getProdTecnica() {
		Produtividade produtividade = this.produtividade.get("prodTecnica");
		return produtividade.getQuantidade();
	}

	private int getProdAcademica() {
		Produtividade produtividade = this.produtividade.get("prodAcademica");
		return produtividade.getQuantidade();
	}

	private int getPatentes() {
		Produtividade produtividade = this.produtividade.get("patentes");
		return produtividade.getQuantidade();
	}

	private int getRendimento() {
		return this.rendimento;
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

	private void criaColecaoProdutividade() {
		this.produtividade.put("prodTecnica", new ProdTecnica(0));
		this.produtividade.put("prodAcademica", new ProdAcademica(0));
		this.produtividade.put("patentes", new Patente(0));
	}

	private void adicionaProdTecnica(int prodTecnica) {
		Produtividade produtividade = this.produtividade.get("prodTecnica");
		produtividade.adicionaQuantidade(prodTecnica);
	}

	private void adicionaProdAcademica(int prodAcademica) {
		Produtividade produtividade = this.produtividade.get("prodAcademica");
		produtividade.adicionaQuantidade(prodAcademica);
	}

	private void adicionaPatentes(int patentes) {
		Produtividade produtividade = this.produtividade.get("patentes");
		produtividade.adicionaQuantidade(patentes);
	}

}
