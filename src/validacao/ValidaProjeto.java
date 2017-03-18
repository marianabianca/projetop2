package validacao;

import exception.StringInvalidaException;

public class ValidaProjeto {

	public static void validaAtributo(String atributo) throws Exception {
		if ((!atributoValido(atributo))) {
			throw new Exception("Atributo nulo ou invalido");
		}

		try {
			ModuloDeValidacao.stringInvalida(atributo);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Atributo nulo ou invalido");
		}

	}

	private static boolean atributoValido(String atributo) {
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

	private static void validaValor(String valor, String atributo) throws Exception {
		if (atributo.equalsIgnoreCase("nome")) {
			ValidaProjeto.validaNome(valor);
		} else if (atributo.equalsIgnoreCase("disciplina")) {
			ValidaProjeto.validaDisciplina(valor);
		} else if (atributo.equalsIgnoreCase("rendimento")) {
			ValidaProjeto.validaRendimento(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			ValidaProjeto.validaObjetivo(valor);
		} else if (atributo.equalsIgnoreCase("periodo")) {
			ValidaProjeto.validaPeriodo(valor);
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			ModuloDeValidacao.dataInvalida(valor);
		} else if (atributo.equalsIgnoreCase("duracao")) {
			ValidaProjeto.validaDuracao(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("impacto")) {
			ValidaProjeto.validaImpacto(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("producao tecnica")) {
			ValidaProjeto.validaProdTecnica(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("producao academica")) {
			ValidaProjeto.validaProdAcademica(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("patentes")) {
			ValidaProjeto.validaProdAcademica(Integer.parseInt(valor));
		} else {
			ValidaProjeto.validaCategoria(valor);
		}
	}

	public static void validaNome(String nome) throws StringInvalidaException {
		try {
			ModuloDeValidacao.stringInvalida(nome);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Nome " + e.getMessage());
		}
	}

	public static void validaDisciplina(String disciplina) throws StringInvalidaException {
		try {
			ModuloDeValidacao.stringInvalida(disciplina);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Disciplina " + e.getMessage());
		}
	}

	public static void validaRendimento(int rendimento) throws Exception {
		if (rendimento < 0 || rendimento > 100) {
			throw new Exception("Rendimento invalido");
		}
	}

	public static void validaObjetivo(String objetivo) throws StringInvalidaException {
		try {
			ModuloDeValidacao.stringInvalida(objetivo);
		} catch (Exception e) {
			throw new StringInvalidaException("Objetivo " + e.getMessage());
		}
	}

	public static void validaPeriodo(String periodo) throws Exception {
		try {
			ModuloDeValidacao.stringInvalida(periodo);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Periodo " + e.getMessage());
		}

		String formaPeriodo = "[1-9][0-9]{3}\\.[1-2]";
		if (!periodo.matches(formaPeriodo)) {
			throw new Exception("Formato de periodo invalido");
		}
	}

	public static void validaDuracao(int duracao) throws Exception {
		if (duracao <= 0) {
			throw new Exception("Duracao invalida");
		}
	}

	public static void validaQtdHoras(int qtdHoras) throws Exception {
		if (qtdHoras <= 0) {
			throw new Exception("Quantidade de horas invalida");
		}
	}

	public static void validaImpacto(int impacto) throws Exception {
		if (impacto < 1 || impacto > 6) {
			throw new Exception("Impacto invalido");
		}
	}

	public static void validaProdAcademica(int prodAcademica) throws Exception {
		if (prodAcademica < 0) {
			throw new Exception("Numero de producoes academicas invalido");
		}
	}

	public static void validaProdTecnica(int prodTecnica) throws Exception {
		if (prodTecnica < 0) {
			throw new Exception("Numero de producoes tecnicas invalido");
		}
	}

	public static void validaPatentes(int patentes) throws Exception {
		if (patentes < 0) {
			throw new Exception("Numero de patentes invalido");
		}
	}

	public static void validaCategoria(String categoria) throws Exception {
		try {
			ModuloDeValidacao.stringInvalida(categoria);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Categoria " + e.getMessage());
		}

		if (!(categoria.equalsIgnoreCase("pibic") || categoria.equalsIgnoreCase("pibiti")
				|| categoria.equalsIgnoreCase("pivic") || categoria.equalsIgnoreCase("coop"))) {
			throw new Exception("Categoria invalida");
		}
	}

	public static void validaValorAtributo(String atributo, String valor) throws Exception {
		ValidaProjeto.validaValor(valor, atributo);
		try {
			ModuloDeValidacao.stringInvalida(valor);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException(atributo + " " + e.getMessage());
		}
	}

	public static void validaValorHora(double valorHora) throws Exception {
		if (valorHora <= 0) {
			throw new Exception("Valor da hora invalido");
		}	
	}

	public static void validaValorHoraDeCoordenador(double valorHora) throws Exception {
		if (valorHora < 0) {
			throw new Exception("Valor da Hora invalido");
		}
	}

}
