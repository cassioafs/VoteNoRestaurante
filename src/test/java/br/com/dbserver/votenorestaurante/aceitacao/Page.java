package br.com.dbserver.votenorestaurante.aceitacao;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	private WebDriver driver;

	public void inicializaChromeDriver(){
		System.setProperty("webdriver.chrome.driver", "c:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	public void encerrarChromeDriver(){
		driver.quit();
	}
	
	public void acessarTelaInicial(){
		driver.get("localhost:8080/");
	}
	
	protected WebElement procurarElemento(By t){
		return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(t));
	}
	
	public boolean isPossuiTodosCamposNaTela(String ... campos){
		String conteudoDaPagina = driver.getPageSource();
		
		for (String campo : campos) {
			if(!conteudoDaPagina.contains(campo)){
				return false;
			}
		}
		
		return true;
	}
	

	protected boolean validaConteudoAlert(String msg){
		Alert alert = getAlert();
		boolean isMsgCorreta = msg.equals(alert.getText());
		alert.accept();
		return isMsgCorreta;
		
	}

	protected Alert getAlert() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	
	

}
