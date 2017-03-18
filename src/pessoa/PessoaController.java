package pessoa;

import java.util.HashMap;
import java.util.Map;

import participacao.Participacao;
import validacao.ValidaPessoa;

public class PessoaController {

	private Map<String, Pessoa> pessoas;

	public PessoaController() {
		pessoas = new HashMap<>();
	}

	public String cadastraPessoa(String cpf, String nome, String email) throws Exception {
		try {
			ValidaPessoa.validaCpf(cpf);
			ValidaPessoa.validaNome(nome);
			ValidaPessoa.validaEmail(email);
			if (this.contemPessoa(cpf)) {
				throw new Exception("Pessoa com mesmo CPF ja cadastrada");
			}
			Pessoa pessoa = new Pessoa(nome, email, cpf);
			pessoas.put(cpf, pessoa);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de pessoa: " + e.getMessage());
		}
		return cpf;
	}

	private boolean contemPessoa(String cpf) {
		if (pessoas.containsKey(cpf)) {
			return true;
		}
		return false;
	}

	public void removePessoa(String cpf) {
		this.pessoas.remove(cpf, pessoas.get(cpf));
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		if (atributo.equalsIgnoreCase("email")) {
			return this.getEmailDePessoa(cpf);
		} else if (atributo.equalsIgnoreCase("nome")) {
			return this.getNomeDePessoa(cpf);
		} else if (atributo.equalsIgnoreCase("participacoes")) {
			return this.getParticipacoesDePessoa(cpf);
		}
		throw new Exception("Atributo inexistente");
	}

	private String getParticipacoesDePessoa(String cpf) throws Exception {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getParticipacoes();
	}

	public Pessoa getPessoa(String cpf) throws Exception {
		for (String cpfDaPessoa : pessoas.keySet()) {
			if (cpf.equals(cpfDaPessoa)) {
				return pessoas.get(cpfDaPessoa);
			}
		}
		throw new Exception("Erro na consulta de pessoa: Pessoa nao encontrada");
	}

	private String getEmailDePessoa(String cpf) throws Exception {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getEmail();
	}

	private String getNomeDePessoa(String cpf) throws Exception {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getNome();
	}

	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		try {
			ValidaPessoa.validaCpf(cpfPessoa);
			this.valorAtributoValidos(atributo, valor);

			Pessoa aEditar = getPessoa(cpfPessoa);
			if (atributo.equalsIgnoreCase("nome")) {
				aEditar.setNome(valor);
			} else {
				aEditar.setEmail(valor);
			}
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de pessoa: " + e.getMessage());
		}
	}

	private void valorAtributoValidos(String atributo, String valor) throws Exception {
		if (atributo.equalsIgnoreCase("cpf")) {
			throw new Exception("CPF nao pode ser alterado");
		} else if (atributo.equalsIgnoreCase("nome")) {
			ValidaPessoa.validaNome(valor);
		} else {
			ValidaPessoa.validaEmail(valor);
		}
	}

	public void adicionaParticipacao(String cpfPessoa, Participacao participacao)
			throws Exception {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		pessoa.adicionaParticipacao(participacao);
	}

	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		Pessoa pessoa = this.getPessoa(cpfPessoa);	
		pessoa.removeParticipacao(codigoProjeto);
	}
}
