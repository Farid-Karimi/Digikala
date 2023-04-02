package org.example;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Shop shop = new Shop("My Shop", "www.myshop.com", "909 230 12 02", 0.0);
    static Admin admin = new Admin("gholi","1234","gholiMoli@gmail.com");
    public static void main(String[] args) {
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("3. Seller");
        System.out.println("Choose your role:");
        int choice = input.nextInt();
        switch (choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                // Invalid choice
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public void adminMenu(){
        while (true) {

            System.out.println("1. Add product");
            System.out.println("2. Add order");
            System.out.println("3. Add account");
            System.out.println("4. Add profit");
            System.out.println("5. List products");
            System.out.println("6. List orders");
            System.out.println("7. List accounts");
            System.out.println("8. Show total profit");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Add product
                    addProduct();
                    break;

                case 2:
                    // Add order
                    addOrder();
                    break;

                case 3:
                    // Add account
                    addAccount();
                    break;

                case 4:
                    // Add profit
                    System.out.print("Enter profit amount: ");
                    double profitAmount = input.nextDouble();
                    shop.addProfit(profitAmount);
                    System.out.println("Profit added successfully!");
                    break;

                case 5:
                    // List products
                    System.out.println("Product list:");
                    for (Product p : shop.getProductList()) {
                        System.out.println(p);
                    }
                    break;

                case 6:
                    // List orders
                    System.out.println("Order list:");
                    for (Order o : shop.getOrderList()) {
                        System.out.println(o);
                    }
                    break;

                case 7:
                    // List accounts
                    System.out.println("Account list:");
                    for (Account acc : shop.getAccountList()) {
                        System.out.println(acc);
                    }
                    break;

                case 8:
                    // Show total profit
                    System.out.println("Total profit: " + shop.getTotalProfit());
                    break;

                case 0:
                    // Exit
                    System.out.println("Goodbye!");
                    System.exit(0);

                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    public static void addProduct(){
        System.out.println("Enter product category:");
        System.out.println("Choose a category for products: ");
        System.out.println("1. Electronics");
        System.out.println("2. Books");
        System.out.println("3. Clothes");
        System.out.println("4. Phone");
        System.out.println("5. Smartwatch");
        System.out.println("6. Laptop");
        System.out.println("7. Headphone");
        System.out.println("8. Toy");
        System.out.println("9. Beauty Products");
        System.out.println("10. Food");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter product name:");
                String name = input.nextLine();
                System.out.println("Enter product price:");
                double price = input.nextDouble();
                System.out.println("Enter product quantity:");
                int quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                String comment = input.nextLine();
                System.out.println("Enter product brand:");
                String brand = input.nextLine();
                System.out.println("Enter product model:");
                String model = input.nextLine();
                Electronics electronics = new Electronics(name,price,quantity,comment,brand,model);
                shop.addProduct(electronics);
                break;
            case 2:
                System.out.println("Enter Book name:");
                name = input.nextLine();
                System.out.println("Enter Book price:");
                price = input.nextDouble();
                System.out.println("Enter Book quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter Book ISBN:");
                String isbn = input.next();
                System.out.println("Enter Book author:");
                String author = input.nextLine();
                Book book = new Book(name,price,quantity,comment,author,isbn);
                shop.addProduct(book);
                break;
            case 3:
                System.out.println("Enter Clothes name:");
                name = input.nextLine();
                System.out.println("Enter Clothes price:");
                price = input.nextDouble();
                System.out.println("Enter Clothes quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter Clothes size:");
                String size = input.nextLine();
                System.out.println("Enter Clothes color:");
                String color = input.nextLine();
                System.out.println("Enter Clothes gender:");
                String gender = input.nextLine();
                Clothing clothing = new Clothing(name,price,quantity,comment,size,color,gender);
                shop.addProduct(clothing);
                break;
            case 4:
                System.out.println("Enter phone name:");
                name = input.nextLine();
                System.out.println("Enter phone price:");
                price = input.nextDouble();
                System.out.println("Enter phone quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter phone brand:");
                brand = input.nextLine();
                System.out.println("Enter phone model:");
                model = input.nextLine();
                System.out.println("Enter phone screen Size:");
                String screenSize = input.nextLine();
                System.out.println("Enter phone operating System:");
                String operatingSystem = input.nextLine();
                System.out.println("Enter phone cameraSpecs:");
                String cameraSpecs = input.nextLine();
                System.out.println("Enter phone memory capacity:");
                int memory = input.nextInt();
                Phone phone = new Phone(name,price,quantity,comment,brand,model,screenSize,operatingSystem,cameraSpecs,memory);
                shop.addProduct(phone);
                break;
            case 5:
                System.out.println("Enter watch name:");
                name = input.nextLine();
                System.out.println("Enter watch price:");
                price = input.nextDouble();
                System.out.println("Enter watch quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter watch brand:");
                brand = input.nextLine();
                System.out.println("Enter watch model:");
                model = input.nextLine();
                System.out.println("Enter watch band Type:");
                String bandType = input.nextLine();
                System.out.println("Enter watch build Material:");
                String buildMaterial = input.nextLine();
                System.out.println("Enter watch screen Size:");
                screenSize = input.nextLine();
                SmartWatch smartWatch = new SmartWatch(name,price,quantity,comment,brand,model,bandType,buildMaterial,screenSize);
                shop.addProduct(smartWatch);
                break;
            case 6:
                System.out.println("Enter laptop name:");
                name = input.nextLine();
                System.out.println("Enter laptop price:");
                price = input.nextDouble();
                System.out.println("Enter laptop quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter laptop brand:");
                brand = input.nextLine();
                System.out.println("Enter laptop model:");
                model = input.nextLine();
                System.out.println("Enter laptop screen Size:");
                screenSize = input.nextLine();
                System.out.println("Enter laptop operating System:");
                operatingSystem = input.nextLine();
                System.out.println("Enter laptop CPU model:");
                String cpu = input.nextLine();
                System.out.println("Enter laptop GPU model:");
                String gpu = input.nextLine();
                Laptop laptop = new Laptop(name,price,quantity,comment,brand,model,screenSize,operatingSystem,cpu,gpu);
                shop.addProduct(laptop);
                break;
            case 7:
                System.out.println("Enter HeadPhone name:");
                name = input.nextLine();
                System.out.println("Enter HeadPhone price:");
                price = input.nextDouble();
                System.out.println("Enter HeadPhone quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter HeadPhone brand:");
                brand = input.nextLine();
                System.out.println("Enter HeadPhone model:");
                model = input.nextLine();
                System.out.println("Enter HeadPhone connection Type:");
                String connectionType = input.nextLine();
                System.out.println("Enter HeadPhone battery Capacity:");
                String batteryCapacity = input.nextLine();
                Headphone headphone = new Headphone(name,price,quantity,comment,brand,model,connectionType,batteryCapacity);
                shop.addProduct(headphone);
                break;
            case 8:
                System.out.println("Enter Toy name:");
                name = input.nextLine();
                System.out.println("Enter Toy price:");
                price = input.nextDouble();
                System.out.println("Enter Toy quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter Toy brand:");
                brand = input.nextLine();
                System.out.println("Enter Toy age range:");
                String ageRange = input.nextLine();
                Toy toy = new Toy(name,price,quantity,comment,brand,ageRange);
                shop.addProduct(toy);
                break;
            case 9:
                System.out.println("Enter product name:");
                name = input.nextLine();
                System.out.println("Enter product price:");
                price = input.nextDouble();
                System.out.println("Enter product quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter product brand:");
                brand = input.nextLine();
                System.out.println("Enter product type:");
                String type = input.nextLine();
                BeautyProduct beautyProduct = new BeautyProduct(name,price,quantity,comment,brand,type);
                break;
            case 10:
                System.out.println("Enter Food name:");
                name = input.nextLine();
                System.out.println("Enter Food price:");
                price = input.nextDouble();
                System.out.println("Enter Food quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                comment = input.nextLine();
                System.out.println("Enter Food brand:");
                brand = input.nextLine();
                System.out.println("Enter Food expiration date:");
                String expirationDate = input.nextLine();
                Food food = new Food(name,price,quantity,comment,brand,expirationDate);
                shop.addProduct(food);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}
