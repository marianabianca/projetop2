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

	/**
	 * O MÉTODO TEM COMO OBJETIVO VERIFICAR SE O CPF, NOME E EMAIL PASSADOS SÃO
	 * VÁLIDOS, CASO NÃO SEJA, ELE LANÇARÁ UMA EXCEPTION, CASO SEJA, ELE CRIARÁ
	 * UMA NOVA PESSOA E COLOCARÁ DENTRO DO MAP DE PESSOAS.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param nome
	 *            RECEBE UMA STRING QUE CORRESPONDE AO NOME PARA A NOVA PESSOA.
	 * @param email
	 *            RECEBE UMA STRING QUE CORRESPONDE AO EMAIL PARA A NOVA PESSOA.
	 * @return RETORNA O CPF DA PESSOA CRIADA.
	 * @throws Exception
	 *             CASO OS PARÂMETROS NÃO SEJAM VÁLIDOS, O SISTEMA LANÇARÁ O UMA
	 *             MENSAGEM DE ERRO.
	 */
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

	/**
	 * O MÉTODO É RESPONSÁVEL POR RETORNAR A INFORMAÇÃO PEDIDA PELO O USUÁRIO
	 * SOBRE A PESSOA, CASO O ATRIBUTO NÃO SEJA O VÁLIDO, O SISTEMA DEVERÁ
	 * LANÇAR UMA EXCEPTION.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAÇÃO BUSCADA SOBRE O
	 *            PESSOA.
	 * @return INFORMAÇÃO PEDIDA PELO USUÁRIO.
	 * @throws Exception
	 *             CASO O ATRIBUTO NÃO SEJA VÁLIDO, O SISTEMA LANÇARÁ UMA
	 *             MENSAGEM DE ERRO.
	 */
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

	/**
	 * O MÉTODO É RESPONSÁVEL POR PROCURAR UMA PESSOA EM "pessoas" A PARTIR DE
	 * UM CPF.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF DA PESSOA BUSCADA.
	 * @return RETORNA A PESSOA DESEJADA.
	 * @throws Exception
	 *             CASO O CPF NÃO CORRESPONDA A DETERMINADA PESSOA, O SISTEMA
	 *             DEVERÁ LANÇAR UMA EXCEPTION.
	 */
	public Pessoa getPessoa(String cpf) throws Exception {
		for (String cpfDaPessoa : pessoas.keySet()) {
			if (cpf.equals(cpfDaPessoa)) {
				return pessoas.get(cpfDaPessoa);
			}
		}
		throw new Exception("Pessoa nao encontrada");
	}

	/**
	 * O MÉTODO É RESPONSÁVEL POR REMOVER UMA PESSOA EM "pessoas" A PARTIR DE UM
	 * CPF.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF DA PESSOA BUSCADA.
	 * @return RETORNA A OPERAÇÃO FOI FEITA COM SUCESSO.
	 */
	public void removePessoa(String cpf) {
		this.pessoas.remove(cpf, pessoas.get(cpf));
	}

	/**
	 * O MÉTODO É RESPONSÁVEL PARA EDITAR O "atributo" DA PESSOA CORRESPONDENTE
	 * AO CPF PARA O VALOR, CASO OS PARÂMETROS NÃO SEJA O VÁLIDO, O SISTEMA
	 * DEVERÁ LANÇAR UMA EXCEPTION.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAÇÃO QUE DESEJA SER
	 *            MODIFICADA EM PESSOA.
	 * @param valor
	 *            RECEBE UMA STRING QUE CORRESPONDE PARA O QUE O ATRIBUTO SERÁ
	 *            MUDADO PESSOA.
	 * @throws Exception
	 *             CASO OS PARÂMETROS NÃO SEJAM VÁLIDOS, O SISTEMA DEVERÁ LANÇAR
	 *             UMA MENSAGEM DE ERRO
	 * 
	 */
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

	/**
	 * MÉTODO RESPONSÁVEL POR VERIFICAR SE JÁ HÁ OUTRA PESSOA COM ESSE MESMO
	 * CPF.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE A UM CPF DE UMA PESSOA.
	 * @return RETORNA SE HÁ(TRUE) OU NÃO(FALSE) ALGUMA PESSOA COM O MESMO CPF.
	 */
	private boolean contemPessoa(String cpf) {
		if (pessoas.containsKey(cpf)) {
			return true;
		}
		return false;
	}

	/**
	 * MÉTODO RESPONSÁVEL POR VALIDAR AS ENTRADAS DE "valor".
	 * 
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAÇÃO QUE DESEJA SER
	 *            MODIFICADA EM PESSOA.
	 * @param valor
	 *            RECEBE UMA STRING QUE CORRESPONDE PARA O QUE O ATRIBUTO SERÁ
	 *            MUDADO PESSOA.
	 * @throws Exception
	 */
	private void valorAtributoValidos(String atributo, String valor) throws Exception {
		if (atributo.equalsIgnoreCase("cpf")) {
			throw new Exception("CPF nao pode ser alterado");
		} else if (atributo.equalsIgnoreCase("nome")) {
			ValidaPessoa.validaNome(valor);
		} else {
			ValidaPessoa.validaEmail(valor);
		}
	}

	/**
	 * MÉTODO "calculaPontuacaoPorParticipacao" TEM COMO OBJETIVO ENCONTRAR A
	 * PESSOA ATRAVÉS DO CPF E DEPOIS DISSO CHAMAR O MÉTODO DE "Pessoa" A PARTIR
	 * DA "pessoa" ENCONTRADA.
	 * 
	 * @param cpfPessoa
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF DA PESSOA QUE DESEJA
	 *            CALCULAR.
	 * @return CHAMARÁ O MÉTODO DE "Pessoa" A PARTIR DA "pessoa" ENCONTRADA.
	 * @throws Exception
	 *             TODO FALTA DEFINIR FALTA DEFINIR FALTA DEFINIR FALTA DEFINIR
	 *             FALTA DEFINIR FALTA DEFINIR FALTA DEFINIR FALTA DEFINIR
	 */
	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws Exception {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		return pessoa.calculaPontuacaoPorParticipacao();
	}

	public double getValorBolsa(String cpfPessoa) throws Exception {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		return pessoa.getValorBolsa();
	}

}
