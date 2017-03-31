package validacao;

import java.io.Serializable;

import exception.ParametroInvalidoException;
import exception.StringInvalidaException;

public class ValidaProjeto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModuloDeValidacao moduloDeValidacao;
	
	public ValidaProjeto() {
		this.moduloDeValidacao = new ModuloDeValidacao();
	}

	/**
	 * O MÉTODO É RESPONSÁVEL POR VERIFICAR SE O ATRIBUTO RECEBIDO É VÁLIDO.
	 * 
	 * @param atributo
	 *            ATRIBUTO A SER TESTADO.
	 * @throws Exception
	 *             EXCEÇÃO A SER LANÇADA.
	 */
	public void validaAtributo(String atributo) throws Exception {
		if ((!atributoValido(atributo))) {
			throw new Exception("Atributo nulo ou invalido");
		}

		try {
			this.moduloDeValidacao.stringInvalida(atributo);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Atributo nulo ou invalido");
		}

	}

	/**
	 * O MÉTODO É RESPONSÁVEL POR VERIFICAR SE O ATRIBUTO RECEBIDO REALMENTE
	 * CONFERE A UM ATRIBUTO DE PROJETO.
	 * 
	 * @param atributo
	 *            ATRIBUTO A SER TESTADO.
	 * @return DETERMINAR SE O ATRIBUTO REALMENTE EXISTE O ATRIBUTO PASSADO COMO
	 *         PARÂMETRO.
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

	private void validaValor(String valor, String atributo) throws Exception {
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

	public void validaNome(String nome) throws StringInvalidaException {
		try {
			this.moduloDeValidacao.stringInvalida(nome);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Nome " + e.getMessage());
		}
	}

	public void validaDisciplina(String disciplina) throws StringInvalidaException {
		try {
			this.moduloDeValidacao.stringInvalida(disciplina);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Disciplina " + e.getMessage());
		}
	}

	public void validaRendimento(int rendimento) throws Exception {
		if (rendimento < 0 || rendimento > 100) {
			throw new Exception("Rendimento invalido");
		}
	}

	public void validaObjetivo(String objetivo) throws StringInvalidaException {
		try {
			this.moduloDeValidacao.stringInvalida(objetivo);
		} catch (Exception e) {
			throw new StringInvalidaException("Objetivo " + e.getMessage());
		}
	}

	public void validaPeriodo(String periodo) throws Exception {
		try {
			this.moduloDeValidacao.stringInvalida(periodo);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Periodo " + e.getMessage());
		}

		String formaPeriodo = "[1-9][0-9]{3}\\.[1-2]";
		if (!periodo.matches(formaPeriodo)) {
			throw new Exception("Formato de periodo invalido");
		}
	}

	public void validaDuracao(int duracao) throws Exception {
		if (duracao <= 0) {
			throw new Exception("Duracao invalida");
		}
	}

	public void validaQtdHoras(int qtdHoras) throws Exception {
		if (qtdHoras <= 0) {
			throw new Exception("Quantidade de horas invalida");
		}
	}

	public void validaImpacto(int impacto) throws Exception {
		if (impacto < 1 || impacto > 6) {
			throw new Exception("Impacto invalido");
		}
	}

	public void validaProdAcademica(int prodAcademica) throws Exception {
		if (prodAcademica < 0) {
			throw new Exception("Numero de producoes academicas invalido");
		}
	}

	public void validaProdTecnica(int prodTecnica) throws Exception {
		if (prodTecnica < 0) {
			throw new Exception("Numero de producoes tecnicas invalido");
		}
	}

	public void validaPatentes(int patentes) throws Exception {
		if (patentes < 0) {
			throw new Exception("Numero de patentes invalido");
		}
	}

	public void validaCategoria(String categoria) throws Exception {
		try {
			this.moduloDeValidacao.stringInvalida(categoria);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Categoria " + e.getMessage());
		}

		if (!(categoria.equalsIgnoreCase("pibic") || categoria.equalsIgnoreCase("pibiti")
				|| categoria.equalsIgnoreCase("pivic") || categoria.equalsIgnoreCase("coop"))) {
			throw new Exception("Categoria invalida");
		}
	}

	public void validaValorAtributo(String atributo, String valor) throws Exception {
		this.validaValor(valor, atributo);
		try {
			this.moduloDeValidacao.stringInvalida(valor);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException(atributo + " " + e.getMessage());
		}
	}
	
	public void validaValorAtributo(double valor) throws ParametroInvalidoException {
		if (valor < 0) {
			throw new ParametroInvalidoException("valor negativo");
		}
	}

	public void validaValorHora(double valorHora) throws Exception {
		if (valorHora <= 0) {
			throw new Exception("Valor da hora invalido");
		}
	}

	public void validaValorHoraMenorQueZero(double valorHora) throws Exception {
		if (valorHora < 0) {
			throw new Exception("Valor da hora invalido");
		}
	}

	public void validaValorHoraDeMonitoria(double valorHora) throws Exception {
		if (valorHora != 0) {
			throw new Exception("Valor da hora de um professor da monitoria deve ser zero");
		}

	}

	public void validaCodigo(String cod) throws ParametroInvalidoException {
		try {
			this.moduloDeValidacao.stringInvalida(cod);
		} catch (Exception e) {
			throw new ParametroInvalidoException("codigo nulo ou vazio");
		}
	}

}
