package projetos;

import custos.Bolsa;
import custos.Custo;

public class Monitoria extends Projeto{
	
	private String disciplina, periodo;
	
	public Monitoria(String nomeDoProjeto, String objetivoDoProjeto, String dataDeInicio, int duracaoTotal,
			String codigoDoProjeto, Bolsa bolsa, String periodo, String disciplina) throws Exception {
		
		// TODO TRATAMENTO
		
		super(nomeDoProjeto, objetivoDoProjeto, dataDeInicio, duracaoTotal, codigoDoProjeto, bolsa);
		
		this.periodo = periodo;
		this.disciplina = disciplina;
	}

}
