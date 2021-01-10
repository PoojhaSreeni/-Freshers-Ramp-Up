import {ClientFunction} from 'testcafe';
import homepage from '../Pages/HomePage';
import loginpage from '../Pages/LoginPage';
import myaccount from '../Pages/MyAccount';
import women, { quantity } from '../Pages/Women';
import placeorder from '../Pages/PlaceOrder';


const URL = 'http://automationpractice.com/index.php';
const inputdata = require("../Data/Data.json");

fixture("Page Fixture")
.page(URL)
.beforeEach(async t =>{
    await t
        .maximizeWindow()
        .setTestSpeed(0.1)
        .setPageLoadTimeout(0);
});


inputdata.forEach(data =>{
    test
        ('Test for successful order placement', async t =>{
            await homepage.home();
            await loginpage.login(data.Email,data.Password);
            await myaccount.womenButton();
            await women.womenSection(data.Text);
            await women.iframe(data.ProductQuant,data.ProductMessage);
            await placeorder.orderSummary(data.Heading, data.Name, data.ProductPrice, data.ErrorMessage, data.ConfirmationMessage);
        })
})