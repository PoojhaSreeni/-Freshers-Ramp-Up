import {Selector,t} from 'testcafe';

class MyAccount{
    constructor(){
        this.WomenButton = Selector("a[title='Women']");

    }
    async womenButton(){
        await t
        .click(this.WomenButton)
    }
}
export default new MyAccount();