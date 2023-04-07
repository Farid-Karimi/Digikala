# Digikala Project Document

## Introduction:

The objective of this project is to build an online shop simulator App that provides e-commerce services similar to Digikala or Amazon. The App should allow users to simulate a simplified version of the online shopping experience through the command line interface. This is a solo project meant to be undertaken by each student individually.

## UML Diagram:
To build the application, I started by creating a UML diagram for each class. The UML diagram helped me to understand the relationships between classes and their methods. It also helped me to identify the attributes and methods required for each class. The following is the UML diagram for the classes in the project:

## Entities to be Uniquely Identifiable:
To determine which entities need to be uniquely identifiable, I considered the following factors:

Whether the entity needs to be referenced by other classes
Whether there can be multiple instances of the entity
Whether the entity needs to be stored or retrieved from a database or other data storage system.

Based on these factors, I identified the following entities that need to be uniquely identifiable:

- User
- Product
- Order

## Challenges and Bugs:
- During the development process, I faced several challenges and bugs. One of the challenges was implementing the design patterns to ensure the code was modular and maintainable.
- Another challenge I faced was testing the code for bugs and ensuring it was functioning properly.
- One of the most frustrating bugs I encountered was handling user inputs due to issues with the `nextLine()` method in Java.

- While testing my code, I encountered another bug which involved modifying an ArrayList while iterating through it. Through this experience, I learned that editing an ArrayList using a for-each loop is not possible. To resolve this issue, I used an Iterator object, which I learned how to use from this [link](https://www.w3schools.com/java/java_iterator.asp). By implementing functionalities such as Order Verification, I was able to successfully address this problem in my project.

## Additional Features:
- In addition to the project requirements, I implemented a feature that allows users to view their order history. This feature provides users with a list of all their previous orders, including the order date, the products ordered, and the total price of the order. This feature was implemented to enhance the user experience and to make it easier for users to keep track of their purchases.
- By utilizing polymorphism in the product sub-classes, it is possible for a product to be sold by multiple sellers through a deep copy mechanism.

## UML Diagram

![digikala](https://user-images.githubusercontent.com/118434072/230642341-d2dfa6fc-1d02-459d-810b-bb150f67fb9b.png)

## Conclusion:

In conclusion, building an online shop simulator App provided me with an opportunity to exercise my creativity and implement my own unique approach to building the application. The UML diagram helped me to understand the relationships between classes and their methods. I also identified the entities that need to be uniquely identifiable. Despite facing challenges and bugs, I was able to overcome them by researching and implementing the best practices. Finally, I implemented an additional feature to enhance the user experience.

---

### Advanced Programming - Spring 2023
