package facade;

import centralDeProjeto.CentralDeProjeto;
import exceptions.StringInvalidaException;

public class Facade {
	
	CentralDeProjeto central;
	
	public Facade() {
		central = new CentralDeProjeto();
	}
	
	
	public void iniciaSistema(){
		
	}
	
	public void fechaSistema(){
		
	}
	
	public String cadastraPessoa(String cpf, String nome, String email) throws StringInvalidaException{
		return central.cadastraPessoa(cpf, nome, email);
	}
	
	public void removePessoa(String cpf){
		// TODO
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws Exception{
		return central.getInfoPessoa(cpf, atributo);
	}
	
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			String periodo, String dataInicio, int duracao) throws Exception {
		return central.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao);
	}
	
	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception{
		return central.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes, dataInicio, duracao);
	}
	
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) throws Exception{
		return central.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}
	
	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception{
		return central.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao);
	}
	
	public void editaProjeto(String codigo, String atributo, String valor) throws Exception{
		central.editaProjeto(codigo, atributo, valor);
	}
	
	public void removeProjeto(String codigo){
		central.removeProjeto(codigo);
	}
	
	public String getInfoProjeto(String codigo, String atributo) throws Exception{
		return central.getInfoProjeto(codigo, atributo);
	}
	
	public void associaProfessor (String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora, int qntHoras){
		central.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);
	}
	
	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras){
		central.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);
	}
	
	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora, int qntHoras){
		central.associaProfissional(cpfPessoa, codigoProjeto, cargo, valorHora, qntHoras);
	}

}
