# CSC 241 inheritance

In this assignment, we will modify the code used in the previous assignment in
order to introduce the concept of inheritance. Inheritance is a feature of object
programming languages that permit a new class to acquire properties of an
existing class. This simplifies development and facilitates good program design.

Follow the specification below and make the necessary changes and additions.

## Specification

### Customer
This class represents a person using the system. Implement each of these methods:
- A constructor that initializes the last name and first name
- getLastName() - Returns a String that is the customer’s last name
- getFirstName() - Returns a String that is the customer’s first name
- getBuilding() - Returns a reference to the Building object (see below) associated with this customer
- setBuilding(Building) - Sets the Building for this customer

### Building
This class becomes `abstract`. It will be used later in an inheritance relationship
with two new classes, `CommercialBuilding` and `Residence`. These are the required
methods:
- A constructor that initializes the name, longitude, latitude, and Customer. It
 should also associate the new Building object with the given Customer. If the Customer
 parameter is not defined, it should throw an `IllegalArgumentException`
- getName() - returns a String that is the name of the building
- getLongitude() - returns a double that is the building's longitude
- getLatitude() - returns a double that is the building's latitude
- getCustomer() - Returns a reference to the Customer object associated with this building
- addRoom(Room) - Adds a Room object reference to a building
- getRooms() - Returns an array of Room objects associated with a building

### CommercialBuilding
This new class is subclass of `Building` and represents buildings that are 
commercial in nature. Such buildings will have an additional instance variable 
called `contactInfo` used to store the contact information for the building manager.
These are the required methods:
- The constructor should add a new `contactInfo` parameter to those needed by the 
parent constructor.
- getContactInfo() - Returns a String that is the contact info

### Residence
This new class is a subclass of `Building` and represents residences. It
should merely extend the `Building` class and implement the constructor, as needed.

### Room
This class represents a room in a Building. Here are the required methods:
- A constructor that initializes the name, floor, and Building. It
 should also associate the new Room object to the given Building. If the
Building parameter is not defined, it should throw an IllegalArgumentException
- getName() - Returns a String that is the room name
- getFloor() - Returns a String that is the room's floor within the building
- getBuilding() - Returns a reference to the associated Building object
- addDevice(Device) - Add a Device (see below) to this room 
- getDevices() - Returns an array of Device object references

### Device
This class becomes `abstract`. It will be used later in an inheritance relationship
with two new classes, `LightSwitch` and `Thermostat`. Here is the updated set of required methods:
- The constructor uses parameters to initialize id, room, and location. The
initial state should be set to false (off), and the initial Readings collection
should be empty. It should also add the new Device object to the specified Room. 
If the Room parameter is not defined, it should throw an `IllegalArgumentException`
- getId() - Returns a String that is the sensor device identifier
- getRoom() - Returns a reference to the Room where the sensor is located
- getLocation() - Returns a String indicating where in the Room the device was placed
- getState() - Returns a boolean that is the state of the device
- setState(boolean) - Updates the state of the device
- getReadings() - Returns an array of readings
- addReading() - This method adds a new reading to the collection. If the
  collection has reached 5 readings, remove the oldest reading before adding the new one

### LightSwitch
This new class represents a light switch that can be turned on or off. It is a subclass
of `Device`. Here are its required methods:
- A constructor defined by the requirements of the parent constructor
- turnOn() - sets the state of the device to on (true)
- turnOff() - sets the state of the device to off (false)

### Thermostat
This new class represents a thermostat that can read room temperature. It is a 
subclass of `Device`. Here are the required methods:
- The constructor is defined by the requirements of the 
parent and should set the state of the device to true (on)
- getCurrentReading() - Returns the most recently added Reading

### Reading
This class represents a reading produced by a device. These are the required methods:
- A constructor method that initializes the Device, units, and value, and sets the 
timestamp to the current system time. It should also add the new Reading to the 
specified Device. If the Device parameter is undefined, it should throw an `IllegalArgumentException`
- getDevice() - returns a reference to the Device that produced the reading
- getUnits() - returns a String representing the units of measurement
- getValue() - returns a double corresponding to the reading's value
- getTimestamp() - Returns a reference to `LocalDateTime` object that is the timestamp of the reading

## Testing
When you push your code back to your repository, GitHub will initiate a series of
tests to verify the correct operation of each of the required methods.
