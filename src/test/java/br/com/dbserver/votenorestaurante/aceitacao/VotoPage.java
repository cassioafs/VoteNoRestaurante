package br.com.dbserver.votenorestaurante.aceitacao;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class VotoPage extends Page {


	protected static final String MSG_VOTO_REALIZADO_COM_SUCESSO = "Voto realizado com sucesso";
	protected static final String MSG_PROFISSINAL_JA_VOTOU = "Esse Profissional Faminto já fez sua escolha do dia!";
	private static final String TELA_RESULTADO = "http://localhost:8080/resultado";
	private static final String ID_VENCEDOR = "vencedor";
	private static final String ID_MSG_SUCESSO = "msgSucesso";
	private static final String ID_MSG_ERROR = "msgError";
	private static final String COMPRADOR_NAO_POSSUI_CREDITO = "Comprador não possui crédito suficiente!!!";
	private static final String COMPRA_FINALIZADA_COM_SUCESSO = "Compra finalizada com sucesso!!!";
	private static final String FINALIZAR_PEDIDO = "finalizarPedido";
	private static final String VOTAR = "votar";
	private static final String RESTAURANTE = "restaurante";
	private static final String PROFISSIONAL = "profissional";

	public void selecionaProfissional(String nomeProfissional) {
		Select select = new Select(procurarElemento(By.id(PROFISSIONAL)));
		select.selectByVisibleText(nomeProfissional);
	}

	public void selecionaRestaurante(String nomeRestaurante) {
		Select select = new Select(procurarElemento(By.id(RESTAURANTE)));
		select.selectByVisibleText(nomeRestaurante);
	}
	


	public void votar() {
		procurarElemento(By.id(VOTAR)).click();
	}

	public boolean votouComSucesso() {
		
		return MSG_VOTO_REALIZADO_COM_SUCESSO.equals(procurarElemento(By.id(ID_MSG_SUCESSO)).getText());
	}
	
	public boolean bloqueouVotoIndevido() {
		
		return MSG_PROFISSINAL_JA_VOTOU.equals(procurarElemento(By.id(ID_MSG_ERROR)).getText());
	}

	public void finalizarPedido() {
		procurarElemento(By.id(FINALIZAR_PEDIDO)).click();		
	}
	
	public boolean isPedidoFinalizado() {
		return validaConteudoAlert(COMPRA_FINALIZADA_COM_SUCESSO);
		
	}
	
	public boolean isPedidoNaoPodeSerRealizado() {
		return validaConteudoAlert(COMPRADOR_NAO_POSSUI_CREDITO);
	}

	public void acessarTelaResultado() {
		getDriver().get(TELA_RESULTADO);
	}

	public String mensagemVencedor() {
		return  procurarElemento(By.id(ID_VENCEDOR)).getText();
	}

	
}
