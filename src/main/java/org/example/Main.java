package org.example;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Shop shop = new Shop("My Shop", "www.myshop.com", "909 230 12 02", 0.0);
    static Scanner input = new Scanner(System.in);
    static Admin admin = new Admin("gholi","1234","gholiMoli@gmail.com");
    static User user = null;
    static Seller seller = null;

    public static void main(String[] args) {
        register();
    }

    //---------------------------------------------
    public static void register(){
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Close");
        System.out.println("Enter your choice: ");
        int choice = input.nextInt();

        if (choice == 0){
            System.exit(0);
        }
        if (choice != 1 || choice != 2){
            System.out.println("Invalid Input!");
            register();
        }

        if(choice == 2){
            createAccount();
        }

        System.out.println("Enter username:");
        String username = input.nextLine();
        System.out.println("Enter password:");
        String password = input.nextLine();
        if (shop.doesAccountExist(username) && Objects.equals(shop.findAccountByName(username).getPassword(), password)){

            if(Objects.equals(shop.findAccountByName(username).getInstance(), "Admin")){
                admin =(Admin) shop.findAccountByName(username);
                adminMenu();
            }
            else if(Objects.equals(shop.findAccountByName(username).getInstance(), "Seller")){
                seller = (Seller) shop.findAccountByName(username);
                sellerMenu();
            }
            else if(Objects.equals(shop.findAccountByName(username).getInstance(), "User")){
                user = (User) shop.findAccountByName(username);
                userMenu();
            }
            else{
                System.out.println("Oh Nooooooooooo!");
            }

        }

    }

    public static void createAccount(){
        System.out.println("1. User");
        System.out.println("2. Seller");
        System.out.println("0. Exit");
        System.out.println("Choose your role :");

        int choice = input.nextInt();

        switch (choice){
            case 1:
                System.out.println("Enter username:");
                String username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    createAccount();
                }
                System.out.println("Enter amount of money:");
                double wallet = input.nextDouble();
                System.out.println("Enter password:");
                String password = input.nextLine();
                Seller seller = new Seller(username,password,wallet);
                shop.addToVerifyAccount(seller);
                break;
            case 2:
                System.out.println("Enter username:");
                username = input.nextLine();
                System.out.println("Enter amount of money:");
                wallet = input.nextDouble();
                System.out.println("Enter password:");
                password = input.nextLine();
                System.out.println("Enter email:");
                String email = input.nextLine();
                System.out.println("Enter phone number:");
                String phoneNumber = input.nextLine();
                System.out.println("Enter address:");
                String address = input.nextLine();
                User user = new User(username,password,email,phoneNumber,address,wallet);
                shop.addToVerifyAccount(user);
                break;
            case 0:
                main(null);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    //---------------------------------------------
    public static void sellerMenu(){

        int choice = -1;

            while (choice != 0) {
                System.out.println("1. View available products");
                System.out.println("2. Add a new product");
                System.out.println("3. Transfer Money");
                System.out.println("4. View wallet balance");
                System.out.println("5. Remove a product");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        for (Product p : seller.getAvailableProducts()){
                            System.out.println(p);
                        }
                        break;
                    case 2:
                        addProductBySeller();
                        break;
                    case 3:
                        transferMenuForSeller();
                        break;
                    case 4:
                        System.out.println("your balance: ");
                        System.out.println(seller.getWallet());;
                        break;
                    case 5:
                        System.out.println("Enter product name: ");
                        String name = input.nextLine();
                        if (seller.findProductByName(name).size() == 0){
                            System.out.println("A product with this name was not found in your list!");
                            break;
                        }
                        for (Product p : seller.findProductByName(name)){
                            seller.getAvailableProducts().remove(p);
                        }
                        System.out.println("product has been successfully removed.");
                        break;
                    case 0:
                        main(null);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
        }
    }
    public static void transferMenuForSeller(){
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("Enter your desired operation: ");
        int choice = input.nextInt();
        switch(choice){
            case 1:
                System.out.println("Enter the amount you want to deposit:");
                double amount = input.nextDouble();
                Transfer transfer = new Transfer(seller,amount, LocalDate.now(),"Deposit");
                shop.addToVerifyTransaction(transfer);
                break;
            case 2:
                System.out.println("Enter the amount you want to withdrawal:");
                amount = input.nextDouble();
                if (seller.getWallet() < amount){
                    System.out.println("you don't have enough money in your wallet!");
                    transferMenuForSeller();
                }
                transfer = new Transfer(seller, amount, LocalDate.now(),"Withdrawal");
                shop.addToVerifyTransaction(transfer);
                break;
            default:
                System.out.println("Invalid Input!");
                break;
        }
    }

    //---------------------------------------------
    public static void userMenu(){

    }

    //---------------------------------------------
    public static void adminMenu(){

            System.out.println("1. Add product");
            System.out.println("2. verify order");
            System.out.println("3. Add Admin");
            System.out.println("4. Add profit");
            System.out.println("5. List products");
            System.out.println("6. List orders");
            System.out.println("7. List accounts");
            System.out.println("8. Show total profit");
            System.out.println("9. Verify Accounts");
            System.out.println("10. Verify Transfers");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Add product
                    addProductByAdmin();
                    break;

                case 2:
                    // Add order
                    verifyOrder();
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

                case 9:
                    //authorize Sellers
                    verifyAccount();
                case 10:
                    verifyTransfer();
                    break;
                case 0:
                    // Exit
                    main(null);

                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    adminMenu();
                    break;
            }
    }

    public static void addProductByAdmin(){
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
        System.out.println("0. Exit");
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
                shop.addProduct(beautyProduct);
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
            case 0:
                main(null);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void addProductBySeller(){
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
        System.out.println("0. Exit");
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
                seller.addProducts(electronics);
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
                seller.addProducts(book);
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
                seller.addProducts(clothing);
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
                seller.addProducts(phone);
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
                seller.addProducts(smartWatch);
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
                seller.addProducts(laptop);
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
                seller.addProducts(headphone);
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
                seller.addProducts(toy);
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
                shop.addProduct(beautyProduct);
                seller.addProducts(beautyProduct);
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
                seller.addProducts(food);
                break;
            case 0:
                main(null);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void addAccount(){
        System.out.println("1. Admin");
        System.out.println("2. Seller");
        System.out.println("3. User");
        System.out.println("0. Exit");
        System.out.println("Enter account type:");

        int choice = input.nextInt();

        switch (choice){
            case 1:
                System.out.println("Enter username:");
                String username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    createAccount();
                }
                System.out.println("Enter email:");
                String email = input.nextLine();
                System.out.println("Enter password:");
                String password = input.nextLine();
                Admin admin1 = new Admin(username,password,email);
                shop.addAccount(admin1);
                break;
            case 2:
                System.out.println("Enter username:");
                username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    createAccount();
                }
                System.out.println("Enter amount of money:");
                double wallet = input.nextDouble();
                System.out.println("Enter password:");
                password = input.nextLine();
                Seller seller = new Seller(username,password,wallet);
                shop.addAccount(seller);
                break;
            case 3:
                System.out.println("Enter username:");
                username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    createAccount();
                }
                System.out.println("Enter amount of money:");
                wallet = input.nextDouble();
                System.out.println("Enter password:");
                password = input.nextLine();
                System.out.println("Enter email:");
                email = input.nextLine();
                System.out.println("Enter phone number:");
                String phoneNumber = input.nextLine();
                System.out.println("Enter address:");
                String address = input.nextLine();
                User user = new User(username,password,email,phoneNumber,address,wallet);
                shop.addAccount(user);
                break;
            case 0:
                main(null);
                break;
            default:
                System.out.println("Invalid choice. Please try again!");
                addAccount();
                break;
        }

    }

    public static void verifyOrder(){

        for(Order order : shop.getOrderQueue()){
            System.out.println(order);
            System.out.println("do you want this order to be verified?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("0. Exit");
            int choice = input.nextInt();
            switch (choice){
                case 1 :
                    order.setVerification(true);
                    shop.getOrderQueue().remove(order);
                    break;
                case 2:
                    order.setVerification(false);
                    shop.getOrderQueue().remove(order);
                    break;
                case 0:
                    main(null);
                    break;
                default :
                    System.out.println("Invalid Input!");
                    verifyOrder();
                    break;
            }
            System.out.println("-------------------------");
        }
    }

    public static void verifyAccount(){

        for(Account account : shop.getAccountQueue()){
            System.out.println(account);
            System.out.println("do you want this account to be verified?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("0. Exit");
            int choice = input.nextInt();
            switch (choice){
                case 1 :
                    account.setAuthorization(true);
                    shop.getAccountQueue().remove(account);
                    shop.addAccount(account);
                    break;
                case 2:
                    account.setAuthorization(false);
                    shop.getAccountQueue().remove(account);
                    break;
                case 0:
                    main(null);
                    break;
                default :
                    System.out.println("invalid Input!");
                    verifyAccount();
                    break;
            }
            System.out.println("-------------------------");
        }
    }

    public static void verifyTransfer(){
        for(Transfer transfer : shop.getTransferQueue()){
            System.out.println(transfer);
            System.out.println("do you want this Transfer to be done?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("0. Exit");
            int choice = input.nextInt();
            switch (choice){
                case 1 :
                    if(Objects.equals(transfer.getDepositOrWithdrawal(), "Withdrawal")){
                        if (transfer.isUser()){
                            transfer.getUser().withdrawalFromWallet(transfer.getAmount());
                        }
                        else{
                            transfer.getSeller().withdrawalFromWallet(transfer.getAmount());
                        }
                    }
                    else{
                        if (transfer.isUser()){
                            transfer.getUser().depositToWallet(transfer.getAmount());
                        }
                        else{
                            transfer.getSeller().depositToWallet(transfer.getAmount());
                        }
                    }
                    System.out.println("transfer has been successfully done.");
                    shop.getTransferQueue().remove(transfer);
                    break;
                case 2:
                    shop.getTransferQueue().remove(transfer);
                    System.out.println("transfer request has been denied by admin!");
                    break;
                case 0:
                    main(null);
                    break;
                default :
                    System.out.println("invalid Input!");
                    verifyTransfer();
                    break;
            }
            System.out.println("-------------------------");
        }
    }
    //---------------------------------------------

}
