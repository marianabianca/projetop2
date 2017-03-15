package facade;

import centraldeprojeto.CentralDeProjeto;
import easyaccept.EasyAccept;
import pessoa.PessoaController;

public class Facade {

	private PessoaController pessoaController;

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us1_test_exception.txt",
				"acceptance_test/us2_test.txt", "acceptance_test/us2_test_exception.txt",
				"acceptance_test/us3_test.txt", "acceptance_test/us3_test_exception.txt" };
		EasyAccept.main(args);
	}

	public Facade() {
		pessoaController = new PessoaController();
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

	public void removePessoa(String cpf) {
		pessoaController.removePessoa(cpf);
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}
	
	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		pessoaController.editaPessoa(cpfPessoa, atributo, valor);
	}

	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		return central.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao);
	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		return central.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao);
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		return central.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		return central.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
				duracao);
	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		central.editaProjeto(codigo, atributo, valor);
	}

	public void removeProjeto(String codigo) {
		central.removeProjeto(codigo);
	}

	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		return central.getInfoProjeto(codigo, atributo);
	}

	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora,
			int qntHoras) throws Exception {
		central.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);
	}

	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
			throws Exception {
		central.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);
	}

	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws Exception {
		central.associaProfissional(cpfPessoa, codigoProjeto, cargo, valorHora, qntHoras);
	}
	
}
