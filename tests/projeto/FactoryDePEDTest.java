package projeto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FactoryDePEDTest {

	FactoryDePED factory;
	
	@Before
	public void setup() {
		factory = new FactoryDePED();
	}
	
	@Test
	public void test() {
		Projeto projetoCoop = factory.criaProjetoPED("projCoop", "COOP", 0, 0, 0, "pesquisa", "20/10/2015", 12, "1");
		Assert.assertEquals(true, projetoCoop instanceof ProjetoCoop);
		
		Projeto projetoPIBIC = factory.criaProjetoPED("projPIBIC", "PIBIC", 0, 0, 0, "pesquisa", "20/10/2015", 12, "2");
		Assert.assertEquals(true, projetoPIBIC instanceof ProjetoPIBICPIBITI);
	}

}
