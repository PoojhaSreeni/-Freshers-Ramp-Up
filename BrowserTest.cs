using Microsoft.VisualStudio.TestTools.UnitTesting;
using OfficeOpenXml;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.IO;
using System.Threading;

namespace MSTestProject
{
    [TestClass]
    public class BrowserTest
    {
        String welcome_url = "http://www.practiceselenium.com/welcome.html";
        
       
        [TestMethod]
        public void WelcomePage()
        {
            ReaderClass r = new ReaderClass();

            IWebDriver driver;
            driver = new ChromeDriver();
            driver.Manage().Window.Maximize();

            driver.Navigate().GoToUrl(welcome_url);
            driver.Manage().Window.Maximize();

            //Tea image check
            IWebElement webImg = driver.FindElement(By.ClassName("img"));

            try
            {
                Assert.IsNotNull(webImg);
            }
            catch (AssertFailedException e)
            {
                Assert.Fail("Image not found");
            }

            //Company name check
            IWebElement webTxt = driver.FindElement(By.ClassName("editor_tea"));
            Assert.IsTrue(webTxt.Displayed);

            //tagline check
            IWebElement webTxt_Tagline = driver.FindElement(By.XPath("//div[@class='txt ']/h1"));
            String tagline = webTxt_Tagline.Text;

            Assert.IsTrue(tagline.Equals("We're passionate about tea. "));


            //Welcome Check
            IWebElement webTxt_Welcome = driver.FindElement(By.ClassName("active"));
            String welcome_txt = webTxt_Welcome.Text;
            Assert.IsTrue(welcome_txt.Equals("Welcome"));


            //Loose tea text check
            IWebElement webTxt_Loose_Tea_Txt = driver.FindElement(By.XPath("//span[@class='editor_collections']"));
            String text_check = webTxt_Loose_Tea_Txt.Text;
            Assert.IsTrue(text_check.Equals("Loose Tea"));

            //Loose tea image check
            IWebElement webtxt_Loose_tea_img = driver.FindElement(By.XPath("//div[@id='wsb-element-00000000-0000-0000-0000-000450914892']"));
            Assert.IsTrue(webtxt_Loose_tea_img.Displayed);

            //See Collection button
            IWebElement collection_button = driver.FindElement(By.XPath("//a[@id='wsb-button-00000000-0000-0000-0000-000450914897']"));
            try
            {
                collection_button.Click();
            }
           catch(ElementClickInterceptedException e)
            {
                Assert.Fail("Failed to navigate to next page");
            }

            //Menu.html page
            //menu active check
            IWebElement menu_check = driver.FindElement(By.ClassName("active"));
            String menu = menu_check.Text;
            Assert.IsTrue(menu.Equals("Menu"));


            //checkout button check
            IWebElement checkout_button = driver.FindElement(By.XPath("//a[@id='wsb-button-00000000-0000-0000-0000-000451959280']"));
            try
            {
                checkout_button.Click();
            }
            catch(Exception e)
            {
                Assert.Fail("Click did not work");
            }

            //orange checkout text
            IWebElement checkout_color = driver.FindElement(By.ClassName("active"));
            String color = checkout_color.Text;
            Assert.IsTrue(color.Equals("Check Out"));


            //Including text file
            string InputData = r.ReadText("C:\\Project\\MSTestProject\\TextReader.txt");
            string[] InputDataList = InputData.Split(", ");   

            //Email check
            driver.FindElement(By.XPath("//div[@class='controls']/input")).SendKeys(InputDataList[0]);
            Thread.Sleep(2000);

            //Name check
            IWebElement name_check = driver.FindElement(By.XPath("//div[@class='controls']/input[@id='name']"));
            name_check.SendKeys(InputDataList[1]);
            Thread.Sleep(2000);


            //Address Check
            IWebElement addr_check = driver.FindElement(By.XPath("//div[@class='controls']/textarea"));
            addr_check.SendKeys(InputDataList[2]);
            Thread.Sleep(2000);

            //card type
            SelectElement oSelect = new SelectElement(driver.FindElement(By.XPath("//div[@class='controls']/select")));
            oSelect.SelectByText(InputDataList[3]);
            Thread.Sleep(2000);

            //Card number
            IWebElement card_number = driver.FindElement(By.XPath("//div[@class='controls']/input[@id='card_number']"));
            card_number.SendKeys(InputDataList[4]);
            Thread.Sleep(2000);

            //Cardholder name
            IWebElement card_holder = driver.FindElement(By.XPath("//div[@class='controls']/input[@id='cardholder_name']"));
            card_holder.SendKeys(InputDataList[5]);
            Thread.Sleep(2000);

            //Verification code
            IWebElement verify = driver.FindElement(By.XPath("//div[@class='controls']/input[@id='verification_code']"));
            verify.SendKeys(InputDataList[6]);
            Thread.Sleep(2000);

            //place order button
            IWebElement placeOrderButton = driver.FindElement(By.XPath("//div[@class='form-actions']//button"));
            placeOrderButton.Click();
            

            Console.WriteLine("Pass");

            driver.Quit();


        }
      


        

      

    }
}

