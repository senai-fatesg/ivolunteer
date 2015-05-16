package br.com.ambientinformatica.ivolunteer.test;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTestIT {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testELoginSage() throws Exception {
    driver.get(baseUrl + "/ivolunteer/");
    driver.findElement(By.xpath("//input[@value='Entrar']")).click();
    //clicar no menu de Contato (Caso exista)
    driver.findElement(By.linkText("Contato")).click();
    //Limpa o campo 
    driver.findElement(By.id("formCorpo:consEmpresa_input")).clear();
    //insere um valor no input
    driver.findElement(By.id("formCorpo:consEmpresa_input")).sendKeys("Empresa a");
    
    driver.findElement(By.id("formCorpo:nome")).clear();
    driver.findElement(By.id("formCorpo:nome")).sendKeys("Ambient");
    driver.findElement(By.id("formCorpo:email")).clear();
    driver.findElement(By.id("formCorpo:email")).sendKeys("aureaqs@gmail.com");
    driver.findElement(By.id("formCorpo:j_idt29")).click();
    driver.findElement(By.xpath("//input[@value='Sair']")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
