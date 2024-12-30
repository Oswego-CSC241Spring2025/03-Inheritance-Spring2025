package inheritance;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.LocalDateTime;

class Tests {

  @Test
  void testCustomer() {
    // Instantiate a customer
    Customer c1 = new Customer("Early", "James");
    assertEquals("Early", c1.getLastName(), "Last name test");
    assertEquals("James", c1.getFirstName(), "First name test");
    assertNull(c1.getBuilding(), "Initial Building reference should be null");
  }

  @Test void inspectBuilding() {
    // Make sure Building is both public, abstract, and the parent class
    try {
      Class<?> c = Class.forName("inheritance.Building");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public abstract", "Building is not 'public abstract'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "java.lang.Object", "Building has an incorrect parent class");
    } catch (Exception e){
      // Report exception
      fail("Could not inspect Building class");
    }

  }

  @Test void inspectCommerialBuilding() {
    // Make sure CommercialBuilding is both public and a subclass of Building
    try {
      Class<?> c = Class.forName("inheritance.CommercialBuilding");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public", "Commercial Building is not 'public'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "inheritance.Building", "CommercialBuilding has an incorrect parent class");
    } catch (Exception e){
      // Report exception
      fail("Could not inspect CommercialBuilding class");
    }

  }

  @Test void testCommercialBuilding() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new CommercialBuilding(null, 0, 0, null, null));

    // Instantiate a customer
    Customer c1 = new Customer("Early", "James");

    // Create a complete commercial building
    CommercialBuilding cb1 = new CommercialBuilding("home", 84.4, 57.7,
        c1, "Jones / 315-312-3200");

    // Check values
    assertEquals("home", cb1.getName(), "Name test");
    assertEquals(84.4, cb1.getLongitude(), "Longitude test");
    assertEquals(57.7, cb1.getLatitude(), "Latitude test");
    assertEquals(c1, cb1.getCustomer(), "Customer test");
    assertEquals(0, cb1.getRooms().length, "Initial rooms array length test");
    assertEquals("Jones / 315-312-3200", cb1.getContactInfo(), "Contact info test");

    // Check building reference in customer
    assertEquals(cb1, c1.getBuilding(), "Customer building test");
  }

  @Test void inspectResidence() {
    // Make sure Residence is both public and a subclass of Building
    try {
      Class<?> c = Class.forName("inheritance.Residence");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public", "Residence is not 'public'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "inheritance.Building", "Residence has an incorrect parent class");
    } catch (Exception e) {
      // Report exception
      fail("Could not inspect Residence class");
    }
  }

  @Test void testResidence() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new Residence(null, 0, 0, null));

    // Instantiate a customer
    Customer c1 = new Customer("Early", "James");

    // Create a residence
    Residence b1 = new Residence("home", 84.4, 57.7, c1);

    // Check values
    assertEquals("home", b1.getName(), "Name test");
    assertEquals(84.4, b1.getLongitude(), "Longitude test");
    assertEquals(57.7, b1.getLatitude(), "Latitude test");
    assertEquals(c1, b1.getCustomer(), "Customer test");
    assertEquals(0, b1.getRooms().length, "Initial rooms array length test");

    // Check building reference in customer
    assertEquals(b1, c1.getBuilding(), "Customer building test");
  }

  @Test
  void testRoom() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new Room(null, null, null));

    // Instantiate a customer
    Customer c1 = new Customer("Early", "James");

    // Instantiate a building(residence)
    Building b1 = new Residence("home", 84.4, 57.7, c1);

    // Create a room
    Room r1 = new Room("kitchen", "1st", b1);

    // Check values
    assertEquals("kitchen", r1.getName(), "Name test");
    assertEquals("1st", r1.getFloor(), "Floor test");
    assertEquals(0, r1.getDevices().length, "Initial devices array length test");
    assertEquals(b1, r1.getBuilding(), "Building reference test");

    // Check that building was updated
    assertEquals(1, b1.getRooms().length, "Testing rooms array length");
  }


  @Test void inspectDevice() {
    // Make sure Device is both public and abstract.
    try {
      Class<?> c = Class.forName("inheritance.Device");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public abstract", "Device is not 'public abstract'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "java.lang.Object", "Device has an incorrect parent class");
    } catch (Exception e){
      // Report exception
      fail("Could not inspect Device class");
    }
  }

  @Test void inspectLightSwitch() {
    // Make sure LightSwitch is both public and a subclass of Device.
    try {
      Class<?> c = Class.forName("inheritance.LightSwitch");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public", "LightSwitch is not 'public'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "inheritance.Device", "LightSwitch has an incorrect parent class");
    } catch (Exception e){
      // Report exception
      fail("Could not inspect LightSwitch class");
    }
  }

  @Test
  void testLightSwitch() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new LightSwitch(null, null, null));

    // Instantiate a customer
    Customer c1 = new Customer("Early", "James");

    // Instantiate a building(Residence)
    Building b1 = new Residence("home", 84.4, 57.7, c1);

    // Instantiate a room
    Room r1 = new Room("kitchen", "1st", b1);

    // Create a device
    LightSwitch ls1 = new LightSwitch("abcd-1234", r1, "Bottom of west wall");

    // Check values
    assertEquals("abcd-1234", ls1.getId(), "Id test");
    assertEquals(r1, ls1.getRoom(), "Room reference test");
    assertEquals("Bottom of west wall", ls1.getLocation(), "Location test");
    assertFalse(ls1.getState(), "Initial state test");

    // Cycle the state
    ls1.turnOn();
    assertTrue(ls1.getState(), "Turn light switch on");
    ls1.turnOff();
    assertFalse(ls1.getState(), "Turn light switch off");

    // Check room was updated
    assertEquals(1, r1.getDevices().length, "Testing devices array length");
  }

  @Test void inspectThermostat() {
    // Make sure Thermostat is both public and a subclass of Device.
    try {
      Class<?> c = Class.forName("inheritance.Thermostat");
      // Inspect modifiers
      String modifiers = Modifier.toString(c.getModifiers());
      assertEquals(modifiers, "public", "Thermostat is not 'public'");
      // Inspect parent classes
      String ancestor = c.getSuperclass().getCanonicalName();
      assertEquals(ancestor, "inheritance.Device", "Thermostat has an incorrect parent class");
    } catch (Exception e){
      // Report exception
      fail("Could not inspect Thermostat class");
    }
  }

  @Test
  void testThermostat() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new Thermostat(null, null, null));

    // Instantiate a customer
    Customer c1 = new Customer("Early", "James");

    // Instantiate a building(Residence)
    Building b1 = new Residence("home", 84.4, 57.7, c1);

    // Instantiate a room
    Room r1 = new Room("kitchen", "1st", b1);

    // Create a device
    Thermostat t1 = new Thermostat("abcd-1234", r1, "Bottom of west wall");

    // Check values
    assertEquals("abcd-1234", t1.getId(), "Id test");
    assertEquals(r1, t1.getRoom(), "Room reference test");
    assertEquals("Bottom of west wall", t1.getLocation(), "Location test");
    assertTrue(t1.getState(), "Thermostat state test");
    assertEquals(0, t1.getReadings().length, "Initial readings test");

    // Try adding Readings
    for (int i = 1; i <= 10; i++) {
      Reading rd1 = new Reading(t1,"F", i);
      //t1.addReading(rd1); // Add reading
      if (i <= 5) {
        assertEquals(i, t1.getReadings().length, "Add multiple readings test");
        // Check for the last reading
        assertEquals(rd1, t1.getCurrentReading(), "Current reading test");
      } else {
        // Max reached
        assertEquals(5, t1.getReadings().length, "Maximum readings test");
        assertEquals(rd1, t1.getCurrentReading(), "Current reading test");
      }
    }


    // Check room was updated
    assertEquals(1, r1.getDevices().length, "Testing devices array length");
  }

  @Test
  void testReading() {
    // Test incomplete constructor
    assertThrows(IllegalArgumentException.class, () -> new Reading(null, null, 0));

    // Instantiate a customer
    Customer c1 = new Customer("Early", "James");

    // Instantiate a building(Residence)
    Building b1 = new Residence("home", 84.4, 57.7, c1);

    // Instantiate a room
    Room r1 = new Room("kitchen", "1st", b1);

    // Create a device
    Thermostat t1 = new Thermostat("abcd-1234", r1, "Bottom of west wall");

    // Create a reading
    LocalDateTime now = LocalDateTime.now(); // Get system time
    Reading rd1 = new Reading(t1, "Degrees F", 67.5);
    long delta = Duration.between(now,rd1.getTimestamp()).toMillis();
    System.out.println("DELTA: " + delta);

    // Check values
    assertEquals(t1, rd1.getDevice(), "Device reference test");
    assertEquals("Degrees F", rd1.getUnits(), "Units test");
    assertEquals(67.5, rd1.getValue(), "Value test");
    assertTrue(delta < 4, "Timestamp less than 4ms");

    // Check device was updated
    assertEquals(1, t1.getReadings().length, "Testing devices array length");
  }

}
