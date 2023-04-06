package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Shop shop = new Shop("My Shop", "www.myshop.com", "909 230 12 02", 0.0);
    static Scanner input = new Scanner(System.in);
    static Admin admin = new Admin("gholi","1234","gholiMoli@gmail.com");
    static User user = null;
    static Seller seller = null;

    public static void main(String[] args) {
        shop.addAccount(admin);
        user = new User("farid","1234","fkarimi8320@gmail.com","09191771628","thailand");
        ArrayList<Product> purchased = new ArrayList<>();
        Product produc = new Product("iphone11",1200, 1, "iphone");
        produc.setSeller(new Seller("unknown","unknown"));
        shop.addProduct(produc);
        purchased.add(new Product("iphone12",1200, 1, "iphone"));
        purchased.add(new Product("iphone13",1200, 1, "iphone"));
        purchased.add(new Product("iphone14",1200, 1, "iphone"));
        for (Product product : purchased){
            product.setSeller(new Seller("unknown","unknown"));
        }
        user.getOrders().add(new Order(LocalDate.now(), user, purchased));
        user.getOrders().add(new Order(LocalDate.now(), user, purchased));
        user.getOrders().add(new Order(LocalDate.now(), user, purchased));
        shop.addAccount(user);
        userMenu();
    }

    //---------------------------------------------
    public static void register(){
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Close");
        System.out.println("Enter your choice: ");
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
        System.out.println("Enter username:");
        String username = input.nextLine();
        System.out.println("Enter password:");
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
        System.out.println("1. Seller");
        System.out.println("2. User");
        System.out.println("0. Exit");
        System.out.println("Choose your role :");

        int choice = input.nextInt();

        switch (choice){
            case 1:
                input.nextLine();
                System.out.println("Enter username:");
                String username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    createAccount();
                }
                System.out.println("Enter amount of money:");
                double wallet = input.nextDouble();
                System.out.println("Enter password:");
                input.nextLine();
                String password = input.nextLine();
                Seller seller = new Seller(username, password, wallet);
                shop.addToVerifyAccount(seller);
                break;
            case 2:
                input.nextLine();
                System.out.println("Enter username:");
                username = input.nextLine();
                System.out.println("Enter amount of money:");
                wallet = input.nextDouble();
                System.out.println("Enter password:");
                input.nextLine();
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

        int choice = -1;

            while (choice != 0) {
                System.out.println("------------SELLER MENU------------");
                System.out.println("1. View available products");
                System.out.println("2. Add a new product");
                System.out.println("3. Transfer Money");
                System.out.println("4. View wallet balance");
                System.out.println("5. Remove a product");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                choice = input.nextInt();

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
                        register();
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
                register();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    //-------------------User---------------------
    public static void userMenu(){

        System.out.println("------------USER MENU------------");
        System.out.println("1. View profile");
        System.out.println("2. View shopping cart");
        System.out.println("3. Add product to cart");
        System.out.println("4. Remove product from cart");
        System.out.println("5. Checkout");
        System.out.println("6. View order history");
        System.out.println("7. View purchased products");
        System.out.println("8. View wallet balance");
        System.out.println("9. Add funds to wallet");
        System.out.println("10. find products by name");
        System.out.println("11. find products by price range");
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
                addProductToCart();
                break;
            case 4:
                removeProductFromCart();
                break;
            case 5:
                user.checkout(shop);
                break;
            case 6:
                viewOrderHistory();
                break;
            case 7:
                viewPurchasedProducts();
                break;
            case 8:
                user.viewWalletBalance();
                break;
            case 9:
                transferMenuForUser();
                break;
            case 10:
                findProductByName();
                break;
            case 11:
                findProductsByPriceRange();
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
        System.out.println("Enter products name: ");
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
        System.out.println("Enter product name: ");
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
        System.out.println("Enter your desired operation: ");
        int choice = input.nextInt();
        switch(choice){
            case 1:
                System.out.println("Enter the amount you want to deposit:");
                double amount = input.nextDouble();
                Transfer transfer = new Transfer(user,amount, LocalDate.now(),"Deposit");
                shop.addToVerifyTransaction(transfer);
                break;
            case 2:
                System.out.println("Enter the amount you want to withdrawal:");
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
            System.out.println("2. verify order");
            System.out.println("3. Add Account");
            System.out.println("4. Add profit");
            System.out.println("5. List products");
            System.out.println("6. Verify Orders");
            System.out.println("7. List accounts");
            System.out.println("8. Show total profit");
            System.out.println("9. Verify Accounts");
            System.out.println("10. Verify Transfers");
            System.out.println("11. find Account");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    addProductByAdmin();
                    break;

                case 2:
                    verifyOrder();
                    break;

                case 3:
                    addAccount();
                    break;

                case 4:
                    System.out.print("Enter profit amount: ");
                    double profitAmount = input.nextDouble();
                    shop.addProfit(profitAmount);
                    System.out.println("Profit added successfully!");
                    System.out.println("-------------------------");
                    break;

                case 5:
                    System.out.println("Product list:");
                    for (Product p : shop.getProductList()) {
                        System.out.println(p);
                    }
                    break;

                case 6:
                    verifyOrder();
                    break;

                case 7:
                    System.out.println("Account list:");
                    for (Account acc : shop.getAccountList()) {
                        System.out.println(acc);
                    }
                    break;

                case 8:
                    System.out.println("-------------------------");
                    System.out.println("Total profit: " + shop.getTotalProfit());
                    System.out.println("-------------------------");
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
        System.out.println("Enter username:");
        input.nextLine();
        String name = input.nextLine();
        System.out.println(shop.findAccountByName(name));
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
                input.nextLine();
                System.out.println("Enter product name:");
                String name = input.nextLine();
                System.out.println("Enter product price:");
                double price = input.nextDouble();
                System.out.println("Enter product quantity:");
                int quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
                String comment = input.nextLine();
                System.out.println("Enter product brand:");
                String brand = input.nextLine();
                System.out.println("Enter product model:");
                String model = input.nextLine();
                Electronics electronics = new Electronics(name,price,quantity,comment,brand,model);
                electronics.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(electronics);
                break;
            case 2:
                input.nextLine();
                System.out.println("Enter Book name:");
                name = input.nextLine();
                System.out.println("Enter Book price:");
                price = input.nextDouble();
                System.out.println("Enter Book quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
                comment = input.nextLine();
                System.out.println("Enter Book ISBN:");
                String isbn = input.nextLine();
                System.out.println("Enter Book author:");
                String author = input.nextLine();
                Book book = new Book(name,price,quantity,comment,author,isbn);
                book.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(book);
                break;
            case 3:
                input.nextLine();
                System.out.println("Enter Clothes name:");
                name = input.nextLine();
                System.out.println("Enter Clothes price:");
                price = input.nextDouble();
                System.out.println("Enter Clothes quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
                comment = input.nextLine();
                System.out.println("Enter Clothes size:");
                String size = input.nextLine();
                System.out.println("Enter Clothes color:");
                String color = input.nextLine();
                System.out.println("Enter Clothes gender:");
                String gender = input.nextLine();
                Clothing clothing = new Clothing(name,price,quantity,comment,size,color,gender);
                clothing.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(clothing);
                break;
            case 4:
                input.nextLine();
                System.out.println("Enter phone name:");
                name = input.nextLine();
                System.out.println("Enter phone price:");
                price = input.nextDouble();
                System.out.println("Enter phone quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
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
                phone.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(phone);
                break;
            case 5:
                input.nextLine();
                System.out.println("Enter watch name:");
                name = input.nextLine();
                System.out.println("Enter watch price:");
                price = input.nextDouble();
                System.out.println("Enter watch quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
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
                smartWatch.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(smartWatch);
                break;
            case 6:
                input.nextLine();
                System.out.println("Enter laptop name:");
                name = input.nextLine();
                System.out.println("Enter laptop price:");
                price = input.nextDouble();
                System.out.println("Enter laptop quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
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
                laptop.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(laptop);
                break;
            case 7:
                input.nextLine();
                System.out.println("Enter HeadPhone name:");
                name = input.nextLine();
                System.out.println("Enter HeadPhone price:");
                price = input.nextDouble();
                System.out.println("Enter HeadPhone quantity:");
                quantity = input.nextInt();
                input.nextLine();
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
                headphone.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(headphone);
                break;
            case 8:
                input.nextLine();
                System.out.println("Enter Toy name:");
                name = input.nextLine();
                System.out.println("Enter Toy price:");
                price = input.nextDouble();
                System.out.println("Enter Toy quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
                comment = input.nextLine();
                System.out.println("Enter Toy brand:");
                brand = input.nextLine();
                System.out.println("Enter Toy age range:");
                String ageRange = input.nextLine();
                Toy toy = new Toy(name,price,quantity,comment,brand,ageRange);
                toy.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(toy);
                break;
            case 9:
                input.nextLine();
                System.out.println("Enter product name:");
                name = input.nextLine();
                System.out.println("Enter product price:");
                price = input.nextDouble();
                System.out.println("Enter product quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
                comment = input.nextLine();
                System.out.println("Enter product brand:");
                brand = input.nextLine();
                System.out.println("Enter product type:");
                String type = input.nextLine();
                BeautyProduct beautyProduct = new BeautyProduct(name,price,quantity,comment,brand,type);
                beautyProduct.setSeller(new Seller("unknown", "unknown"));
                shop.addProduct(beautyProduct);
                break;
            case 10:
                input.nextLine();
                System.out.println("Enter Food name:");
                name = input.nextLine();
                System.out.println("Enter Food price:");
                price = input.nextDouble();
                System.out.println("Enter Food quantity:");
                quantity = input.nextInt();
                System.out.println("Enter any additional info:");
                input.nextLine();
                comment = input.nextLine();
                System.out.println("Enter Food brand:");
                brand = input.nextLine();
                System.out.println("Enter Food expiration date:");
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
        System.out.println("Enter account type:");

        int choice = input.nextInt();

        switch (choice){
            case 1:
                input.nextLine();
                System.out.println("Enter username:");
                String username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    addAccount();
                }
                System.out.println("Enter email:");
                String email = input.nextLine();
                System.out.println("Enter password:");
                String password = input.nextLine();
                Admin admin1 = new Admin(username,password,email);
                shop.addAccount(admin1);
                break;
            case 2:
                input.nextLine();
                System.out.println("Enter username:");
                username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    addAccount();
                }
                System.out.println("Enter amount of money:");
                double wallet = input.nextDouble();
                System.out.println("Enter password:");
                input.nextLine();
                password = input.nextLine();
                Seller seller = new Seller(username,password,wallet);
                shop.addAccount(seller);
                break;
            case 3:
                input.nextLine();
                System.out.println("Enter username:");
                username = input.nextLine();
                if (shop.doesAccountExist(username)){
                    System.out.println("sorry this username already exist!");
                    addAccount();
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
