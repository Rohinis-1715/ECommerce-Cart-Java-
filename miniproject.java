import java.util.*;

// ===== Product Class =====
class Product {
    private String name;
    private double price;
    private String genderCategory; // e.g., "Male", "Female", "Unisex"

    public Product(String name, double price, String genderCategory) {
        this.name = name;
        this.price = price;
        this.genderCategory = genderCategory;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getGenderCategory() { return genderCategory; }
}

// ===== Cart Class =====
class Cart {
    private List<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(p);
        System.out.println("✅ " + p.getName() + " added to cart!");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("\n🛒 Your cart is empty!");
            return;
        }

        double total = 0;
        System.out.println("\n🛒 Items in Cart:");
        for (Product p : items) {
            System.out.println("- " + p.getName() + " : ₹" + p.getPrice());
            total += p.getPrice();
        }
        System.out.println("Total Amount: ₹" + total);
    }

    public double getTotalAmount() {
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }
}

// ===== Main Class =====
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Gender selection
        String[] genders = {"Male", "Female", "Other"};
        System.out.println("=== Select Your Gender ===");
        for (int i = 0; i < genders.length; i++) {
            System.out.println((i + 1) + ". " + genders[i]);
        }
        System.out.print("Enter your choice: ");
        int genderChoice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (genderChoice < 1 || genderChoice > genders.length) {
            System.out.println("Invalid choice! Exiting...");
            return;
        }
        String selectedGender = genders[genderChoice - 1];
        System.out.println("\nYou selected: " + selectedGender);

        // Products
        List<Product> allProducts = Arrays.asList(
            new Product("Men's Shirt", 1200, "Male"),
            new Product("Men's Shoes", 2500, "Male"),
            new Product("Women's Dress", 2000, "Female"),
            new Product("Women's Handbag", 1800, "Female"),
            new Product("Smartwatch", 3500, "Unisex"),
            new Product("Wireless Earbuds", 1500, "Unisex")
        );

        // Show products for the selected gender
        System.out.println("\n=== Products for " + selectedGender + " ===");
        List<Product> availableProducts = new ArrayList<>();
        int index = 1;
        for (Product p : allProducts) {
            if (p.getGenderCategory().equalsIgnoreCase(selectedGender) ||
                p.getGenderCategory().equalsIgnoreCase("Unisex")) {
                System.out.println(index + ". " + p.getName() + " - ₹" + p.getPrice());
                availableProducts.add(p);
                index++;
            }
        }

        // Add products to cart
        Cart myCart = new Cart();
        while (true) {
            System.out.print("\nEnter product number to add to cart (0 to finish): ");
            int choice = sc.nextInt();
            if (choice == 0) break;
            if (choice >= 1 && choice <= availableProducts.size()) {
                myCart.addProduct(availableProducts.get(choice - 1));
            } else {
                System.out.println("❌ Invalid product number!");
            }
        }

        // Show final cart
        myCart.showCart();

        // Delivery option
        if (!myCart.isEmpty()) {
            sc.nextLine(); // consume newline
            System.out.println("\n=== Delivery Details ===");
            System.out.print("Enter your delivery address: ");
            String address = sc.nextLine();

            System.out.println("\n📦 Order Summary:");
            myCart.showCart();
            System.out.println("Delivery Address: " + address);
            System.out.println("✅ Your order will be delivered soon!");
        } else {
            System.out.println("No items in cart. Exiting...");
        }
    }
}
