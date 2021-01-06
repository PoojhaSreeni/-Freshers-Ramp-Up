Feature: YourLogo Website Test

  Scenario Outline: Test YourLogo Website under certain cases
    Given Navigate to home page
    And click signIn button
    When Entered valid <usermail> and <password> under Already Registered field
    And Click on Sign In button
    When Click on the Women section
    And select two <options> from styles	
    And select option 5 from the sortby dropdown
    When <product> name is searched
    And the product is selected under Quick View
    And quantity is set to '2' and click on add to cart
    And verification message is displayed and quick view window is closed
    When Click on 'cart' available on the top right corner
    And Proceed to checkout
    When terms of service checkbox not selected
    And select terms of service and click on proceed
    Then payment mode is selected

    Examples: 
      | usermail               | password  | options | product                    |
      | poojaasreeni@gmail.com | D@ddy2603 | Dressy  | Faded short sleeve t-shirt |
