package projeto;

import projeto.ProjetoService;
import validacao.ModuloDeValidacao;
import validacao.ValidaProjeto;

public class ProjetoController {
	private ProjetoService projetoService;
	private ValidaProjeto validaProjeto;
	
	public ProjetoController(){
		projetoService = new ProjetoService();
	}
	
	
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		try {
			return projetoService.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		try {
			return projetoService.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
					dataInicio, duracao);	
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

		
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		try {
			return projetoService.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		try {
			return projetoService.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
					duracao);			
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		try {
			ValidaProjeto.validaAtributo(atributo);
			atributo = atributo.toLowerCase();
			projetoService.editaProjeto(codigo, atributo, valor);
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de projeto: " + e.getMessage());
		}
	}

	public void removeProjeto(String codigo) {
		projetoService.removeProjeto(codigo);
	}

	// TODO AJEITAR OS TESTES
	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		atributo = atributo.toLowerCase();
		return projetoService.getInfoProjeto(codigo, atributo);
	}


	public String getCodigoProjeto(String nome) throws Exception {
		// ValidaProjeto.;
		// TODO Auto-generated method stub
		return projetoService.getCodigoProjeto(nome);
	}
	
}
