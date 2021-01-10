import {Selector,t} from 'testcafe';

class PlaceOrder{
    constructor(){
        this.Cart = Selector('#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a');
        this.Message = Selector('#cart_title');
        this.Name = Selector('#product_3_13_0_425211 > td.cart_description > p > a');
        this.Qunatity = Selector('#product_3_13_0_425211 > td.cart_quantity.text-center > input.cart_quantity_input.form-control.grey');
        this.Price = Selector('#product_price_3_13_425211 > span');
        this.Checkout = Selector('a.button.btn.btn-default.standard-checkout.button-medium');
        this.Checkout1 = Selector('#center_column > form > p > button');
        this.Checkout2 = Selector('#form > p > button');
        this.ErorMessage = Selector('#order > div.fancybox-overlay.fancybox-overlay-fixed > div > div > div > div > p');
        this.CloseWindow = Selector('#order > div.fancybox-overlay.fancybox-overlay-fixed > div > div > a');
        this.CLickCheckBox = Selector('input[type="checkbox"]');
        this.PaymentMode = Selector('#HOOK_PAYMENT > div:nth-child(2) > div > p');
        this.ConfirmOrder = Selector('#cart_navigation > button');
        this.LAstMessgae = Selector('#center_column > p.alert.alert-success');
    }
    async orderSummary(Heading, Name, ProductPrice, ErrorMessage, ConfirmationMessage){
        await t
        .click(this.Cart)
        .expect((this.Message).textContent).contains(Heading)
        .expect((this.Name).textContent).contains(Name)
        .expect((this.Qunatity).exists).ok()
        .expect((this.Price).textContent).contains(ProductPrice)
        .expect(Selector('li').withAttribute('class','step_current  first').exists).ok()
        .click(this.Checkout)
        .expect(Selector('li').withAttribute('class','step_current third').exists).ok()
        .click(this.Checkout1)
        .expect(Selector('li').withAttribute('class','step_current four').exists).ok()
        .click(this.Checkout2)
        .expect((this.ErorMessage).textContent).contains(ErrorMessage)
        .click(this.CloseWindow)
        .click(this.CLickCheckBox)
        .click(this.Checkout2)
        .expect(Selector('li').withAttribute('id','step_end').exists).ok()
        .click(this.PaymentMode)
        .click(this.ConfirmOrder)
        .expect((this.LAstMessgae).textContent).contains(ConfirmationMessage);



    }
}
export default new PlaceOrder();