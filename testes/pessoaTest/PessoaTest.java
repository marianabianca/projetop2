package pessoaTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import pessoa.Pessoa;

/**
 * Classe de testes para Pessoa. Como as excecoes sao filtradas no controller,
 * eh possivel que sejam feitas mudancas indevidas nas classes basicas(nome
 * vazio, null, etc) entao apenas a funcionalidade dos metodos esta sendo
 * verificada.
 * 
 * @author Julio Costa
 */
public class PessoaTest {

	Pessoa pessoa1;
	Pessoa pessoa2;
	Pessoa pessoa3;
	Pessoa test;

	@Before
	public void setUp() {
		pessoa1 = new Pessoa("Fulano", "fulano@exemplo.com", "000.000.000-00");
		pessoa2 = new Pessoa("Cicrano", "cicrano@exemplo.com", "000.000.000-00");
		pessoa3 = new Pessoa("Beltrano", "beltrano@exemplo.com", "000.000.000-00");
	}

	@Test
	public void testConstrutor() {
		test = new Pessoa("Teste", "teste@exemplo.com", "010.010.010.01");
		test = new Pessoa("", "", "");
	}

	@Test
	public void testSetNome() {
		Assert.assertEquals("Fulano", pessoa1.getNome());
		pessoa1.setNome("Jose");
		Assert.assertEquals("Jose", pessoa1.getNome());

		Assert.assertEquals("Cicrano", pessoa2.getNome());
		pessoa2.setNome("Jose");
		Assert.assertEquals("Jose", pessoa2.getNome());

		Assert.assertEquals("Beltrano", pessoa3.getNome());
		pessoa3.setNome("Jose");
		Assert.assertEquals("Jose", pessoa3.getNome());

	}

	@Test
	public void testSetCpf() {
		Assert.assertEquals("000.000.000-00", pessoa1.getCpf());
		pessoa1.setCpf("111.111.111-11");
		Assert.assertEquals("111.111.111-11", pessoa1.getCpf());

		Assert.assertEquals("000.000.000-00", pessoa2.getCpf());
		pessoa2.setCpf("111.111.111-11");
		Assert.assertEquals("111.111.111-11", pessoa2.getCpf());

		Assert.assertEquals("000.000.000-00", pessoa3.getCpf());
		pessoa3.setCpf("111.111.111-11");
		Assert.assertEquals("111.111.111-11", pessoa3.getCpf());
	}

	@Test
	public void testSetEmail() {
		Assert.assertEquals("fulano@exemplo.com", pessoa1.getEmail());
		pessoa1.setEmail("algumacoisa@exemplo.com");
		Assert.assertEquals("algumacoisa@exemplo.com", pessoa1.getEmail());

		Assert.assertEquals("cicrano@exemplo.com", pessoa2.getEmail());
		pessoa2.setEmail("algumacoisa@exemplo.com");
		Assert.assertEquals("algumacoisa@exemplo.com", pessoa2.getEmail());

		Assert.assertEquals("beltrano@exemplo.com", pessoa3.getEmail());
		pessoa3.setEmail("algumacoisa@exemplo.com");
		Assert.assertEquals("algumacoisa@exemplo.com", pessoa3.getEmail());

	}

	@Test
	public void testEquals() {
		Assert.assertTrue(pessoa1.equals(pessoa2));
		pessoa1.setCpf("111.111.111-11");
		Assert.assertFalse(pessoa1.equals(pessoa2));

	}

	@Test
	public void testToString() {
		Assert.assertEquals("Nome: Fulano, e-mail: fulano@exemplo.com, CPF: 000.000.000-00", pessoa1.toString());
		pessoa1.setNome("Otavio");
		Assert.assertEquals("Nome: Otavio, e-mail: fulano@exemplo.com, CPF: 000.000.000-00", pessoa1.toString());
		pessoa1.setEmail("otavio@exemplo.com");
		Assert.assertEquals("Nome: Otavio, e-mail: otavio@exemplo.com, CPF: 000.000.000-00", pessoa1.toString());
		pessoa1.setCpf("111.111.111-11");
		Assert.assertEquals("Nome: Otavio, e-mail: otavio@exemplo.com, CPF: 111.111.111-11", pessoa1.toString());
	}

}
