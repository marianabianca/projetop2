package facade;

import centralDeProjeto.CentralDeProjeto;
import exceptions.StringInvalidaException;
import pessoa.PessoaController;
import validacao.ModuloDeValidacao;

public class Facade {
	
	CentralDeProjeto central;
	PessoaController pessoaController;
	
	public Facade() {
		central = new CentralDeProjeto();
		pessoaController = new PessoaController();
	}
	
	
	public void iniciaSistema(){
		
	}
	
	public void fechaSistema(){
		
	}
	
	public String cadastraPessoa(String cpf, String nome, String email) throws StringInvalidaException{
		ModuloDeValidacao.cpfInvalido(cpf);
		ModuloDeValidacao.stringInvalida(nome);
		ModuloDeValidacao.stringInvalida(email);
		
		return pessoaController.cadastraPessoa(cpf, nome, email);
	}
	
	public void removePessoa(String cpf){
		// TODO
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws Exception{
		ModuloDeValidacao.cpfInvalido(cpf);
		ModuloDeValidacao.stringInvalida(atributo);
		atributo.toLowerCase();
		if (!(atributo.equals("nome") || atributo.equals("email"))){
			throw new Exception("Atributo inválido.");
		}
		
		return pessoaController.getInfoPessoa(cpf, atributo);
	}
	
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			String periodo, String dataInicio, int duracao){
		return "";
	}
	
	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao){
		return "";
	}
	
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao){
		return "";
	}
	
	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao){
		return "";
	}
	
	public void editaProjeto(String codigo, String atributo, int valor){
		
	}
	
	public void removeProjeto(String codigo){
		
	}
	
	public String getInfoProjeto(String codigo, String atributo){
		return "";
	}

}
