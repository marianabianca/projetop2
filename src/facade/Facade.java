package facade;

import centralDeProjeto.CentralDeProjeto;

public class Facade {
	
	CentralDeProjeto central;
	
	public Facade() {
		central = new CentralDeProjeto();
	}
	
	
	public void iniciaSistema(){
		
	}
	
	public void fechaSistema(){
		
	}
	
	public String cadastraPessoa(String cpf, String nome, String email){
		return "";
	}
	
	public void removePessoa(String cpf){
		
	}
	
	public String getInfoPessoa(String cpf, String atributo){
		return "";
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
