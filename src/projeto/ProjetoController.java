package projeto;

import java.util.HashMap;
import java.util.Map;

import exception.ObjetoNuloException;
import participacao.Participacao;
import validacao.ModuloDeValidacao;
import validacao.ValidaProjeto;

public class ProjetoController {

	private Map<String, Projeto> projetos;
	private int contadorCodigo;
	private ValidaProjeto validaProjeto;
	private ModuloDeValidacao moduloDeValidacao;
	
	public ProjetoController() {
		this.projetos = new HashMap<>();
		this.contadorCodigo = 0;
		
		this.validaProjeto= new ValidaProjeto();
		this.moduloDeValidacao = new ModuloDeValidacao();
	}

	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		try {
			this.validaProjeto.validaNome(nome);
			this.validaProjeto.validaDisciplina(disciplina);
			this.validaProjeto.validaRendimento(rendimento);
			this.validaProjeto.validaObjetivo(objetivo);
			this.validaProjeto.validaPeriodo(periodo);
			this.moduloDeValidacao.dataInvalida(dataInicio);
			this.validaProjeto.validaDuracao(duracao);
			String codigo = this.geraCodigo();
			Projeto monitoria = new ProjetoMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,
					duracao, codigo);
			projetos.put(codigo, monitoria);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		try {
			this.validaProjeto.validaNome(nome);
			this.validaProjeto.validaObjetivo(objetivo);
			this.validaProjeto.validaImpacto(impacto);
			this.validaProjeto.validaRendimento(rendimento);
			this.validaProjeto.validaProdTecnica(prodTecnica);
			this.validaProjeto.validaProdTecnica(prodTecnica);
			this.validaProjeto.validaPatentes(patentes);
			this.moduloDeValidacao.dataInvalida(dataInicio);
			this.validaProjeto.validaDuracao(duracao);
			String codigo = this.geraCodigo();
			Projeto pet = new ProjetoPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
					dataInicio, duracao, codigo);
			projetos.put(codigo, pet);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		try {
			this.validaProjeto.validaNome(nome);
			this.validaProjeto.validaObjetivo(objetivo);
			this.validaProjeto.validaImpacto(impacto);
			this.moduloDeValidacao.dataInvalida(dataInicio);
			this.validaProjeto.validaDuracao(duracao);
			String codigo = this.geraCodigo();
			Projeto extensao = new ProjetoExtensao(nome, objetivo, impacto, dataInicio, duracao, codigo);
			projetos.put(codigo, extensao);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}
	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		try {
			this.validaProjeto.validaNome(nome);
			this.validaProjeto.validaCategoria(categoria);
			this.validaProjeto.validaProdTecnica(prodTecnica);
			this.validaProjeto.validaProdAcademica(prodAcademica);
			this.validaProjeto.validaPatentes(patentes);
			this.validaProjeto.validaObjetivo(objetivo);
			this.moduloDeValidacao.dataInvalida(dataInicio);
			this.validaProjeto.validaDuracao(duracao);
			String codigo = this.geraCodigo();
			Projeto ped = new ProjetoPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
					duracao, codigo);
			projetos.put(codigo, ped);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}
	}

	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		try {
			this.validaProjeto.validaAtributo(atributo);
			Projeto projeto = this.getProjeto(codigo);
			return projeto.getInfoProjeto(atributo);
		} catch (Exception e) {
			throw new Exception("Erro na consulta de projeto: " + e.getMessage());
		}
	}

	public String getCodigoProjeto(String nome) throws ObjetoNuloException {
		for (Projeto projeto : projetos.values()) {
			if (projeto.getNome().equals(nome)) {
				return projeto.getCodigo();
			}
		}
		throw new ObjetoNuloException("Erro na obtencao de codigo de projeto: Projeto nao encontrado");
	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		try {
			this.validaProjeto.validaAtributo(atributo);
			this.validaProjeto.validaValorAtributo(atributo, valor);
			Projeto projeto = this.getProjeto(codigo);
			projeto.editaProjeto(atributo, valor);
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de projeto: " + e.getMessage());
		}
	}

	public void removeProjeto(String codigo) {
		this.projetos.remove(codigo);
	}

	public void adicionaParticipacao(String codigoProjeto, Participacao participacao) throws Exception {
		Projeto projeto = getProjeto(codigoProjeto);
		projeto.adicionaParticipacao(participacao);
	}

	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		try {
			Projeto projeto = this.getProjeto(codigoProjeto);
			projeto.removeParticipacao(cpfPessoa);
		} catch (Exception e) {
			throw new Exception("Erro na remocao de participacao: " + e.getMessage());
		}
	}

	private String geraCodigo() {
		String codigo = Integer.toString(contadorCodigo++);
		return codigo;
	}

	public Projeto getProjeto(String codigo) throws Exception {
		if (!projetos.containsKey(codigo)) {
			throw new Exception("Projeto nao encontrado");
		}
		Projeto projeto = projetos.get(codigo);
		return projeto;
	}

	public boolean existeProjeto(String codigoProjeto) {
		return projetos.containsKey(codigoProjeto);
	}

}
