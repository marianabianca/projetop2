package projetoTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import projeto.*;
/**
 * Classe de testes para Projeto. Como as excecoes sao filtradas no controller,
 * eh possivel que sejam feitas mudancas indevidas na classe(null, vazio, etc),
 * entao o objetivo desses testes eh de apenas testar a funcionalidade das classes. 
 * @author Julio Costa
 *
 */
public class ProjetosTest {

	Projeto monitoria;
	Projeto extensao;
	Projeto ped;
	Projeto pet;
	Despesa d1;
	
	@Before
	public void setUp(){
		monitoria = new ProjetoMonitoria("Monitoria", "Laboratorio de Programacao 1", 50, "Ajudar os alunos que cursam a disciplina", "2016.2", "12/12/2016", 6, "000000");
		extensao = new ProjetoExtensao("Extensao", "Levar conhecimento da universidade para fora dela, ajudando a comunidade no geral", 4, "01/01/2016", 12, "000001");
		ped = new ProjetoPED("P&D", "Desenvolvimento", 9, 7, 5, "ganhar dinheiro", "01/01/01", 12, "000002");
		pet = new ProjetoPET("Programa de educacao tutorial", "Ajudar na graduacao", 5, 80, 8, 8, 2, "01/01/16", 12, "000003");
		d1 = new Despesa(500);
	}
	
	@Test
	public void testConstrutor(){
		Projeto proj = new ProjetoMonitoria("EXEMPLO", "EXEMPLO", 100, "EXEMPLO", "EXEMPLO", "EXEMPLO", 100, "000000");
	}
	
	@Test
	public void testSetNome(){
		Assert.assertEquals("Monitoria", monitoria.getNome());
		monitoria.setNome("Monitoria e Tutoria");
		Assert.assertEquals("Monitoria e Tutoria", monitoria.getNome());
	}
	
	@Test
	public void testCalculaCustoTotal(){
		Assert.assertEquals(0.0, monitoria.calculaCustoTotal(), 0.001);
		monitoria.adicionaDespesa(d1);
		Assert.assertEquals(500, monitoria.calculaCustoTotal(), 0.001);
	}
	
	@Test
	public void testEquals(){
		Assert.assertFalse(monitoria.equals(d1));
		Assert.assertFalse(monitoria.equals(ped));
		Assert.assertFalse(monitoria.equals(pet));
		Assert.assertTrue(monitoria.equals(monitoria));
		Projeto proj = new ProjetoMonitoria("Monitoria", "Laboratorio de Programacao 1", 50, "Ajudar os alunos que cursam a disciplina", "2016.2", "12/12/2016", 6, "000000");
		Assert.assertTrue(monitoria.equals(proj));
	}
	
	
	
}
