
# SNAKE CS50X

**Video Demo:** <https://youtu.be/-IQqNs4f7L4>  
**Description:**  A modular project organized under `src/` with `controller/`, `interface/`, and `model/` folders.  
Promotes maintainability, scalability, and clear separation of concerns.  
Patterns like Singleton, Observer, and DTO are applied for structured design.


TODO

---

## Project Architecture

The proposed structure for the project is based on a modular organization within the main folder named `src/`. This internal division allows for a clear separation of components that fulfill different responsibilities within the application. By using independent subfolders for **controller**, **interface**, and **model**, the goal is to promote maintainability, improve code comprehension, and encourage the application of object-oriented design patterns.  

Each of these directories corresponds to a specific layer within the application's architecture and contains classes, services, or components designed to work cohesively but with low coupling.

---

### model/
- Contains the classes and entities that represent the business logic and core data of the system.  
- Examples: `Sender`, `Shipment`, `Rate`, `GuideGenerator`, as well as all necessary DTOs.  
- Suitable for implementing patterns such as **Strategy** for different rates or notification methods.  
- Does not handle graphical or console interactions.  
- Facilitates unit testing by isolating domain logic from external aspects of the system.

---

### controller/
- Coordinates the flow of information between the model and the interface.  
- Examples: `SenderController`, `ShipmentController`, and other specialized controllers.  
- Manages use cases such as sender registration, guide generation, or pickup requests.  
- Validates inputs, invokes the model, and returns processed responses.  
- May serve as a connection point for patterns like **Observer**, enabling automatic reactions to events such as new shipments or request confirmations.

---

### interface/
- Manages user interaction.  
- May include console views, graphical templates, FXML controllers (JavaFX), or adapters for web/mobile environments.  
- Captures user input and displays results.  
- Should not contain business logic beyond basic validation.  
- Designed to be flexible, allowing future changes (e.g., replacing a console interface with a graphical one) without altering the internal system.

---

## Design Patterns

- **Singleton:** applied in services such as `NotificationService` or `GuideGenerator` to avoid unnecessary multiple instances.  
- **Observer:** integrated when the model must notify relevant events to services like `NotificationObserver`.  
- **DTO:** used in the controller to package and transport data between view and model without exposing internal domain details.

---

## Advantages of Modular Structure

- **Scalability:** each folder can grow with new functions without making the code confusing.  
- **Automated testing:** easier due to the independence of business logic.  
- **Maintainability:** changes in one layer do not force modifications in others.  
- **Flexibility:** allows replacing the interface without affecting the model or controller.

---

## Conclusion

Organizing the project with a central `src/` folder containing `controller/`, `interface/`, and `model/` provides a solid and clear architecture. This structure promotes maintainability, facilitates future expansions, supports the correct application of design patterns, and contributes to a cleaner and more professional understanding of the source code. It establishes a coherent workflow where each component knows exactly what responsibility it must fulfill and with which other parts of the system it should communicate, fostering a more orderly and efficient development process.

---

### AI Assistance Citation
The modular project structure and the drafting of this README were developed with assistance from ChatGPT. It was used solely as a support tool; the design and implementation of the project are original.
