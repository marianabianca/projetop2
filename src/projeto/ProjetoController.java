package projeto;

import exception.ObjetoNuloException;
import projeto.ProjetoService;
import validacao.ModuloDeValidacao;
import validacao.ValidaProjeto;

public class ProjetoController {
	private ProjetoService projetoService;
	
	public ProjetoController(){
		projetoService = new ProjetoService();
	}	
	
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		try {
			ValidaProjeto.validaNome(nome);
			ValidaProjeto.validaDisciplina(disciplina);
			ValidaProjeto.validaRendimento(rendimento);
			ValidaProjeto.validaObjetivo(objetivo);
			ValidaProjeto.validaPeriodo(periodo);
			ModuloDeValidacao.dataInvalida(dataInicio);
			ValidaProjeto.validaDuracao(duracao);
			return projetoService.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		try {
			ValidaProjeto.validaNome(nome);
			ValidaProjeto.validaObjetivo(objetivo);
			ValidaProjeto.validaImpacto(impacto);
			ValidaProjeto.validaRendimento(rendimento);
			ValidaProjeto.validaProdTecnica(prodTecnica);
			ValidaProjeto.validaProdTecnica(prodTecnica);
			ValidaProjeto.validaPatentes(patentes);
			ModuloDeValidacao.dataInvalida(dataInicio);
			ValidaProjeto.validaDuracao(duracao);
			return projetoService.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
					dataInicio, duracao);	
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

		
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		try {
			ValidaProjeto.validaNome(nome);
			ValidaProjeto.validaObjetivo(objetivo);
			ValidaProjeto.validaImpacto(impacto);
			ModuloDeValidacao.dataInvalida(dataInicio);
			ValidaProjeto.validaDuracao(duracao);
			return projetoService.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		try {
			ValidaProjeto.validaNome(nome);
			ValidaProjeto.validaCategoria(categoria);
			ValidaProjeto.validaProdTecnica(prodTecnica);
			ValidaProjeto.validaProdAcademica(prodAcademica);
			ValidaProjeto.validaPatentes(patentes);
			ValidaProjeto.validaObjetivo(objetivo);
			ModuloDeValidacao.dataInvalida(dataInicio);
			ValidaProjeto.validaDuracao(duracao);
			return projetoService.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
					duracao);			
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		try {
			ValidaProjeto.validaAtributo(atributo);
			ValidaProjeto.validaValorAtributo(atributo, valor);
			projetoService.editaProjeto(codigo, atributo, valor);
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de projeto: " + e.getMessage());
		}
	}

	public void removeProjeto(String codigo) {
		projetoService.removeProjeto(codigo);
	}

	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		try {
			ValidaProjeto.validaAtributo(atributo);
			return projetoService.getInfoProjeto(codigo, atributo);
		} catch (Exception e) {
			throw new Exception("Erro na consulta de projeto: " + e.getMessage());
		}
	}


	public String getCodigoProjeto(String nome) throws ObjetoNuloException{
		return projetoService.getCodigoProjeto(nome);
	}
	
}
