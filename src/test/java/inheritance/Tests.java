package inheritance;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.LocalDateTime;

class Tests {

  @Test
  void customerTest() {
    // Instantiate a customer
    Customer c1 = new Customer("Weihua", "Liu");
    assertEquals("Liu", c1.getLastName(), "Last name test");
    assertEquals("Weihua", c1.getFirstName(), "First name test");
    assertNull(c1.getOrder(), "Initial Order reference should be null");
  }

  @Test
  void inspectOrder() {
    // Make sure Order is both public, abstract, and the parent class
    try {
      Class<?> c = Class.forName("inheritance.Order");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public abstract", "Order is not 'public abstract'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "java.lang.Object", "Order has an incorrect parent class");
    } catch (Exception e) {
      // Report exception
      fail("Could not inspect Order class");
    }

  }

  @Test
  void inspectDineIn() {
    // Make sure DineIn is both public and a subclass of Order
    try {
      Class<?> c = Class.forName("inheritance.DineIn");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public", "DineIn class is not 'public'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "inheritance.Order", "DineIn has an incorrect parent class");
    } catch (Exception e) {
      // Report exception
      fail("Could not inspect DineIn class");
    }

  }

  @Test
  void testDineIn() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new DineIn(null, null, null, 0));

    // Instantiate a customer
    Customer c1 = new Customer("Weihua", "Liu");

    // Create a complete dine-in order
    DineIn o1 = new DineIn("KL09M", LocalDateTime.now(), c1, 9);

    // Check values
    long delta = Duration.between(LocalDateTime.now(), o1.getOrderTime()).toMillis();
    System.out.println("DELTA: " + delta);
    assertTrue(delta < 4, "Timestamp less than 4ms");

    assertEquals("DineIn", o1.getOrderType(), "Order Type test");
    assertEquals("KL09M", o1.getOrderNum(), "Order number test");
    assertEquals(c1, o1.getCustomer(), "Customer test");
    assertEquals(0, o1.getItems().length, "Initial rooms array length test");
    assertEquals(9, o1.getTableNum(), "" + "Table number test");

    // Check order reference in customer
    assertEquals(o1, c1.getOrder(), "Customer order test");
  }

  @Test
  void inspectDriveThrough() {
    // Make sure DriveThrough is both public and a subclass of Order
    try {
      Class<?> c = Class.forName("inheritance.DriveThrough");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public", "DriveThrough class is not 'public'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "inheritance.Order", "DriveThrough has an incorrect parent class");
    } catch (Exception e) {
      // Report exception
      fail("Could not inspect DriveThrough class");
    }

  }

  @Test
  void testDriveThrough() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new DriveThrough(null, null, null));

    // Instantiate a customer
    Customer c1 = new Customer("Weihua", "Liu");

    // Create a complete dine-in order
    DriveThrough o1 = new DriveThrough("KL08M", LocalDateTime.now(), c1);

    // Check values
    long delta = Duration.between(LocalDateTime.now(), o1.getOrderTime()).toMillis();
    System.out.println("DELTA: " + delta);
    assertTrue(delta < 4, "Timestamp less than 4ms");

    assertEquals("DriveThrough", o1.getOrderType(), "Order Type test");
    assertEquals("KL08M", o1.getOrderNum(), "Order number test");
    assertEquals(c1, o1.getCustomer(), "Customer test");
    assertEquals(0, o1.getItems().length, "Initial rooms array length test");

    // Check order reference in customer
    assertEquals(o1, c1.getOrder(), "Customer order test");
  }

  @Test
  void inspectBurger() {
    // Make sure Burger is both public and a subclass of Order
    try {
      Class<?> c = Class.forName("inheritance.Burger");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public", "Burger class is not 'public'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "inheritance.Item", "Burger has an incorrect parent class");
    } catch (Exception e) {
      // Report exception
      fail("Could not inspect Burger class");
    }

  }

  @Test
  void testBurger() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new Burger(null, null, 0));

    // Instantiate a customer
    Customer c1 = new Customer("Weihua", "Liu");

    // Instantiate an DineIn Order
    DineIn b1 = new DineIn("KI908B", LocalDateTime.now(), c1, 7);

    // Create an Item
    Burger r1 = new Burger(b1, "Spicy Crispy", 2);


    // Check values

    assertEquals("Burger", r1.getType(), "Type test");
    assertEquals(b1, r1.getOrder(), "Order reference test");
    assertEquals("Spicy Crispy", r1.getName(), "Name test");
    assertEquals(2, r1.getQuantity(), "Quantity test");

    assertEquals(0, r1.getIngredients().length, "Initial Ingredients array length test");


    // Check Item reference in Order
    assertEquals(r1, b1.getItems()[0], "Order's Item test");
  }


  @Test
  void inspectCoffee() {
    // Make sure Coffee is both public and a subclass of Order
    try {
      Class<?> c = Class.forName("inheritance.Coffee");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public", "Coffee class is not 'public'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "inheritance.Item", "Coffee has an incorrect parent class");
    } catch (Exception e) {
      // Report exception
      fail("Could not inspect Coffee class");
    }

  }

  @Test
  void testCoffee() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new Coffee(null, null, 0,null));

    // Instantiate a customer
    Customer c1 = new Customer("Weihua", "Liu");

    // Instantiate an DineIn Order
    DineIn b1 = new DineIn("KI908B", LocalDateTime.now(), c1, 7);

    // Create an Item
    Coffee r1 = new Coffee(b1, "Latta", 4,"small");


    // Check values

    assertEquals("Coffee", r1.getType(), "Type test");
    assertEquals(b1, r1.getOrder(), "Order reference test");
    assertEquals("Latta", r1.getName(), "Name test");
    assertEquals(4, r1.getQuantity(), "Quantity test");
    assertEquals("small", r1.getSize(), "Size test");
    assertEquals(0, r1.getIngredients().length, "Initial Ingredients array length test");
    // Check Item reference in Order
    assertEquals(r1, b1.getItems()[0], "Order's Item test");
  }
  @Test
  void AddIngredientTest() {
    // Instantiate a customer
    Customer c1 = new Customer("Daisy", "Liu");

    // Instantiate an Order
    Order o1 = new DineIn("KI908B", LocalDateTime.now(),c1,5);

    // Create an Item
    Item item1 = new Burger(o1,"Spicy Crispy",2);

    // Create an Ingredient
    Ingredient ingre1 = new Ingredient(item1, "Potato Roll", "regular", 100.00);
    assertEquals(1, item1.getIngredients().length, "Testing Ingredients array length");
    Ingredient ingre2 = new Ingredient(item1, "Lattuce", "regular", 5.00);
    assertEquals(2, item1.getIngredients().length, "Testing Ingredients array length");
    Ingredient ingre3 = new Ingredient(item1, "Tomato", "none", 9.00);
    assertEquals(3, item1.getIngredients().length, "Testing Ingredients array length");
    Ingredient ingre4 = new Ingredient(item1, "Chicken Fillet", "regular", 100.00);
    assertEquals(4, item1.getIngredients().length, "Testing Ingredients array length");
    Ingredient ingre5 = new Ingredient(item1, "kechup", "extra", 20.00);
    assertEquals(5, item1.getIngredients().length, "Testing Ingredients array length");
    Ingredient ingre6 = new Ingredient(item1, "White Roll", "regular", 100.00);
    assertEquals(5, item1.getIngredients().length, "Testing Ingredients array length");
    assertEquals(ingre2, item1.getIngredients()[0], "Item's Ingredient test");
    assertEquals(ingre6, item1.getIngredients()[item1.getIngredients().length-1], "Item's Ingredient test");

  }

  @Test
  void IngredientTest() {
    // Instantiate a customer
    Customer c1 = new Customer("Weihua", "Liu");

    // Instantiate an Order
    Order o1 = new DineIn("KI908B", LocalDateTime.now(),c1,5);

    // Create an Item
    Item item1 = new Burger(o1,"Spicy Crispy",2);

    // Create an Ingredient
    Ingredient ingre1 = new Ingredient(item1, "Potato Roll", "regular", 100.00);

    // Check values

    assertEquals(item1, ingre1.getItem(), "Item reference test");
    assertEquals("Potato Roll", ingre1.getName(), "Name test");
    assertEquals("regular", ingre1.getCustomized(), "Customized test");
    assertEquals(100.00, ingre1.getCalorie(), "Calorie test");
    assertEquals(0, ingre1.getNutritions().length, "Initial Nutritions length test");

    // Check that we can add a device to a room
    assertEquals(1, item1.getIngredients().length, "Testing Ingredients array length");
    assertEquals(ingre1, item1.getIngredients()[0], "Item's Ingredient test");
  }

  @Test
  void nutritionTest()  {
    Customer c1 = new Customer("Weihua", "Liu");

    // Instantiate an Order
    Order o1 = new DineIn("KI908B", LocalDateTime.now(),c1,5);

    // Create an Item
    Item item1 = new Burger(o1,"Spicy Crispy",2);

    // Create an Ingredient
    Ingredient ingre1 = new Ingredient(item1, "Potato Roll", "regular", 100.00);

    // Create a Nutrition
    Nutrition n1=new Nutrition(ingre1,"Calcium","mcg", 30);

    // Check values
    assertEquals(ingre1, n1.getIngredient(), "Ingredient reference test");
    assertEquals("mcg", n1.getUnits(), "Unit test");
    assertEquals(30,n1.getAmount(), "Amount test");
    assertEquals("Calcium",n1.getName(), "Name test");


    // Check that reading was added
    assertEquals(1, ingre1.getNutritions().length, "Testing readings array length");
    assertEquals(n1, ingre1.getNutritions()[0], "Ingredient's Nutrition test");
  }


}