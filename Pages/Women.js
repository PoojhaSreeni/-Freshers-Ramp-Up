import {Selector,t} from 'testcafe';

class Women{
    constructor(){
        this.Compose = Selector('layered_subtitle').withText('Compositions');
        this.CheckboxPoly = Selector("#layered_id_feature_1");
        this.DropDown = Selector('select#selectProductSort');
        this.Option = this.DropDown.find('option'); 
        this.Dress = Selector('#center_column > ul > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-in-line.first-item-of-tablet-line.last-item-of-mobile-line > div > div.left-block > div > a.product_img_link > img');
        this.QuickView = Selector('#center_column > ul > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-in-line.first-item-of-tablet-line.last-item-of-mobile-line.hovered > div > div.left-block > div > a.quick-view');
        this.IFrame = Selector('iframe');
        this.Quantity = Selector('#quantity_wanted');
        this.CartClick = Selector('button.exclusive');
        this.Message = Selector('#layer_cart');
        this.Close = Selector('span.cross');
        
    }
    async womenSection(Text){
        await t
        .click(this.CheckboxPoly)
        .click(this.DropDown)
        .click(this.Option.withText(Text))
        .hover(this.Dress)
        .hover(this.QuickView)
        .click(this.QuickView)
    }
    async iframe(ProductQuant,ProductMessage){
        await t
        .switchToIframe(this.IFrame)
        .click(this.Quantity)
        .pressKey('ctrl+a delete')
        .typeText(this.Quantity,ProductQuant)
        .click(this.CartClick)
        .switchToMainWindow()
        .expect((this.Message).textContent).contains(ProductMessage)
        .click(this.Close)
    }
}
    

export default new Women();