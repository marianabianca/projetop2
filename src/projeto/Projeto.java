package projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import participacao.Participacao;

public abstract class Projeto {

	private String nome, objetivo, dataInicio, codigo;
	private int duracao;
	private List<Despesa> custos;
	private Map<String, Participacao> participacoes;

	public Projeto(String nome, String objetivo, String dataInicio, int duracao, String codigo) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.custos = new ArrayList<>();
		this.codigo = codigo;
		this.participacoes = new HashMap<>();
	}

	public double calculaCustoTotal() {
		double despesaTotal = 0;
		for (Despesa despesa : custos) {
			despesaTotal = despesaTotal + despesa.getValor();
		}
		return despesaTotal;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}