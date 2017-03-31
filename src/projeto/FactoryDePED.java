package projeto;

public class FactoryDePED {
	
	public ProjetoPED criaProjetoPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo,
			String dataInicio, int duracao, String codigo) {
		if (categoria.equals("COOP")){
			return new ProjetoCoop(nome, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao, codigo);
		} else {
			return new ProjetoPIBICPIBITI(nome, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao, codigo);
		}
	}

}
