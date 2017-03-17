package projeto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import exception.ObjetoNuloException;
import participacao.Participacao;
import pessoa.Pessoa;

public class ProjetoService {

	private Map<String, Projeto> projetos;
	private int contadorCodigo;

	public ProjetoService() {
		this.projetos = new HashMap<>();
		this.contadorCodigo = 0;
	}

	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) {
		String codigo = this.geraCodigo();
		Projeto monitoria = new ProjetoMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao,
				codigo);
		projetos.put(codigo, monitoria);
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

	public void adicionaParticipacao(String cpfPessoa, String codigoProjeto, Participacao participacao)
			throws Exception {
		Projeto projeto = this.getProjeto(codigoProjeto);
		projeto.adicionaParticipacao(cpfPessoa, participacao);
	}

	public void removeProjeto(String codigo) {
		this.projetos.remove(codigo);
	}

	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		Projeto projeto = this.getProjeto(codigo);
		return projeto.getInfoProjeto(atributo);
	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		Projeto projeto = this.getProjeto(codigo);
		projeto.editaProjeto(atributo, valor);
	}

	private String geraCodigo() {
		String codigo = Integer.toString(contadorCodigo++);
		return codigo;
	}

	private Projeto getProjeto(String codigo) throws Exception {
		if (!projetos.containsKey(codigo)) {
			throw new Exception("Projeto nao encontrado");
		}
		Projeto projeto = projetos.get(codigo);
		return projeto;
	}

	private String getDataInicio(String codigo) {
		Projeto projeto = projetos.get(codigo);
		return projeto.getDataInicio();
	}

	private String getObjetivo(String codigo) {
		Projeto projeto = projetos.get(codigo);
		return projeto.getObjetivo();
	}
	
	public String getCodigoProjeto(String nome) throws ObjetoNuloException {
		Iterable<Projeto> objetosProjetos = projetos.values();
		for (Iterator<Projeto> iterator = objetosProjetos.iterator(); iterator.hasNext();) {
			Projeto projeto = (Projeto) iterator.next();
			String nomeProjeto = projeto.getNome();
			if (nomeProjeto.equals(nome)){
				return projeto.getCodigo();
			}
		}
		
		throw new ObjetoNuloException("Projeto não encontrado");
	}

}