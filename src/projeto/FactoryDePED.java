package projeto;

import java.io.Serializable;

public class FactoryDePED implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo responsavel pela criacao de um Projeto PED
	 * 
	 * @param nome
	 *            - Nome do projeto.
	 * @param categoria
	 *            - Categoria do projeto.
	 * @param prodTecnica
	 *            - Producao Tecnica.
	 * @param prodAcademica
	 *            - Producao Academica
	 * @param patentes
	 *            - Patentes.
	 * @param objetivo
	 *            - Objetivo.
	 * @param dataInicio
	 *            - Data de incio.
	 * @param duracao
	 *            - Duracao.
	 * @param codigo
	 *            - Codigo do projeto.
	 * @return - Novo projeto do tipo cooperativo ou PIBICPIBIT.
	 */
	public ProjetoPED criaProjetoPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao, String codigo) {
		if (categoria.equals("COOP")) {
			return new ProjetoCoop(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao,
					codigo);
		} else {
			return new ProjetoPIBICPIBITI(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
					duracao, codigo);
		}
	}

}
