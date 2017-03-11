package projeto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pariticipacao.Participacao;

public abstract class Projeto {
	private String nome, objetivo, dataInicio, codigo;
	private int duracao;
	private List<Despesas> custos;
	private HashMap<String,Participacao> listaDeParticipacoesDeProjeto;

	public Projeto(String nome, String objetivo, String dataInicio, int duracao, String codigo) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		custos = new ArrayList<Despesas>();
		this.codigo = codigo;
		listaDeParticipacoesDeProjeto = new HashMap<>();
	}

	public double calculaCustoTotal() {
		double despesaTotal = 0;
		for (Despesas despesa : custos) {
			despesaTotal = despesaTotal + despesa.getValor();
		}
		return despesaTotal;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
}
