package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class FactoryProfissional {
	public Profissional FactoryProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras) throws Exception{
		if (cargo.equalsIgnoreCase("desenvolvedor")) {
			return criaDesenvolvedor(pessoa, projeto, valorHora, qntHoras);
		} else if (cargo.equalsIgnoreCase("gerente")) {
			return criaGerente(pessoa, projeto, valorHora, qntHoras);
		} else if (cargo.equalsIgnoreCase("pesquisador")) {
			return criaPesquisador(pessoa, projeto, valorHora, qntHoras);
		}
		throw new Exception("Tipo de profissional nao existe");
	}
	
	private Desenvolvedor criaDesenvolvedor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras){
		return new Desenvolvedor(pessoa, projeto, valorHora, qntHoras);
	}
	
	private Gerente criaGerente(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras){
		return new Gerente(pessoa, projeto, valorHora, qntHoras);
	}
	
	private Pesquisador criaPesquisador(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras){
		return new Pesquisador(pessoa, projeto, valorHora, qntHoras);
	}
}
