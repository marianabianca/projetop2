package projetos;

import java.util.HashSet;
import java.util.Set;

import gastos.Gasto;

public class Projeto {
	private String nomeDoProjeto, objetivoDoProjeto, dataDeInicio, codigoDoProjeto;
	private int duracaoEmMeses;
	private Set<Gasto> custos;
	
	public Projeto(String nomeDoProjeto, String objetivoDoProjeto, String dataDeInicio, 
			int duracaoTotal, String codigoDoProjeto, Gasto despesaInicial) throws Exception{
		
		/// TRATAMENTO A DECIDIR ~~~~~~~~~~
		
		setNomeDoProjeto(nomeDoProjeto);
		setObjetivoDoProjeto(objetivoDoProjeto);
		setDataDeInicio(dataDeInicio);
		setDuracaoEmMeses(duracaoTotal);
		setCodigoDoProjeto(codigoDoProjeto);
		
		custos = new HashSet<Gasto>();
		custos.add(despesaInicial);
	}
	
	
	
	
	
	
	
	
	
	public String getNomeDoProjeto() {
		return nomeDoProjeto;
	}
	public void setNomeDoProjeto(String nomeDoProjeto) {
		/// TRATAMENTO
		this.nomeDoProjeto = nomeDoProjeto;
	}
	public String getObjetivoDoProjeto() {
		return objetivoDoProjeto;
	}
	public void setObjetivoDoProjeto(String objetivoDoProjeto) {
		/// TRATAMENTO
		this.objetivoDoProjeto = objetivoDoProjeto;
	}
	public String getDataDeInicio() {
		return dataDeInicio;
	}
	public void setDataDeInicio(String dataDeInicio) {
		/// TRATAMENTO
		this.dataDeInicio = dataDeInicio;
	}
	public String getCodigoDoProjeto() {
		return codigoDoProjeto;
	}
	public void setCodigoDoProjeto(String codigoDoProjeto) {
		/// TRATAMENTO
		this.codigoDoProjeto = codigoDoProjeto;
	}
	public int getDuracaoEmMeses() {
		return duracaoEmMeses;
	}
	public void setDuracaoEmMeses(int duracaoEmMeses) {
		/// TRATAMENTO
		this.duracaoEmMeses = duracaoEmMeses;
	}
	public Set<Gasto> getCustos() {
		return custos;
	}
	public void adicionarCusto(Gasto novoCusto) {
		/// TRATAMENTO
		custos.add(novoCusto);
	}
	
	public int getCustoTotal(){
		return 0;
	}
}
