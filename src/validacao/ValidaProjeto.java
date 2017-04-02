package validacao;

import java.io.Serializable;

import exception.LogicaException;
import exception.ParametroInvalidoException;
import exception.StringInvalidaException;

public class ValidaProjeto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModuloDeValidacao moduloDeValidacao;

	public ValidaProjeto() {
		this.moduloDeValidacao = new ModuloDeValidacao();
	}

	/**
	 * O metodo eh responsavel por verificar se o atributo recebido eh valido.
	 * 
	 * @param atributo
	 *            - Atributo a ser testado.
	 * @throws exception
	 *             - Excecao a ser lancada.
	 */
	public void validaAtributo(String atributo) throws LogicaException, StringInvalidaException {
		if ((!atributoValido(atributo))) {
			throw new LogicaException("Atributo nulo ou invalido");
		}

		try {
			this.moduloDeValidacao.stringInvalida(atributo);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Atributo nulo ou invalido");
		}

	}

	/**
	 * O metodo eh responsavel por verificar se o atributo recebido realmente
	 * confere a um atributo de projeto.
	 * 
	 * @param atributo
	 *            - Atributo a ser testado.
	 * @return - Determinar se o atributo realmente existe o atributo passado
	 *         como parametro.
	 */
	private boolean atributoValido(String atributo) {
		String[] atributosValidos = { "nome", "disciplina", "rendimento", "objetivo", "periodo", "data de inicio",
				"duracao", "impacto", "producao tecnica", "producao academica", "patentes", "categoria",
				"participacoes" };
		for (String atributoValido : atributosValidos) {
			if (atributo.equalsIgnoreCase(atributoValido)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Metodo responsavel por observar todo tipo de excecao e delegar para os
	 * metodos menores.
	 * 
	 * @param valor
	 *            - Valor a ser analisado.
	 * @param atributo
	 *            - Tipo de valor.
	 * @throws LogicaException
	 *             - Excecao a ser lancada.
	 * @throws StringInvalidaException
	 *             - Excecao a ser lancada.
	 */
	private void validaValor(String valor, String atributo) throws LogicaException, StringInvalidaException {
		if (atributo.equalsIgnoreCase("nome")) {
			this.validaNome(valor);
		} else if (atributo.equalsIgnoreCase("disciplina")) {
			this.validaDisciplina(valor);
		} else if (atributo.equalsIgnoreCase("rendimento")) {
			this.validaRendimento(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			this.validaObjetivo(valor);
		} else if (atributo.equalsIgnoreCase("periodo")) {
			this.validaPeriodo(valor);
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			this.moduloDeValidacao.dataInvalida(valor);
		} else if (atributo.equalsIgnoreCase("duracao")) {
			this.validaDuracao(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("impacto")) {
			this.validaImpacto(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("producao tecnica")) {
			this.validaProdTecnica(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("producao academica")) {
			this.validaProdAcademica(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("patentes")) {
			this.validaProdAcademica(Integer.parseInt(valor));
		} else {
			this.validaCategoria(valor);
		}
	}

	/**
	 * Metodo responsavel por afirmar se o nome recebido eh valido
	 * 
	 * @param nome
	 *            - Nome recebido
	 * @throws StringInvalidaException
	 *             - Exception a ser lancada.
	 */
	public void validaNome(String nome) throws StringInvalidaException {
		try {
			this.moduloDeValidacao.stringInvalida(nome);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Nome " + e.getMessage());
		}
	}

	/**
	 * /** Metodo responsavel por afirmar se a disciplina recebida eh valida.
	 * 
	 * @param disciplina
	 *            - disciplina recebido
	 * @throws StringInvalidaException
	 *             - Exception a ser lancada.
	 */
	public void validaDisciplina(String disciplina) throws StringInvalidaException {
		try {
			this.moduloDeValidacao.stringInvalida(disciplina);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Disciplina " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por afirmar se o rendimento eh valido
	 * 
	 * @param rendimento
	 *            - Rendimento recebido
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaRendimento(int rendimento) throws LogicaException {
		if (rendimento < 0 || rendimento > 100) {
			throw new LogicaException("Rendimento invalido");
		}
	}

	/**
	 * Metodo responsavel por afirmar se o objetivo recebido eh valido.
	 * 
	 * @param objetivo
	 *            - Objetivo recebido
	 * @throws StringInvalidaException
	 *             - Exception a ser lancada.
	 */
	public void validaObjetivo(String objetivo) throws StringInvalidaException {
		try {
			this.moduloDeValidacao.stringInvalida(objetivo);
		} catch (Exception e) {
			throw new StringInvalidaException("Objetivo " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por afirmar se o periodo recebido eh valido.
	 * 
	 * @param periodo
	 *            - Periodo recebido.
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 * @throws StringInvalidaException
	 *             - Exception a ser lancada.
	 */
	public void validaPeriodo(String periodo) throws LogicaException, StringInvalidaException {
		try {
			this.moduloDeValidacao.stringInvalida(periodo);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Periodo " + e.getMessage());
		}

		String formaPeriodo = "[1-9][0-9]{3}\\.[1-2]";
		if (!periodo.matches(formaPeriodo)) {
			throw new LogicaException("Formato de periodo invalido");
		}
	}

	/**
	 * Metodo responsavel por afirmar se a duracao recebida eh valida.
	 * 
	 * @param duracao
	 *            - Duracao recebida
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaDuracao(int duracao) throws LogicaException {
		if (duracao <= 0) {
			throw new LogicaException("Duracao invalida");
		}
	}

	/**
	 * Metodo responsavel por afirmar se a quantidade de horas recebida eh
	 * valida.
	 * 
	 * @param qtdHoras
	 *            - Quantidade de horas recebida
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaQtdHoras(int qtdHoras) throws LogicaException {
		if (qtdHoras <= 0) {
			throw new LogicaException("Quantidade de horas invalida");
		}
	}

	/**
	 * Metodo responsavel por afirmar se o impacto recebido eh valido.
	 * 
	 * @param impacto
	 *            - Impacto recebido.
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaImpacto(int impacto) throws LogicaException {
		if (impacto < 1 || impacto > 6) {
			throw new LogicaException("Impacto invalido");
		}
	}

	/**
	 * Metodo responsavel por afirmar se a producao academica recebida eh
	 * valida.
	 * 
	 * @param prodAcademica
	 *            - Producao academica recebida
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaProdAcademica(int prodAcademica) throws LogicaException {
		if (prodAcademica < 0) {
			throw new LogicaException("Numero de producoes academicas invalido");
		}
	}

	/**
	 * Metodo responsavel por afirmar se a producao tecnica recebida eh valida.
	 * 
	 * @param prodTecnica
	 *            - Producao tecnica recebida
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaProdTecnica(int prodTecnica) throws LogicaException {
		if (prodTecnica < 0) {
			throw new LogicaException("Numero de producoes tecnicas invalido");
		}
	}

	/**
	 * Metodo responsavel por afirmar se as patentes recebidas sao validas.
	 * 
	 * @param patentes
	 *            - Patentes recebidass
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaPatentes(int patentes) throws LogicaException {
		if (patentes < 0) {
			throw new LogicaException("Numero de patentes invalido");
		}
	}

	/**
	 * Metodo responsavel por afirmar se a categoria recebida eh valida.
	 * 
	 * @param categoria
	 *            - Categoria recebida
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 * @throws StringInvalidaException
	 *             - Exception a ser lancada.
	 */
	public void validaCategoria(String categoria) throws LogicaException, StringInvalidaException {
		try {
			this.moduloDeValidacao.stringInvalida(categoria);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Categoria " + e.getMessage());
		}

		if (!(categoria.equalsIgnoreCase("pibic") || categoria.equalsIgnoreCase("pibiti")
				|| categoria.equalsIgnoreCase("pivic") || categoria.equalsIgnoreCase("coop"))) {
			throw new LogicaException("Categoria invalida");
		}
	}

	/**
	 * Metodo responsavel por afirmar se valor e atributo sao validos.
	 * 
	 * @param atributo
	 *            - Atributo recebido.
	 * @param valor
	 *            - Valor recebida
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 * @throws StringInvalidaException
	 *             - Exception a ser lancada.
	 */
	public void validaValorAtributo(String atributo, String valor) throws LogicaException, StringInvalidaException {
		this.validaValor(valor, atributo);
		try {
			this.moduloDeValidacao.stringInvalida(valor);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException(atributo + " " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por afirmar se o valor recebido eh valido.
	 * 
	 * @param valor
	 *            - Valor recebido.
	 * @throws ParametroInvalidoException
	 *             - Exception a ser lancada.
	 */
	public void validaValorAtributo(double valor) throws ParametroInvalidoException {
		if (valor < 0) {
			throw new ParametroInvalidoException("valor negativo");
		}
	}

	/**
	 * Metodo responsavel por afirmar se o valor da hora recebido eh valido.
	 * 
	 * @param valorHora
	 *            - Valor da hora recebido.
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaValorHora(double valorHora) throws LogicaException {
		if (valorHora <= 0) {
			throw new LogicaException("Valor da hora invalido");
		}
	}

	/**
	 * Metodo responsavel por afirmar se o valor da hora recebido eh valido.
	 * 
	 * @param valorHora
	 *            - Valor da hora recebido.
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaValorHoraMenorQueZero(double valorHora) throws LogicaException {
		if (valorHora < 0) {
			throw new LogicaException("Valor da hora invalido");
		}
	}

	/**
	 * Metodo responsavel por afirmar se o valor da hora recebido eh valido.
	 * 
	 * @param valorHora
	 *            - Valor da hora recebido.
	 * @throws LogicaException
	 *             - Exception a ser lancada.
	 */
	public void validaValorHoraDeMonitoria(double valorHora) throws LogicaException {
		if (valorHora != 0) {
			throw new LogicaException("Valor da hora de um professor da monitoria deve ser zero");
		}

	}

	/**
	 * Metodo responsavel por afirmar se o codigo recebido eh valido.
	 * 
	 * @param cod
	 *            - Codigo recebido.
	 * @throws ParametroInvalidoException
	 *             - Exception a ser lancada.
	 */
	public void validaCodigo(String cod) throws ParametroInvalidoException {
		try {
			this.moduloDeValidacao.stringInvalida(cod);
		} catch (Exception e) {
			throw new ParametroInvalidoException("codigo nulo ou vazio");
		}
	}

}
