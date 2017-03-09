package projetos;

import java.util.ArrayList;
import java.util.List;

import custos.Custo;

public class Projeto {
	private String nome, objetivo, dataDeInicio, codigoDoProjeto;
	private int duracaoEmMeses;
	private List<Custo> custos;
	
	public Projeto(String nomeDoProjeto, String objetivoDoProjeto, String dataDeInicio, 
			int duracaoTotal, String codigoDoProjeto, Custo despesaInicial) throws Exception{
		
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataDeInicio = dataDeInicio;
		this.codigoDoProjeto = codigoDoProjeto;
		this.duracaoEmMeses = duracaoTotal;
		
		custos = new ArrayList<Custo>();
		custos.add(despesaInicial);
	}
	
	public double calculaCustoTotal(){
		// TODO		
		return 0;
	}
	
	
}
