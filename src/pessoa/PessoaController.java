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

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		try {
			Pessoa pessoa = this.getPessoa(cpf);
			if (atributo.equalsIgnoreCase("email")) {
				return pessoa.getEmail();
			} else if (atributo.equalsIgnoreCase("nome")) {
				return pessoa.getNome();
			} else if (atributo.equalsIgnoreCase("participacoes")) {
				return pessoa.getParticipacoes();
			}
		} catch (Exception e) {
			throw new Exception("Erro na consulta de pessoa: " + e.getMessage());
		}
		throw new Exception("Atributo inexistente");
	}

	public Pessoa getPessoa(String cpf) throws Exception {
		for (String cpfDaPessoa : pessoas.keySet()) {
			if (cpf.equals(cpfDaPessoa)) {
				return pessoas.get(cpfDaPessoa);
			}
		}
		throw new Exception("Pessoa nao encontrada");
	}

	public void removePessoa(String cpf) {
		this.pessoas.remove(cpf, pessoas.get(cpf));
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

	public void adicionaParticipacao(String cpfPessoa, Participacao participacao) throws Exception {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		pessoa.adicionaParticipacao(participacao);
	}

	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		pessoa.removeParticipacao(codigoProjeto);
	}

	private boolean contemPessoa(String cpf) {
		if (pessoas.containsKey(cpf)) {
			return true;
		}
		return false;
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
	
	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws Exception {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		return pessoa.calculaPontuacaoPorParticipacao();		
	}

}
