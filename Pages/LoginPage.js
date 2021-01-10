import {Selector,t} from 'testcafe';

class LoginPage{
    constructor(){
      //  this.LoginForm = Selector("form[id='login_form']");
        //this.GetText = Selector('h3.page-subheading');
        this.Email = Selector('#email');
        this.Password = Selector('#passwd');
        this.Submit = Selector('#SubmitLogin');

    }
    async login(Email, Password){
        await t 
        .typeText(this.Email,Email)
        .typeText(this.Password,Password)
        .click(this.Submit)
    }
}
export default new LoginPage();