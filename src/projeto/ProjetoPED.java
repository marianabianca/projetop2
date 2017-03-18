package projeto;

import java.util.HashMap;
import java.util.Map;

import produtividade.Patente;
import produtividade.ProdAcademica;
import produtividade.ProdTecnica;
import produtividade.Produtividade;

public class ProjetoPED extends Projeto {

	private String categoria;
	private Map<String, Produtividade> produtividade;
	private String[] atributosValidos = {"nome", "categoria", "producao tecnica", "producao academica", "patentes", "objetivo",
			"data de inicio", "duracao", "participacoes"};

	public ProjetoPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.categoria = categoria;
		this.produtividade = new HashMap<>();
		this.criaColecaoProdutividade();
		this.adicionaProdTecnica(prodTecnica);
		this.adicionaProdAcademica(prodAcademica);
		this.adicionaPatentes(patentes);
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
	
	@Override
	public String getInfoProjeto(String atributo) throws Exception {
		if (!this.temAtributo(atributo)){
			throw new Exception("PED nao possui " + atributo);
		}
		if (atributo.equalsIgnoreCase("nome")) {
			return super.getNome();
		} else if (atributo.equalsIgnoreCase("categoria")) {
			return this.getCategoria();
		} else if (atributo.equalsIgnoreCase("producao tecnica")) {
			return Integer.toString(this.getProdTecnica());
		} else if (atributo.equalsIgnoreCase("producao academica")) {
			return Integer.toString(this.getProdAcademica());
		} else if (atributo.equalsIgnoreCase("patentes")) {
			return Integer.toString(this.getPatentes());
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			return super.getObjetivo();
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			return super.getDataInicio();
		} else if (atributo.equalsIgnoreCase("duracao")) {
			return Integer.toString(super.getDuracao());
		} else {
			return super.getParticipacoes();
		}
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
	
	private String getCategoria() {
		return this.categoria;
	}

	private boolean temAtributo(String atributo) {
		for (String atributoValido : atributosValidos) {
			if (atributoValido.equalsIgnoreCase(atributo)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void editaProjeto(String atributo, String valor) throws Exception {
		if (!this.temAtributo(atributo)){
			throw new Exception("PED nao possui " + atributo);
		}
		
		if (atributo.equalsIgnoreCase("nome")) {
			super.setNome(valor);
		} else if (atributo.equalsIgnoreCase("categoria")) {
			this.setCategoria(valor);
		} else if (atributo.equalsIgnoreCase("producao tecnica")) {
			this.setProdTecnica(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("producao academica")) {
			this.setProdAcademica(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("patentes")) {
			this.setPatentes(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			super.setObjetivo(valor);
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			super.setDataInicio(valor);
		} else {
			super.setDataInicio(valor);
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

	private void setCategoria(String valor) {
		this.categoria = valor;
	}
	
	
	

}
