# CSC 241 inheritance

In this assignment, we will modify the code used in the previous assignment in
order to introduce the concept of inheritance. Inheritance is a feature of object
programming languages that permit a new class to acquire properties of an
existing class. This simplifies development and facilitates good program design.

Follow the specification below and make the necessary changes and additions.

## Specification

### Customer
This class represents a customer visiting the restaurant. Implement each of these methods:

- A constructor that initializes the last name and first name
- getLastName() - Returns a String that is the customer’s last name
- getFirstName() - Returns a String that is the customer’s first name
- getOrder() - Returns a reference to the Order object associated with this customer
- setOrder(Order) - Sets the Order for this customer

### Order
This class becomes `abstract`. It will be used later in an inheritance relationship
with two new classes, `DineIn` and `DriveThrough`. These are the required
methods:
- A constructor that initializes the orderType, orderNum, orderTime and customer. It
  should also associate the new Order object with the given Customer. If the Customer
  parameter is not defined, it should throw an `IllegalArgumentException`
- getOrderType() - returns a String representing the type of the order
- getOrderNum() - returns a String that is the order's order number
- getOrderTime() - Returns a reference to LocalDateTime object that is the time an order is placed
- getCustomer() - Returns a reference to the Customer object associated with this building
- addItem(Item) - Adds an Item object reference to an order
- getItems() - Returns an array of Item objects associated with an order

### DineIn
This new class is subclass of `Order` and represents dine-in orders. Such orders will have an additional instance variable
called `tableNum` used to store the table number of the customer.
These are the required methods:
- The constructor should add a new `table` parameter to those needed by the
  parent constructor.
- getTableNum() - Returns an int that is the table number of the customer

### DriveThrough
This new class is a subclass of `Order` and represents drive-through orders. It
should merely extend the `Order` class and implement the constructor, as needed.


### Item
This class becomes `abstract`. It will be used later in an inheritance relationship
with two new classes, `Burger` and `Coffee`. These are the required
methods:
- A constructor that initializes the type, order, name and quantity. It
  should also associate the new Item object to the given Order. If the
  Order parameter is not defined, it should throw an IllegalArgumentException
- getType() - Returns a String that is the type of an item
- getOrder() - Return a reference to the Order object associated with this Item
- getName() - Returns a String that is the name of an item
- getQuantity() - Returns an int that is the number of item ordered
- addIngredient(Ingredient) - Add an Ingredient to this Item. If the
  collection has reached 5 Ingredients, remove the oldest ingredient before adding the new one
- getIngredients() - Returns an array of Ingredient object references

### Burger
This new class is a subclass of `Item` and represents a burger in the order. It
should merely extend the `Item` class and implement the constructor, as needed.

### Coffee
This new class represents a cup of coffee in the order. It is a subclass
of `Item`. It will have an additional instance variable called `size` used to store the size of the coffee.
Here are its required methods:
- The constructor should add a new `size` parameter to those needed by the
  parent constructor.
- getSize() - Returns a String that is the size of the coffee


### Ingredient
This class represents an Ingredient. It is asscoiated with an Item and multiple Nutrition.
- A constructor that initializes item, name, customized and calorie. It
  should also associate the new Ingredient object to the given Item. If the
  Item parameter is not defined, it should throw an IllegalArgumentException
- getName() - Returns a String that is the name of the Ingredient
- getCustomized() - Returns a String that is for customized option of the ingredient
- getItem() - Returns a reference to the Item
- getCalorie() - Returns a double showing the calorie of the ingredient
- addNutrition(Nutrition) - Adds a Nutrition to an Ingredient
- getNutritions() - Returns an array of Nutrition objects


### Nutrition
This class represents the Nutrition included in an ingredient
- A constructor method that initializes the Ingredient, name, units,and amount. It
  should also associate the new Nutrition object to the given Ingredient. If the
  Ingredient parameter is not defined, it should throw an IllegalArgumentException
- getIngredient() - returns a reference to the Ingredient that contains this nutrition.
- getUnits() - returns a String representing the units of measurement
- getAmount() - returns a double corresponding to the nutrition's value
- getName() - Returns String representing the name of the nutrition

## Testing
When you push your code back to your repository, GitHub will initiate a series of tests to verify the correct operation of each of the methods above.
