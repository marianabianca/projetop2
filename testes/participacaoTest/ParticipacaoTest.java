package participacaoTest;

import participacao.Participacao;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
/**
 * Classe de testes para Participacao. Como as excecoes sao filtradas no controller,
 * eh possivel que sejam feitas mudancas indevidas na classe(null, vazio, etc),
 * entao o objetivo desses testes eh de apenas testar a funcionalidade das classes. 
 * @author Julio Costa
 *
 */
public class ParticipacaoTest {
	Participacao part1;
	
	@Before
	public void setUp(){
		part1 = new Participacao("12/03/2017", 12, 20, 20);
	}
	
	@Test
	public void testConstrutor(){
		part1 = new Participacao("", -1, -1, -1);
		part1 = new Participacao("aa/bb/cc", 0, 0, 0);
	}
}
