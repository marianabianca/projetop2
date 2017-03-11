package Services;

import java.util.HashMap;
import java.util.Map;

import projeto.Projeto;
import projeto.ProjetoExtensao;
import projeto.ProjetoMonitoria;
import projeto.ProjetoPED;
import projeto.ProjetoPET;

public class ProjetoService {

	Map<String, Projeto> projetos;
	int contadorCodigo;

	public ProjetoService() {
		projetos = new HashMap<>();
		contadorCodigo = 0;
	}

	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) {
		String codigo = this.geraCodigo();
		Projeto monitoria = new ProjetoMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao,
				codigo);
		projetos.put(codigo, monitoria);
		return codigo;
	}

	private String geraCodigo() {
		String codigo = Integer.toString(contadorCodigo);
		contadorCodigo += 1;
		return codigo;
	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) {
		String codigo = this.geraCodigo();
		Projeto PET = new ProjetoPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao, codigo);
		projetos.put(codigo, PET);
		return codigo;
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) {
		String codigo = this.geraCodigo();
		Projeto Extensao = new ProjetoExtensao(nome, objetivo, impacto, dataInicio, duracao, codigo);
		projetos.put(codigo, Extensao);
		return codigo;
	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) {
		String codigo = this.geraCodigo();
		Projeto PED = new ProjetoPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
				duracao, codigo);
		projetos.put(codigo, PED);
		return codigo;
	}

	public void removeProjeto(String codigo) {
		this.projetos.remove(codigo);
	}

	public String getInfoProjeto(String codigo, String atributo) {
		if (atributo.equals("objetivo")) {
			return this.getObjetivo(codigo);
		} else {
			return this.getDataInicio(codigo);
		}
	}

	private String getDataInicio(String codigo) {
		Projeto projeto = projetos.get(codigo);
		return projeto.getDataInicio();
	}

	private String getObjetivo(String codigo) {
		Projeto projeto = projetos.get(codigo);
		return projeto.getObjetivo();
	}
	
	public void editaProjeto(String codigo, String atributo, String valor){
		Projeto projeto = this.getProjeto(codigo);
		if (atributo.equals("objetivo")){
			projeto.setObjetivo(valor);
		} else {
			projeto.setDataInicio(valor);
		}
	}

	private Projeto getProjeto(String codigo) {
		Projeto projeto = projetos.get(codigo);
		return projeto;	
	}
}