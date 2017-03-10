package projeto;

import java.util.ArrayList;
import java.util.List;

import gastos.Despesas;

public class Projeto {
	private String nomeDoProjeto, objetivoDoProjeto, dataDeInicio, codigoDoProjeto;
	private int duracaoEmMeses;
	private List<Despesas> custos;
	
	public Projeto(String nomeDoProjeto, String objetivoDoProjeto, String dataDeInicio, 
			int duracaoTotal, Despesas despesaInicial) throws Exception{
		
		this.nomeDoProjeto = nomeDoProjeto;
		this.objetivoDoProjeto = objetivoDoProjeto;
		this.dataDeInicio = dataDeInicio;
		this.duracaoEmMeses = duracaoTotal;
		
		custos = new ArrayList<Despesas>();
		custos.add(despesaInicial);
		
		this.codigoDoProjeto = this.hashCode();
	}
	
	public double calculaCustoTotal(){
		
		
		
		return 0;
	}
	
	
	
	
	
}
