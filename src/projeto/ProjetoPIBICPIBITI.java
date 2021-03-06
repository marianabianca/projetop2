package projeto;

import exception.ParametroInvalidoException;

public class ProjetoPIBICPIBITI extends ProjetoPED {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjetoPIBICPIBITI(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao, String codigo) {
		super(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao, codigo);
	}

	/**
	 * Metodo responsavel por definir a atualizacao das bolsas em pibic e pibit.
	 */
	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ParametroInvalidoException {
		if (montanteBolsas == 0) {
			throw new ParametroInvalidoException(
					"projeto do tipo P&D - PIBIC ou PIBIT deve permitir despesas de bolsas");
		}
		if (montanteCapital > 0 || montanteCusteio > 0) {
			throw new ParametroInvalidoException(
					"projeto do tipo P&D - PIBIC ou PIBIT nao permite despesas de custeio ou capital");
		}

		super.atualizaBolsas(montanteBolsas);
	}

}
