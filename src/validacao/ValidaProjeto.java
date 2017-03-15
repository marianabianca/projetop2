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
		atributosValidos = ("nome", "disciplina", "rendimento", "objetivo", "periodo",
                "data de inicio", "duracao", "impacto", "producao tecnica",
                "producao academica", "patentes", "categoria");
	}
	
	

}
