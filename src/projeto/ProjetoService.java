package projeto;

import java.util.HashMap;
import java.util.Map;

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
		if (atributo.equals("nome")){
			return projeto.getNome();
		} else if (atributo.equals("data de inicio")){
			return projeto.getDataInicio();
		} else if (atributo.equals("duracao")) {
			return Integer.toString(projeto.getDuracao());
		} else if (atributo.equals("objetivo")) {
			return projeto.getObjetivo();
			
		} else if(projeto.getClass() == ProjetoPET.class){
			
			ProjetoPET pet = (ProjetoPET) projeto;
			if (atributo.equals("producao tecnica")){
				return Integer.toString(pet.getProdTecnica());
			} else if (atributo.equals("producao academica")){
				return Integer.toString(pet.getProdAcademica());
			} else if (atributo.equals("patentes")){
				return Integer.toString(pet.getPatentes());
			} else if (atributo.equals("rendimento")){
				return Integer.toString(pet.getRendimento());
			} else if (atributo.equals("impacto")){
				return Integer.toString(pet.getImpacto());
			}
			
		} else if(projeto.getClass() == ProjetoPED.class){
			
			ProjetoPED ped = (ProjetoPED) projeto;
			if (atributo.equals("producao tecnica")){
				return Integer.toString(ped.getProdTecnica());
			} else if (atributo.equals("producao academica")){
				return Integer.toString(ped.getProdAcademica());
			} else if (atributo.equals("patentes")){
				return Integer.toString(ped.getPatentes());
				
			}
			
		} else if(projeto.getClass() == ProjetoExtensao.class) {
			ProjetoExtensao extensao = (ProjetoExtensao) projeto;
			if (atributo.equals("impacto")){
				return Integer.toString(extensao.getImpacto());
			}
			
		} else if (projeto.getClass() == ProjetoMonitoria.class) {
			ProjetoMonitoria monitoria = (ProjetoMonitoria) projeto;
			if (atributo.equals("rendimento")){
				return Integer.toString(monitoria.getRendimento());
			}
		}
		
		throw new Exception("Atributo nao existe");
	}

	public void editaProjeto(String codigo, String atributo, String valor) {
		Projeto projeto = this.getProjeto(codigo);
		if (atributo.equals("objetivo")) {
			projeto.setObjetivo(valor);
		} else if (atributo.equals("data de inicio")) {
			projeto.setDataInicio(valor);
		} else {
			projeto.setDataInicio(valor);
		}
	}

	private String geraCodigo() {
		String codigo = Integer.toString(contadorCodigo++);
		return codigo;
	}

	private Projeto getProjeto(String codigo) {
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

	public String getCodigoProjeto(String nome) throws Exception {
		for (String chave : projetos.keySet()) {
			if (projetos.get(chave).getNome().equals(nome)){
				return projetos.get(chave).getCodigo();
			}
		}
		//TODO
		throw new Exception("Projeto nao encontrado");
	}

}