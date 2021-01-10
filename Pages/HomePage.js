import {Selector,t} from 'testcafe';

class HomePage{
    constructor(){
        this.LoginLink = Selector('a.login');
    }
    async home(){
        await t 
        .click(this.LoginLink)
    }

}
export default new HomePage();
