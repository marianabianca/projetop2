package projeto;

import exception.ParametroInvalidoException;

public class ProjetoCoop extends ProjetoPED {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjetoCoop(String nome, int prodTecnica, int prodAcademica, int patentes, String objetivo,
			String dataInicio, int duracao, String codigo) {
		super(nome, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao, codigo);
	}

	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ParametroInvalidoException {
		if (!(montanteBolsas > 0 && montanteCusteio > 0 && montanteCapital > 0)) {
			throw new ParametroInvalidoException("projeto do tipo Coop devem possuir todas as despesas");
		}
		
		super.atualizaBolsas(montanteBolsas);
		super.atualizaCusteio(montanteCusteio);
		super.atualizaCapital(montanteCapital);
	}

}
