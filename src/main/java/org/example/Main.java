package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Shop shop = new Shop("My Shop", "www.DigiMart.com", "909 230 12 02", 0.0);
    static Scanner input = new Scanner(System.in);
    static Admin admin = new Admin("gholi","1234","gholiMoli@gmail.com");
    static User user = null;
    static Seller seller = null;

    public static void main(String[] args) {
        shop.addAccount(admin);
        adminMenu();

    }

    //---------------------------------------------
    public static void register(){
        System.out.println("------------ WELCOME ------------");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Close");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 0){
            System.exit(0);
        }
        if (choice != 1 && choice != 2){
            System.out.println("Invalid Input!");
            register();
        }

        if(choice == 2){
            createAccount();

        }
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        if (!shop.doesAccountExist(username)){
            System.out.println("username is incorrect!");
            register();
        }

        if (Objects.equals(shop.findAccountByName(username).getPassword(), password)){

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
                System.out.println("Oh Noooooo!");
            }

        }
        else{
            System.out.println("password is incorrect!");
            register();
        }

    }

    public static void createAccount(){
        System.out.println("------------CREATE ACCOUNT------------");
        System.out.println("1. Seller");
        System.out.println("2. User");
        System.out.println("0. Exit");
        System.out.print("Choose your role : ");

        int choice = input.nextInt();

        switch (choice){
            case 1:
                input.nextLine();
                System.out.print("Enter username: ");
                String username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    createAccount();
                }
                System.out.print("Enter amount of money: ");
                double wallet = input.nextDouble();
                System.out.print("Enter password: ");
                input.nextLine();
                String password = input.nextLine();
                Seller seller = new Seller(username, password, wallet);
                shop.addToVerifyAccount(seller);
                break;
            case 2:
                input.nextLine();
                System.out.print("Enter username: ");
                username = input.nextLine();
                System.out.print("Enter amount of money: ");
                wallet = input.nextDouble();
                System.out.print("Enter password: ");
                input.nextLine();
                password = input.nextLine();
                System.out.print("Enter email: ");
                String email = input.nextLine();
                System.out.print("Enter phone number: ");
                String phoneNumber = input.nextLine();
                System.out.print("Enter address: ");
                String address = input.nextLine();
                User user = new User(username, password, email, phoneNumber, address, wallet);
                shop.addToVerifyAccount(user);
                break;
            case 0:
                register();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        register();
    }

    //------------------Seller-----------------------
    public static void sellerMenu(){
        System.out.println("------------SELLER MENU------------");
        System.out.println("1. View available products");
        System.out.println("2. Add a new product");
        System.out.println("3. Find products");
        System.out.println("4. Remove a product");
        System.out.println("5. Transfer Money");
        System.out.println("6. View wallet balance");
        System.out.println("0. Exit");

        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                ViewAvailableProducts();
                break;

            case 2:
                addProductBySeller();
                break;

            case 3:
                findAvailableProducts();
                break;

            case 4:
                removeProductByName();
                break;

            case 5:
                transferMenuForSeller();
                break;

            case 6:
                System.out.println("your balance: ");
                System.out.println(seller.getWallet());
                break;

            case 0:
                register();
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        sellerMenu();
    }

    public static void ViewAvailableProducts(){
        if (seller.getAvailableProducts().isEmpty()){
            System.out.println("You don't have any available product right now!");
            return;
        }
        System.out.println("Your available products: ");
        for (Product p : seller.getAvailableProducts()){
            System.out.println(p);
        }
    }

    public static void removeProductByName(){
        System.out.print("Enter product name: ");
        input.nextLine();
        String name = input.nextLine();
        if (seller.findProductByName(name).size() == 0){
            System.out.println("A product with this name was not found in your list!");
            return;
        }
        for (Product p : seller.findProductByName(name)){
            seller.getAvailableProducts().remove(p);
            System.out.println(p.getName() + " has been successfully removed.");
        }
    }

    public static void transferMenuForSeller(){
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.print("Enter your desired operation: ");
        int choice = input.nextInt();
        switch(choice){
            case 1:
                System.out.print("Enter the amount you want to deposit: ");
                double amount = input.nextDouble();
                Transfer transfer = new Transfer(seller,amount, LocalDate.now(),"Deposit");
                shop.addToVerifyTransaction(transfer);
                break;
            case 2:
                System.out.print("Enter the amount you want to withdrawal: ");
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

    public static void addProductBySeller(){
        System.out.println("------------CHOOSE A CATEGORY------------");
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
                input.nextLine();
                System.out.print("Enter product name: ");
                String name = input.nextLine();
                System.out.print("Enter product price: ");
                double price = input.nextDouble();
                System.out.print("Enter product quantity: ");
                int quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                String comment = input.nextLine();
                System.out.print("Enter product brand: ");
                String brand = input.nextLine();
                System.out.print("Enter product model: ");
                String model = input.nextLine();
                Electronics electronics = new Electronics(name,price,quantity,comment,brand,model);
                shop.addProduct(electronics);
                seller.addProducts(electronics);
                break;
            case 2:
                input.nextLine();
                System.out.print("Enter Book name: ");
                name = input.nextLine();
                System.out.print("Enter Book price: ");
                price = input.nextDouble();
                System.out.print("Enter Book quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter Book ISBN: ");
                String isbn = input.nextLine();
                System.out.print("Enter Book author: ");
                String author = input.nextLine();
                Book book = new Book(name,price,quantity,comment,author,isbn);
                shop.addProduct(book);
                seller.addProducts(book);
                break;
            case 3:
                input.nextLine();
                System.out.print("Enter Clothes name: ");
                name = input.nextLine();
                System.out.print("Enter Clothes price: ");
                price = input.nextDouble();
                System.out.print("Enter Clothes quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter Clothes size: ");
                String size = input.nextLine();
                System.out.print("Enter Clothes color: ");
                String color = input.nextLine();
                System.out.print("Enter Clothes gender: ");
                String gender = input.nextLine();
                Clothing clothing = new Clothing(name,price,quantity,comment,size,color,gender);
                shop.addProduct(clothing);
                seller.addProducts(clothing);
                break;
            case 4:
                input.nextLine();
                System.out.print("Enter phone name: ");
                name = input.nextLine();
                System.out.print("Enter phone price: ");
                price = input.nextDouble();
                System.out.print("Enter phone quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter phone brand: ");
                brand = input.nextLine();
                System.out.print("Enter phone model: ");
                model = input.nextLine();
                System.out.print("Enter phone screen Size: ");
                String screenSize = input.nextLine();
                System.out.print("Enter phone operating System: ");
                String operatingSystem = input.nextLine();
                System.out.print("Enter phone cameraSpecs: ");
                String cameraSpecs = input.nextLine();
                System.out.print("Enter phone memory capacity: ");
                int memory = input.nextInt();
                Phone phone = new Phone(name,price,quantity,comment,brand,model,screenSize,operatingSystem,cameraSpecs,memory);
                shop.addProduct(phone);
                seller.addProducts(phone);
                break;
            case 5:
                input.nextLine();
                System.out.print("Enter watch name: ");
                name = input.nextLine();
                System.out.print("Enter watch price: ");
                price = input.nextDouble();
                System.out.print("Enter watch quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter watch brand: ");
                brand = input.nextLine();
                System.out.print("Enter watch model: ");
                model = input.nextLine();
                System.out.print("Enter watch band Type: ");
                String bandType = input.nextLine();
                System.out.print("Enter watch build Material: ");
                String buildMaterial = input.nextLine();
                System.out.print("Enter watch screen Size: ");
                screenSize = input.nextLine();
                SmartWatch smartWatch = new SmartWatch(name,price,quantity,comment,brand,model,bandType,buildMaterial,screenSize);
                shop.addProduct(smartWatch);
                seller.addProducts(smartWatch);
                break;
            case 6:
                input.nextLine();
                System.out.print("Enter laptop name: ");
                name = input.nextLine();
                System.out.print("Enter laptop price: ");
                price = input.nextDouble();
                System.out.print("Enter laptop quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter laptop brand: ");
                brand = input.nextLine();
                System.out.print("Enter laptop model: ");
                model = input.nextLine();
                System.out.print("Enter laptop screen Size: ");
                screenSize = input.nextLine();
                System.out.print("Enter laptop operating System: ");
                operatingSystem = input.nextLine();
                System.out.print("Enter laptop CPU model: ");
                String cpu = input.nextLine();
                System.out.print("Enter laptop GPU model: ");
                String gpu = input.nextLine();
                Laptop laptop = new Laptop(name,price,quantity,comment,brand,model,screenSize,operatingSystem,cpu,gpu);
                shop.addProduct(laptop);
                seller.addProducts(laptop);
                break;
            case 7:
                input.nextLine();
                System.out.print("Enter HeadPhone name: ");
                name = input.nextLine();
                System.out.print("Enter HeadPhone price: ");
                price = input.nextDouble();
                System.out.print("Enter HeadPhone quantity: ");
                quantity = input.nextInt();
                input.nextLine();
                System.out.print("Enter any additional info: ");
                comment = input.nextLine();
                System.out.print("Enter HeadPhone brand: ");
                brand = input.nextLine();
                System.out.print("Enter HeadPhone model: ");
                model = input.nextLine();
                System.out.print("Enter HeadPhone connection Type: ");
                String connectionType = input.nextLine();
                System.out.print("Enter HeadPhone battery Capacity: ");
                String batteryCapacity = input.nextLine();
                Headphone headphone = new Headphone(name,price,quantity,comment,brand,model,connectionType,batteryCapacity);
                shop.addProduct(headphone);
                seller.addProducts(headphone);
                break;
            case 8:
                input.nextLine();
                System.out.print("Enter Toy name: ");
                name = input.nextLine();
                System.out.print("Enter Toy price: ");
                price = input.nextDouble();
                System.out.print("Enter Toy quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter Toy brand: ");
                brand = input.nextLine();
                System.out.print("Enter Toy age range: ");
                String ageRange = input.nextLine();
                Toy toy = new Toy(name,price,quantity,comment,brand,ageRange);
                shop.addProduct(toy);
                seller.addProducts(toy);
                break;
            case 9:
                input.nextLine();
                System.out.print("Enter product name: ");
                name = input.nextLine();
                System.out.print("Enter product price: ");
                price = input.nextDouble();
                System.out.print("Enter product quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter product brand: ");
                brand = input.nextLine();
                System.out.print("Enter product type: ");
                String type = input.nextLine();
                BeautyProduct beautyProduct = new BeautyProduct(name,price,quantity,comment,brand,type);
                shop.addProduct(beautyProduct);
                seller.addProducts(beautyProduct);
                break;
            case 10:
                input.nextLine();
                System.out.print("Enter Food name: ");
                name = input.nextLine();
                System.out.print("Enter Food price: ");
                price = input.nextDouble();
                System.out.print("Enter Food quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter Food brand: ");
                brand = input.nextLine();
                System.out.print("Enter Food expiration date: ");
                String expirationDate = input.nextLine();
                Food food = new Food(name,price,quantity,comment,brand,expirationDate);
                shop.addProduct(food);
                seller.addProducts(food);
                break;
            case 0:
                sellerMenu();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        sellerMenu();
    }

    public static void findAvailableProducts(){
        System.out.print("Enter products name: ");
        input.nextLine();
        String name = input.nextLine();
        if(!seller.doesProductExist(name)){
            System.out.println("the product you're looking for doesn't exist!");
            sellerMenu();
        }
        System.out.println("Your Results: ");
        for (Product product : seller.findProductByName(name)){
            System.out.println(product);
        }
    }

    //-------------------User---------------------
    public static void userMenu(){

        System.out.println("------------USER MENU------------");
        System.out.println("1. View profile");
        System.out.println("2. View shopping cart");
        System.out.println("3. View purchased products");
        System.out.println("4. View wallet balance");
        System.out.println("5. View order history");
        System.out.println("6. Add product to cart");
        System.out.println("7. Transfer money");
        System.out.println("8. Remove product from cart");
        System.out.println("9. find products by name");
        System.out.println("10. find products by price range");
        System.out.println("11. Checkout");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println(user);
                break;
            case 2:
                user.viewCart();
                break;
            case 3:
                viewPurchasedProducts();
                break;
            case 4:
                user.viewWalletBalance();
                break;
            case 5:
                viewOrderHistory();
                break;
            case 6:
                addProductToCart();
                break;
            case 7:
                transferMenuForUser();
                break;
            case 8:
                removeProductFromCart();
                break;
            case 9:
                findProductByName();
                break;
            case 10:
                findProductsByPriceRange();
                break;
            case 11:
                user.checkout(shop);
                break;
            case 0:
                register();
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                break;
        }
        userMenu();
    }

    public static void findProductByName(){
        System.out.print("Enter products name: ");
        input.nextLine();
        String name = input.nextLine();
        if(!shop.doesProductExist(name)){
            System.out.println("the product you're looking for doesn't exist!");
            userMenu();
        }
        System.out.println("Your Results: ");
        for (Product product : shop.findProductByName(name)){
            System.out.println(product);
        }
    }

    public static void findProductsByPriceRange(){
        System.out.print("Enter minimum price: ");
        double minPrice = input.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = input.nextDouble();

        for (Product product : shop.findProductsByPriceRange(minPrice, maxPrice)) {
            System.out.println(product);
        }
    }

    public static void addProductToCart() {
        System.out.print("Enter product name: ");
        input.nextLine();
        String productName = input.nextLine();
        ArrayList<Product> result = shop.findProductByName(productName);
        if (result.isEmpty()) {
            System.out.println("Product not found.");
        }
        else {
            for (Product product : result){
                if (product.getQuantity() == 0 ){
                    System.out.println("this product is unavailable for now!");
                    return;
                }
                user.addToCart(product);
                System.out.println(product.getName() + " has been added to your cart.");
            }
        }
    }

    public static void removeProductFromCart(){

        System.out.print("Enter product name: ");
        input.nextLine();
        String productName = input.nextLine();
        ArrayList<Product> result = shop.findProductByName(productName);
        if (result.size() == 0) {
            System.out.println("Product not found.");
        }
        else {
            for (Product product : result){
                user.removeFromCart(product);
                System.out.println(product.getName() + " has been removed from your cart.");
            }
        }
    }

    public static void viewOrderHistory(){
        if (user.getOrders().isEmpty()){
            System.out.println("You dont have any previous orders!");
            return;
        }
        for (Order order : user.getOrders()){
            System.out.println(order);
        }
    }

    public static void viewPurchasedProducts(){
        if (user.getPurchased().isEmpty()){
            System.out.println("You don't have any purchased products!");
            return;
        }
        for (Product product : user.getPurchased()){
            System.out.println(product);
        }
    }

    public static void transferMenuForUser(){
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.print("Enter your desired operation: ");
        int choice = input.nextInt();
        switch(choice){
            case 1:
                System.out.print("Enter the amount you want to deposit: ");
                double amount = input.nextDouble();
                Transfer transfer = new Transfer(user,amount, LocalDate.now(),"Deposit");
                shop.addToVerifyTransaction(transfer);
                break;
            case 2:
                System.out.print("Enter the amount you want to withdrawal: ");
                amount = input.nextDouble();
                if (user.getWallet() < amount){
                    System.out.println("you don't have enough money in your wallet!");
                    transferMenuForSeller();
                }
                transfer = new Transfer(user, amount, LocalDate.now(),"Withdrawal");
                shop.addToVerifyTransaction(transfer);
                break;
            default:
                System.out.println("Invalid Input!");
                break;
        }
    }

    //-------------------Admin--------------------
    public static void adminMenu(){
            System.out.println("------------ADMIN MENU------------");
            System.out.println("1. Add product");
            System.out.println("2. Add Account");
            System.out.println("3. Add profit");
            System.out.println("4. View products");
            System.out.println("5. View accounts");
            System.out.println("6. View total profit");
            System.out.println("7. verify order");
            System.out.println("8. Verify Orders");
            System.out.println("9. Verify Accounts");
            System.out.println("10. Verify Transfers");
            System.out.println("11. Find account");
            System.out.println("12. Find products");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    addProductByAdmin();
                    break;

                case 2:
                    addAccount();
                    break;

                case 3:
                    System.out.print("Enter profit amount: ");
                    double profitAmount = input.nextDouble();
                    shop.addProfit(profitAmount);
                    System.out.println("Profit added successfully!");
                    System.out.println("-------------------------");
                    break;

                case 4:
                    System.out.println("Product list: ");
                    for (Product p : shop.getProductList()) {
                        System.out.println(p);
                    }
                    break;

                case 5:
                    System.out.println("Account list: ");
                    for (Account acc : shop.getAccountList()) {
                        System.out.println(acc);
                    }
                    break;

                case 6:
                    System.out.println("-------------------------");
                    System.out.println("Total profit: " + shop.getTotalProfit());
                    System.out.println("-------------------------");
                    break;

                case 7:
                    verifyOrder();
                    break;

                case 8:
                    verifyOrder();
                    break;

                case 9:
                    verifyAccount();
                    break;

                case 10:
                    verifyTransfer();
                    break;

                case 11:
                    findAccountByName();
                    break;

                case 12:
                    findProductByName();
                    break;

                case 0:
                    register();
                    break;

                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    adminMenu();
                    break;
            }
            adminMenu();
    }

    public static void findAccountByName(){
        System.out.print("Enter username: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.println(shop.findAccountByName(name));
    }

    public static void addProductByAdmin(){
        System.out.println("------------CHOOSE A CATEGORY------------");
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
                input.nextLine();
                System.out.print("Enter product name: ");
                String name = input.nextLine();
                System.out.print("Enter product price: ");
                double price = input.nextDouble();
                System.out.print("Enter product quantity: ");
                int quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                String comment = input.nextLine();
                System.out.print("Enter product brand: ");
                String brand = input.nextLine();
                System.out.print("Enter product model: ");
                String model = input.nextLine();
                Electronics electronics = new Electronics(name,price,quantity,comment,brand,model);
                electronics.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(electronics);
                break;
            case 2:
                input.nextLine();
                System.out.print("Enter Book name: ");
                name = input.nextLine();
                System.out.print("Enter Book price: ");
                price = input.nextDouble();
                System.out.print("Enter Book quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter Book ISBN: ");
                String isbn = input.nextLine();
                System.out.print("Enter Book author: ");
                String author = input.nextLine();
                Book book = new Book(name,price,quantity,comment,author,isbn);
                book.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(book);
                break;
            case 3:
                input.nextLine();
                System.out.print("Enter Clothes name: ");
                name = input.nextLine();
                System.out.print("Enter Clothes price: ");
                price = input.nextDouble();
                System.out.print("Enter Clothes quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter Clothes size: ");
                String size = input.nextLine();
                System.out.print("Enter Clothes color: ");
                String color = input.nextLine();
                System.out.print("Enter Clothes gender: ");
                String gender = input.nextLine();
                Clothing clothing = new Clothing(name,price,quantity,comment,size,color,gender);
                clothing.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(clothing);
                break;
            case 4:
                input.nextLine();
                System.out.print("Enter phone name: ");
                name = input.nextLine();
                System.out.print("Enter phone price: ");
                price = input.nextDouble();
                System.out.print("Enter phone quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter phone brand: ");
                brand = input.nextLine();
                System.out.print("Enter phone model: ");
                model = input.nextLine();
                System.out.print("Enter phone screen Size: ");
                String screenSize = input.nextLine();
                System.out.print("Enter phone operating System: ");
                String operatingSystem = input.nextLine();
                System.out.print("Enter phone cameraSpecs: ");
                String cameraSpecs = input.nextLine();
                System.out.print("Enter phone memory capacity: ");
                int memory = input.nextInt();
                Phone phone = new Phone(name,price,quantity,comment,brand,model,screenSize,operatingSystem,cameraSpecs,memory);
                phone.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(phone);
                break;
            case 5:
                input.nextLine();
                System.out.print("Enter watch name: ");
                name = input.nextLine();
                System.out.print("Enter watch price: ");
                price = input.nextDouble();
                System.out.print("Enter watch quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter watch brand: ");
                brand = input.nextLine();
                System.out.print("Enter watch model: ");
                model = input.nextLine();
                System.out.print("Enter watch band Type: ");
                String bandType = input.nextLine();
                System.out.print("Enter watch build Material: ");
                String buildMaterial = input.nextLine();
                System.out.print("Enter watch screen Size: ");
                screenSize = input.nextLine();
                SmartWatch smartWatch = new SmartWatch(name,price,quantity,comment,brand,model,bandType,buildMaterial,screenSize);
                smartWatch.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(smartWatch);
                break;
            case 6:
                input.nextLine();
                System.out.print("Enter laptop name: ");
                name = input.nextLine();
                System.out.print("Enter laptop price: ");
                price = input.nextDouble();
                System.out.print("Enter laptop quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter laptop brand: ");
                brand = input.nextLine();
                System.out.print("Enter laptop model: ");
                model = input.nextLine();
                System.out.print("Enter laptop screen Size: ");
                screenSize = input.nextLine();
                System.out.print("Enter laptop operating System: ");
                operatingSystem = input.nextLine();
                System.out.print("Enter laptop CPU model: ");
                String cpu = input.nextLine();
                System.out.print("Enter laptop GPU model: ");
                String gpu = input.nextLine();
                Laptop laptop = new Laptop(name,price,quantity,comment,brand,model,screenSize,operatingSystem,cpu,gpu);
                laptop.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(laptop);
                break;
            case 7:
                input.nextLine();
                System.out.print("Enter HeadPhone name: ");
                name = input.nextLine();
                System.out.print("Enter HeadPhone price: ");
                price = input.nextDouble();
                System.out.print("Enter HeadPhone quantity: ");
                quantity = input.nextInt();
                input.nextLine();
                System.out.print("Enter any additional info: ");
                comment = input.nextLine();
                System.out.print("Enter HeadPhone brand: ");
                brand = input.nextLine();
                System.out.print("Enter HeadPhone model: ");
                model = input.nextLine();
                System.out.print("Enter HeadPhone connection Type: ");
                String connectionType = input.nextLine();
                System.out.print("Enter HeadPhone battery Capacity: ");
                String batteryCapacity = input.nextLine();
                Headphone headphone = new Headphone(name,price,quantity,comment,brand,model,connectionType,batteryCapacity);
                headphone.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(headphone);
                break;
            case 8:
                input.nextLine();
                System.out.print("Enter Toy name: ");
                name = input.nextLine();
                System.out.print("Enter Toy price: ");
                price = input.nextDouble();
                System.out.print("Enter Toy quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter Toy brand: ");
                brand = input.nextLine();
                System.out.print("Enter Toy age range: ");
                String ageRange = input.nextLine();
                Toy toy = new Toy(name,price,quantity,comment,brand,ageRange);
                toy.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(toy);
                break;
            case 9:
                input.nextLine();
                System.out.print("Enter product name: ");
                name = input.nextLine();
                System.out.print("Enter product price: ");
                price = input.nextDouble();
                System.out.print("Enter product quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter product brand: ");
                brand = input.nextLine();
                System.out.print("Enter product type: ");
                String type = input.nextLine();
                BeautyProduct beautyProduct = new BeautyProduct(name,price,quantity,comment,brand,type);
                beautyProduct.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(beautyProduct);
                break;
            case 10:
                input.nextLine();
                System.out.print("Enter Food name: ");
                name = input.nextLine();
                System.out.print("Enter Food price: ");
                price = input.nextDouble();
                System.out.print("Enter Food quantity: ");
                quantity = input.nextInt();
                System.out.print("Enter any additional info: ");
                input.nextLine();
                comment = input.nextLine();
                System.out.print("Enter Food brand: ");
                brand = input.nextLine();
                System.out.print("Enter Food expiration date: ");
                String expirationDate = input.nextLine();
                Food food = new Food(name,price,quantity,comment,brand,expirationDate);
                food.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(food);
                break;
            case 0:
                adminMenu();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        adminMenu();
    }

    public static void addAccount(){
        System.out.println("1. Admin");
        System.out.println("2. Seller");
        System.out.println("3. User");
        System.out.println("0. Exit");
        System.out.print("Enter account type: ");

        int choice = input.nextInt();

        switch (choice){
            case 1:
                input.nextLine();
                System.out.print("Enter username: ");
                String username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    addAccount();
                }
                System.out.print("Enter email: ");
                String email = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();
                Admin admin1 = new Admin(username,password,email);
                shop.addAccount(admin1);
                break;
            case 2:
                input.nextLine();
                System.out.print("Enter username: ");
                username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    addAccount();
                }
                System.out.print("Enter amount of money: ");
                double wallet = input.nextDouble();
                System.out.print("Enter password: ");
                input.nextLine();
                password = input.nextLine();
                Seller seller = new Seller(username,password,wallet);
                shop.addAccount(seller);
                break;
            case 3:
                input.nextLine();
                System.out.print("Enter username: ");
                username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    addAccount();
                }
                System.out.print("Enter amount of money: ");
                wallet = input.nextDouble();
                System.out.print("Enter password: ");
                password = input.nextLine();
                System.out.print("Enter email: ");
                email = input.nextLine();
                System.out.print("Enter phone number: ");
                String phoneNumber = input.nextLine();
                System.out.print("Enter address: ");
                String address = input.nextLine();
                User user = new User(username, password, email, phoneNumber, address, wallet);
                shop.addAccount(user);
                break;
            case 0:
                adminMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again!");
                addAccount();
                break;
        }
        adminMenu();
    }

    public static void verifyOrder() {
        Iterator<Order> iterator = shop.getOrderQueue().iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            System.out.println(order);
            System.out.println("do you want this order to be verified?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("0. Exit");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    order.setVerification(true);
                    for (Product product : order.getPurchasedProducts()) {
                        product.setQuantity(product.getQuantity() - 1);
                        product.getSeller().depositToWallet(product.getPrice() * 0.9);
                        order.getUser().addToPurchased(product);
                    }
                    shop.addProfit(order.getTotalPrice() * 0.1);
                    order.getUser().withdrawalFromWallet(order.getTotalPrice());
                    iterator.remove();
                    break;
                case 2:
                    order.setVerification(false);
                    iterator.remove();
                    break;
                case 0:
                    adminMenu();
                    return;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
        adminMenu();
    }

    public static void verifyAccount() {
        Iterator<Account> iterator = shop.getAccountQueue().iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            System.out.println(account);
            System.out.println("do you want this account to be verified?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("0. Exit");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    account.setAuthorization(true);
                    iterator.remove();
                    shop.addAccount(account);
                    break;
                case 2:
                    account.setAuthorization(false);
                    iterator.remove();
                    break;
                case 0:
                    adminMenu();
                    return;
                default:
                    System.out.println("invalid Input!");
                    break;
            }
        }
        adminMenu();
    }

    public static void verifyTransfer(){
        Iterator<Transfer> iterator = shop.getTransferQueue().iterator();
        while (iterator.hasNext()) {
            Transfer transfer = iterator.next();
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
                    iterator.remove();
                    break;
                case 2:
                    iterator.remove();
                    System.out.println("transfer request has been denied by admin!");
                    break;
                case 0:
                    adminMenu();
                    break;
                default :
                    System.out.println("invalid Input!");
                    break;
            }

        }
        adminMenu();
    }

    //---------------------------------------------

}
