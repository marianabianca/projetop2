package projeto;

import java.util.HashMap;
import java.util.Map;

public class ProjetoPED extends Projeto {

	// TODO Classe produtividade faz mais sentido aqui

	private String categoria;
	private Map<String, Produtividade> produtividade;

	public ProjetoPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo,
			String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.categoria = categoria;
		this.setProdTecnica(prodTecnica);
		this.setProdAcademica(prodAcademica);
		this.setPatentes(patentes);
		produtividade = new HashMap<String, Produtividade>();
		produtividade.put("producao academica", new ProducaoAcademica(0));
		produtividade.put("producao tecnica", new ProducaoTecnica(0));
		produtividade.put("patentes", new Patentes(0));
	}

	public int getPatentes() {
		return produtividade.get("patentes").getValor();
	}

	public void setPatentes(int patentes) {
		produtividade.get("patentes").setNumero(patentes);
	}

	public int getProdAcademica() {
		return produtividade.get("producao academica").getValor();
	}

	public void setProdAcademica(int prodAcademica) {
		produtividade.get("producao academica").setNumero(prodAcademica);
	}

	public int getProdTecnica() {
		return produtividade.get("producao tecnica").getValor();
	}

	public void setProdTecnica(int prodTecnica) {
		produtividade.get("producao tecnica").setNumero(prodTecnica);
	}
}
