package validacao;

import java.util.HashSet;

public class ValidaProjeto {

	public static void validaAtributo(String atributo) {
		if (!ValidaProjeto.atributoValido(atributo)){
			
		}
		
	}

	// TODO HASHSET
	private static boolean atributoValido(String atributo) {
		HashSet<String> atributosValidos = new HashSet<>();
		atributosValidos.add("nome");
		atributosValidos.add("disciplina");
		atributosValidos.add("rendimento");
		atributosValidos.add("objetivo");
		atributosValidos.add("periodo");
		atributosValidos.add("data de inicio");
		atributosValidos.add("duracao");
		atributosValidos.add("impacto");
		atributosValidos.add("producao tecnica");
		atributosValidos.add("producao academica");
		atributosValidos.add("patentes");
		atributosValidos.add("categoria");

		if (atributosValidos.contains(atributo)){
			return true;
		}
		return false;
	}
	
	

}
