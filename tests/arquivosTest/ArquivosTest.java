package arquivosTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import projeto.ProjetoController;

public class ArquivosTest {

	ProjetoController projController;
	
	@Before
	public void setup() {
		projController = new ProjetoController();
	}
	
	@Test
	public void test() throws IOException {
		projController.geraRelatorioProjetos();
		projController.geraRelatorioColaboracoes();
	}

}
