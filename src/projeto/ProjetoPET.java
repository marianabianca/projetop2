package projeto;

import java.util.HashMap;
import java.util.Map;

public class ProjetoPET extends Projeto {

	// TODO Classe produtividade tambem

	private int impacto, rendimento;
	private Map<String, Produtividade> produtividade;

	public ProjetoPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica,
			int patentes, String dataInicio, int duracao, String codigo) {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.setImpacto(impacto);
		this.setRendimento(rendimento);
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

	public int getImpacto() {
		return impacto;
	}

	public void setImpacto(int impacto) {
		this.impacto = impacto;
	}
	
	public int getRendimento() {
		return this.rendimento;
	}

	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

}
