package br.com.dbserver.votenorestaurante.aceitacao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VotoTest {
	

	private static final String PADOKA = "Padoka";
	private static final String MICHELL = "Michell";
	private static final String MANIA_DE_CHURRASCO = "Mania de Churrasco";
	private static final String LE_CHEF = "Le Chef";
	private static final String LUIZ = "Luiz";
	private static final String CASSIO = "Cássio";
	private static final String MURILO = "Murilo";
	private static VotoPage votoPage;

	@BeforeClass
	public  static void inicializarDriver(){
		votoPage = new VotoPage();
		votoPage.inicializaChromeDriver();
		votoPage.acessarTelaInicial();
		
		votoPage.selecionaProfissional(CASSIO);
		votoPage.selecionaRestaurante(LE_CHEF);
		votoPage.votar();
		votoPage.acessarTelaInicial();
		
		votoPage.selecionaProfissional(MURILO);
		votoPage.selecionaRestaurante(LE_CHEF);
		votoPage.votar();
		votoPage.acessarTelaInicial();
		
		votoPage.selecionaProfissional(LUIZ);
		votoPage.selecionaRestaurante(MANIA_DE_CHURRASCO);
		votoPage.votar();
		votoPage.acessarTelaInicial();
	}
	

	@Before
	public void inicioTestes(){
		votoPage.acessarTelaInicial();
	}
	
	
	@AfterClass
	public static void finalizarDriver(){
		votoPage.encerrarChromeDriver();
	}
	
	@Test
	public void deveSerPossivelRealizarVoto(){
		votoPage.selecionaProfissional(MICHELL);
		votoPage.selecionaRestaurante(PADOKA);
		votoPage.votar();
		
		assertTrue(votoPage.votouComSucesso());
	}
	
	@Test
	public void naoDevePermitirProfissionalVotarDuasVezes(){
		profissionalCassioVotaLeChef();
		
		assertTrue(votoPage.bloqueouVotoIndevido());
	}
	
	@Test
	public void deveSerPossivelVerRestauranteVencedor(){
		votoPage.acessarTelaResultado();
		
//		assertTrue(votoPage.elegeuVencedor());
		assertEquals(LE_CHEF, votoPage.mensagemVencedor());
	}


	private void profissionalCassioVotaLeChef() {
		votoPage.selecionaProfissional(CASSIO);
		votoPage.selecionaRestaurante(LE_CHEF);
		votoPage.votar();
	}
	
	
	

}
