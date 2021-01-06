import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.util.concurrent.TimeUnit

import org.openqa.selenium.interactions.Action
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.testng.Assert

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class WebsiteLoginTest {

	static WebDriver driver
	String price

	@Given("Navigate to home page")
	def navigate_to_home_page() {
		System.setProperty("webdriver.chrome.driver","C:\\KAtalon\\chromedriver.exe")
		driver = new ChromeDriver()
		driver.navigate().to('http://automationpractice.com/index.php')
		driver.manage().window().maximize()
	}

	@And("click signIn button")
	def click_signIn_button() {
		driver.findElement(By.className("login")).click()
		Thread.sleep(2000)
	}

	@When("Entered valid (.*) and (.*) under Already Registered field")
	def enter_login_details(String usermail, String password){
		driver.findElement(By.id("email")).sendKeys(usermail)
		driver.findElement(By.id("passwd")).sendKeys(password)
	}

	@And("Click on Sign In button")
	def click_on_sign_in(){
		driver.findElement(By.id("SubmitLogin")).click()
	}

	@When("Click on the Women section")
	def click_on_women_button(){
		Thread.sleep(2000)
		driver.findElement(By.className("sf-with-ul")).click()
	}

	@And("select two (.*) from styles")
	def select_options_from_styles(String options){
		list_selection_of_styles(options, "ul_layered_id_feature_6")
	}
	

	@And("select option 5 from the sortby dropdown")
	def sortby_select(){
		driver.findElement(By.xpath("//*[@id=\"uniform-selectProductSort\"]/select/option[6]")).click()
		Thread.sleep(2000)
	}

	@When("(.*) name is searched")
	def product_search_check(String product){
		driver.findElement(By.id("search_query_top")).sendKeys(product)
		Thread.sleep(2000)
		driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click()
	}

	@And("the product is selected under Quick View")
	def quick_view(){
		Actions builder = new Actions(driver)
		WebElement Product = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a"))
		builder.moveToElement(Product).build().perform()
		WebElement View = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[2]/span"))
		builder.moveToElement(View).build().perform()
		View.click()
	}

	@And("quantity is set to '2' and click on add to cart")
	def iframe_add_to_cart(){
		WebElement iframeElement = driver.findElement(By.className("fancybox-iframe"));
		driver.switchTo().frame(iframeElement);
		Thread.sleep(2000)
		price = driver.findElement(By.id("our_price_display")).getText()
		driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/a[2]")).click()
		driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]")).click()
		Thread.sleep(2000)
		
	}

	@And("verification message is displayed and quick view window is closed")
	def product_verification(){
		WebDriverWait w = new WebDriverWait(driver,10);
		w.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector("h2")));
		String t = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText().trim()
		Assert.assertTrue(t.contains("Product successfully added to your shopping cart"))
		driver.findElement(By.className("cross")).click()
		
	
	}
	
	@When("Click on 'cart' available on the top right corner")
	def cart_page(){
		//click on cart
		driver.findElement(By.className("ajax_cart_product_txt_s")).click()
		//check title
		String title = driver.findElement(By.className("page-heading")).getText()
		Assert.assertTrue(title.contains("SHOPPING-CART SUMMARY"))
		//check summary
		WebElement Summary = driver.findElement(By.xpath("//*[@id=\"order_step\"]/li[1]"))
		String Class = Summary.getAttribute('class')
		Assert.assertTrue(Class.toLowerCase().contains("current".toLowerCase())) 
		//name of dress
		String dress = driver.findElement(By.xpath("//*[@id=\"product_1_1_0_425211\"]/td[2]//a")).getText()
		Assert.assertTrue(dress.equals("Faded Short Sleeve T-shirts"))
		//quantity of dress
		Assert.assertNotNull(By.xpath("//*[@id=\"product_1_1_0_425211\"]/td[5]/input[2]"))
		//price of dress
		String c = driver.findElement(By.id("product_price_1_1_425211")).getText()
		Assert.assertTrue(c.equals(price))
		
	}
	
	@And("Proceed to checkout")
	def checkout(){
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click()
		//check Address
		WebElement Address = driver.findElement(By.xpath("//*[@id=\"order_step\"]/li[3]"))
		String Class = Address.getAttribute('class')
		Assert.assertTrue(Class.toLowerCase().contains("current".toLowerCase()))
		
		
		//Proceed checkout
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click()
		//check shipment
		WebElement Shipping = driver.findElement(By.xpath("//*[@id=\"order_step\"]/li[4]"))
		String Class_ = Shipping.getAttribute('class')
		Assert.assertTrue(Class_.toLowerCase().contains("current".toLowerCase()))
		//check step done of address
		WebElement StepDoneAddress = driver.findElement(By.xpath("//*[@id=\"order_step\"]/li[3]"))
		String Class__ = StepDoneAddress.getAttribute('class')
		Assert.assertTrue(Class__.toLowerCase().contains("step_done".toLowerCase()))
		
	} 
	
	@When("terms of service checkbox not selected")
	def terms_of_service_error(){
		//click on proceed checkout
		driver.findElement(By.xpath("//*[@id=\"form\"]/p//span")).click()
		//error message check
		String error = driver.findElement(By.className("fancybox-error")).getText()
		Assert.assertTrue(error.equals("You must agree to the terms of service before continuing."))
		WebDriverWait w = new WebDriverWait(driver,10);
		w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//*[@id=\"order\"]/div[2]/div//a")));
		driver.findElement(By.xpath("//*[@id=\"order\"]/div[2]/div//a")).click()
	}
	
	@And("select terms of service and click on proceed")
	def terms_of_service_check(){
		Thread.sleep(1000)
		driver.findElement(By.id("cgv")).click()
		Thread.sleep(500)
		driver.findElement(By.xpath("//*[@id=\"form\"]/p//span")).click()
	}
	
	@Then("payment mode is selected")
	def payment_mode(){
		WebElement Payment = driver.findElement(By.xpath("//*[@id=\"step_end\"]"))
		String Class = Payment.getAttribute('class')
		Assert.assertTrue(Class.toLowerCase().contains("current".toLowerCase()))
		
		//any payment mode
		driver.findElement(By.className("bankwire")).click()
		//confirm order
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]//span")).click()
		//last message check
		String msg = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div//strong")).getText()
		Thread.sleep(500)
		Assert.assertTrue(msg.equals("Your order on My Store is complete."))
		
		
		driver.quit()
	}
	def list_selection_of_styles(String op, String st){
		WebElement ul = driver.findElement(By.id(st))
		List <WebElement> listElement = ul.findElements(By.tagName("li"))
		for(WebElement e:listElement){
			String text_name = e.getText()
			if((text_name.contains(op))||(listElement.indexOf(e)==2)){
				e.findElement(By.className("checkbox")).click()
				Thread.sleep(2000)
			}
		}
	}
}