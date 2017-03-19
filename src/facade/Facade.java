package facade;

import pessoa.PessoaController;
import projeto.ProjetoController;
import easyaccept.EasyAccept;
import participacao.ParticipacaoController;

public class Facade {

	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ParticipacaoController participacaoController;

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us1_test_exception.txt",
				"acceptance_test/us2_test.txt", "acceptance_test/us2_test_exception.txt",
				"acceptance_test/us3_test.txt", "acceptance_test/us3_test_exception.txt",
				"acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt" };
		EasyAccept.main(args);
	}

	public Facade() {
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
		participacaoController = new ParticipacaoController(pessoaController, projetoController);
	}

	public void iniciaSistema() {
		// TODO
	}

	public void fechaSistema() {
		// TODO
	}

	public String cadastraPessoa(String cpf, String nome, String email) throws Exception {
		return pessoaController.cadastraPessoa(cpf, nome, email);
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}

	public void editaPessoa(String cpf, String atributo, String valor) throws Exception {
		pessoaController.editaPessoa(cpf, atributo, valor);
	}

	public void removePessoa(String cpf) {
		pessoaController.removePessoa(cpf);
	}

	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,
				duracao);
	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao);
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		return projetoController.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo,
				dataInicio, duracao);
	}

	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		return projetoController.getInfoProjeto(codigo, atributo);
	}

	public String getCodigoProjeto(String nome) throws Exception {
		return projetoController.getCodigoProjeto(nome);
	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		projetoController.editaProjeto(codigo, atributo, valor);
	}

	public void removeProjeto(String codigo) {
		projetoController.removeProjeto(codigo);
	}

	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora,
			int qntHoras) throws Exception {
		participacaoController.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);
	}

	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
			throws Exception {
		participacaoController.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);
	}

	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws Exception {
		participacaoController.associaProfissional(cpfPessoa, codigoProjeto, cargo, valorHora, qntHoras);
	}

	public void associaPosGraduando(String cpfPessoa, String codigoProjeto, String vinculo, double valorHora,
			int qntHoras) throws Exception {
		participacaoController.associaPosGraduando(cpfPessoa, codigoProjeto, vinculo, valorHora, qntHoras);
	}

	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		participacaoController.removeParticipacao(cpfPessoa, codigoProjeto);
	}

	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws Exception {
		return pessoaController.calculaPontuacaoPorParticipacao(cpfPessoa);
	}
	
	public double getValorBolsa(String cpfPessoa) throws Exception {
		return pessoaController.getValorBolsa(cpfPessoa);
	}

}
