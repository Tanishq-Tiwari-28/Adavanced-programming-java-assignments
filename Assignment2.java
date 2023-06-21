import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
class FLIPZON{
    public void Explore_catalog(admin A){
        A.explore(A);
    }
    Scanner sc=new Scanner(System.in);
    public void explore(admin A){
        if(A.getCategories().size()==0){
            System.out.println("No products available");
        }else{
            for(int i=0;i<A.getCategories().size();i++){
                System.out.println("CATEGORY  : "+A.getCategories().get(i).getName());
                for(int j=0;j<A.getCategories().get(i).getProducts().size();j++){
                    System.out.println("    Product->"+A.getCategories().get(i).getProducts().get(j).getName());
                    System.out.println("    Id->"+A.getCategories().get(i).getProducts().get(j).getId());
                    System.out.println("    Price->"+A.getCategories().get(i).getProducts().get(j).getPrice());
                    System.out.println("    Amount in stock->"+A.getCategories().get(i).getProducts().get(j).getStock_quantity());
                    System.out.println("    ");
                }
            }
        }
    }
}
interface Checkout{
    public void checkout(admin A);

}
class customer extends FLIPZON implements Checkout {
    Scanner sc=new Scanner(System.in);
    private ArrayList<customer> signed_in_students = new ArrayList<>();
    private ArrayList<products> cart = new  ArrayList<>();

    private String name;
    private int age;
    private int phone;
    private String email;
    private String password;
    private String status="Normal";
    private double balance=1000;
    private int total_coupons=0;
    private double status_discount;

    public double getStatus_discount() {
        return status_discount;
    }

    public void setStatus_discount(double status_discount) {
        this.status_discount = status_discount;
    }

    public void Status_discount(){
        if(this.getStatus()=="Normal"){
            this.setStatus_discount(0);
        }else if(this.getStatus()=="Prime"){
            this.setStatus_discount(5);
        }else if(this.status=="Elite"){
            this.setStatus_discount(10);
        }
    }

    public int getTotal_coupons() {
        return total_coupons;
    }

    public void setTotal_coupons(int total_coupons) {
        this.total_coupons = total_coupons;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void signup(){
        System.out.println("Enter name");
        String nm= sc.nextLine();
        System.out.println("Enter password");
        String ps=sc.next();
        this.setName(nm);
        this.setPassword(ps);
        signed_in_students.add(this);
        System.out.println(signed_in_students.size());
    }
    public void login(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter name");
        String nm2= sc.nextLine();
        this.setName(nm2);
        System.out.println("Enter password");
        String ps=sc.next();
        this.setPassword(ps);
        if(signed_in_students.size()==0){
            System.out.println("USER NOT REGISTERED......Sign in First");
            Assignment2.num=-1;
        }else{
            for(int i=0;i<this.signed_in_students.size();i++){
                if(nm2.equals(this.signed_in_students.get(i).getName()) && ps.equals(this.signed_in_students.get(i).getPassword())){
                    System.out.println("WELCOME "+this.name+" !!!!");
                    Assignment2.num=1;
                }else{
                    System.out.println("USER NOT REGISTERED......Sign in First");
                    Assignment2.num=-1;
                }
            }
        }

    }
    public void browse_Deals(admin A){
        if(A.getDeals().size()==0){
            System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.");
        }else {
            System.out.println("All available deals are shown below....");
            for(int i=0;i<A.getDeals().size();i++){
                for(int j=0;j<A.getDeals().get(i).getBrowse_deals().size();j++){
                    System.out.println("    Product name-> "+A.getDeals().get(i).getBrowse_deals().get(j).getName());
                    System.out.println("    Product ID-> "+A.getDeals().get(i).getBrowse_deals().get(j).getId());
                    System.out.println("    Product price-> "+A.getDeals().get(i).getBrowse_deals().get(j).getPrice());
                    System.out.println("    Product description-> "+A.getDeals().get(i).getBrowse_deals().get(j).getDescription());
                    System.out.println("    Product amount in stock-> "+A.getDeals().get(i).getBrowse_deals().get(j).getStock_quantity());
                    System.out.println("  ");
                }



            }
        }
    }
    public void upgrade_status(){
        System.out.println("Current status is: "+this.getStatus());
        if(this.getStatus()=="Normal"){
            System.out.println("""
            CHOOSE NEW STATUS...
            1.Prime
            2.Elite""");
            int s=sc.nextInt();
            if(s==1){
                this.status="Prime";
                this.balance=balance-200;
            }else if(s==2){
                this.status="Elite";
                this.balance=balance-300;
            }
            System.out.println("Status upgrade to prime....");
        }else if(this.getStatus()=="Prime"){
            System.out.println("Upgrade to elite?? click 1 for yes and 2 for no");
            int s=sc.nextInt();
            if(s==1){
                this.status="Elite";
                this.balance=balance-300;
                System.out.println("Status upgrade to Elite....");
            }else if(s==2){
                System.out.println("Current status is: "+this.getStatus());
            }
        } else if(this.getStatus()=="Elite") {
            System.out.println("Already at highest status...");
        }

    }


    public void add_amount(){
        System.out.println("Enter the amount to add in wallet");
        double am=sc.nextDouble();
        this.balance=balance+am;
    }
    public void add_to_cart_deals(){

    }

    public void add_to_cart(admin A){
        System.out.println("Enter product ID");
        String id=sc.next();
        System.out.println("Enter the quantity:");
        int q=sc.nextInt();
        for(int i=0;i<A.getCategories().size();i++){
            for(int j=0;j<A.getCategories().get(i).getProducts().size();j++){
                if(Objects.equals(id, A.getCategories().get(i).getProducts().get(j).getId())){
                    if(A.getCategories().get(i).getProducts().get(j).getStock_quantity()<q){
                        System.out.println("Product is Out of stock");
                    }else{
                        A.getCategories().get(i).getProducts().get(j).setTot_amount(q);
                        cart.add(A.getCategories().get(i).getProducts().get(j));
                        A.getCategories().get(i).getProducts().get(j).change_stock_quantity(-q);
                        System.out.println("Product is added to cart");
                    }
                }else{
                    System.out.println("Enter valid product ID...");
                }
            }
        }

    }
    public void empty_cart(admin A){
        if(cart.size()==0){
            System.out.println("Cart is already empty");
        }else{
            for(int i=0;i<A.getCategories().size();i++){
                for(int j=0;j<A.getCategories().get(i).getProducts().size();j++){
                    A.getCategories().get(i).getProducts().get(j).change_stock_quantity(cart.get(i).getTot_amount());
                }
            }
            cart.clear();
            System.out.println("Cart is being emptied.....");

        }
    }
    public void view_cart(){
        if(cart.size()==0){
            System.out.println("Cart is empty...");
        }else{
            for(int i=0;i<cart.size();i++){
                System.out.println("Cart details.....");
                System.out.println("    Product name-> "+cart.get(i).getName());
                System.out.println("    Product Id-> "+cart.get(i).getId());
                System.out.println("    Product description-> "+cart.get(i).getDescription());
                System.out.println("    Product amount-> "+cart.get(i).getTot_amount());
                System.out.println("    Product price-> "+cart.get(i).getPrice());
            }
        }

    }


    @Override
    public void checkout(admin A) {
        Random R =new Random();
        double total_price=0;
        if(cart.size()==0){
            System.out.println("cart is empty");
        }else{
            if(Objects.equals(this.getStatus(), "Normal")){
                for( int i=0;i< cart.size();i++){
                    total_price=(total_price+(cart.get(i).getPrice()-(cart.get(i).getPrice())*(cart.get(i).getDiscount()/100)))*cart.get(i).getTot_amount();
                }
                double de=100+((total_price*5)/100);
                if(this.getBalance()<(total_price+de)){
                    System.out.println("BALANCE insufficient");
                }else{
                    int j=0;
                    System.out.println("Proceeding to checkout. Details:");
                    this.setBalance(this.getBalance()-(total_price+de));

                    while(j<cart.size()){
                        this.setTotal_coupons(0);
                        System.out.println("Product name: "+cart.get(j).getName());
                        System.out.println("Product Id: "+cart.get(j).getId());
                        System.out.println("Product description: "+cart.get(j).getDescription());
                        System.out.println("Delivery charge: 100 + 5%of order value -> " + de);
                        System.out.println("Final Discount: "+0);
                        System.out.println("Total coupons: " + this.getTotal_coupons());
                        System.out.println("Current Balance: "+this.getBalance());
                        j++;
                    }


                    System.out.println("Order Placed Successfully.....It will be delivered in 7-8 days.");
                    cart.clear();



                }


            }else if(this.getStatus()=="Prime"){
                if(this.getTotal_coupons()==0){
                    int k=0;
                        double tot_dis=0;
                        while(k<cart.size()){
                            tot_dis=Math.max(cart.get(k).getDiscount(),this.getStatus_discount());
                            total_price=total_price+(cart.get(k).getPrice()-(cart.get(k).getPrice()*tot_dis)/100)*cart.get(k).getTot_amount();
                        }
                        double de=100+((total_price*2)/100);
                        if(this.getBalance()<total_price+de){
                            System.out.println("Balance insufficient");
                        }else{
                            System.out.println("Proceeding to checkout. Details:");
                            this.setBalance(this.getBalance()-(total_price+de));
                            while(k<cart.size()){
                                System.out.println("Product name: "+cart.get(k).getName());
                                System.out.println("Product Id: "+cart.get(k).getId());
                                System.out.println("Product description: "+cart.get(k).getDescription());
                                System.out.println("Delivery charge: "+ de);
                                System.out.println("Final Discount: "+tot_dis);
                                System.out.println("Current Balance: "+this.getBalance());
                                k++;
                            }

                            int tot_cop= (int)(Math.random()*2)+1;
                            this.setTotal_coupons(tot_cop);
                            System.out.println("Order Placed Successfully.....It will be delivered in 3-6 days.");
                            System.out.println("Congratulations you have won "+this.getTotal_coupons()+" Coupons.....");
                            cart.clear();

                    }

                }else if(this.getTotal_coupons()!=0){
                    int l=0;
                    int[] sep_dis=new int[this.getTotal_coupons()];
                    for (int j=0;j<this.getTotal_coupons();j++){
                        int dis=R.nextInt(5)+5;
                        sep_dis[j]=dis;
                    }
                    int sep1=Math.max(sep_dis[0],sep_dis[1]);
                    double tot_dis=Math.max(cart.get(l).getDiscount(),this.getStatus_discount());
                    double fin_dis=Math.max(tot_dis,sep1);
                    while(l< cart.size()){
                        total_price=total_price+(cart.get(l).getPrice()-(cart.get(l).getPrice()*fin_dis)/100)*cart.get(l).getTot_amount();
                    }
                    System.out.println("Proceeding to checkout. Details:");
                    while(l<cart.size()){
                        System.out.println("Product name: "+cart.get(l).getName());
                        System.out.println("Product Id: "+cart.get(l).getId());
                        System.out.println("Product description: "+cart.get(l).getDescription());
                        System.out.println("Delivery charge: "+ 100+(2*total_price)/100);
                        System.out.println("Final Discount: "+fin_dis);
                        System.out.println("Current Balance: "+(this.balance-total_price));
                        l++;
                    }
                    cart.clear();
                    System.out.println("Order Placed Successfully.....It will be delivered in 3-6 days.");
                }



            }else if(this.getStatus()=="Elite"){
                if(this.getTotal_coupons()==0){
                    int k=0;
                    double tot_dis=0;
                    while(k<cart.size()){
                        tot_dis=Math.max(cart.get(k).getDiscount(),this.getStatus_discount());
                        total_price=total_price+(cart.get(k).getPrice()-(cart.get(k).getPrice()*tot_dis)/100)*cart.get(k).getTot_amount();
                    }
                    double de=100;
                    if(this.getBalance()<total_price+de){
                        System.out.println("Balance insufficient");
                    }else{
                        System.out.println("Proceeding to checkout. Details:");
                        this.setBalance(this.getBalance()-(total_price+de));
                        while(k<cart.size()){
                            System.out.println("Product name: "+cart.get(k).getName());
                            System.out.println("Product Id: "+cart.get(k).getId());
                            System.out.println("Product description: "+cart.get(k).getDescription());
                            System.out.println("Delivery charge: "+ de);
                            System.out.println("Final Discount: "+tot_dis);
                            System.out.println("Current Balance: "+this.getBalance());

                        }
                        int tot_cop= R.nextInt(2)+3;
                        this.setTotal_coupons(tot_cop);
                        System.out.println("Order Placed Successfully.....It will be delivered in 3-6 days.");
                        System.out.println("Congratulations you have won "+this.getTotal_coupons()+" Coupons.....");
                        cart.clear();

                    }
                }else if(this.getTotal_coupons()!=0){
                    int l=0;
                    double tot_dis=Math.max(cart.get(l).getDiscount(),5);
                    int[] sep_dis=new int[4];
                    for (int j=0;j<this.getTotal_coupons();j++){
                        int dis=R.nextInt(5)+5;
                        sep_dis[l]=dis;
                    }
                    int sep1=Math.max(sep_dis[0],sep_dis[1]);
                    int sep2=Math.max(sep1,sep_dis[2]);
                    int sep3=Math.max(sep2,sep_dis[3]);
                    double fin_dis=Math.max(tot_dis,sep3);
                    System.out.println("Proceeding to checkout. Details:");
                    while(l<cart.size()){
                        System.out.println("Product name: "+cart.get(l).getName());
                        System.out.println("Product Id: "+cart.get(l).getId());
                        System.out.println("Product description: "+cart.get(l).getDescription());
                        System.out.println("Delivery charge: "+ 100);
                        System.out.println("Final Discount: "+fin_dis);
                        System.out.println("Current Balance: "+(this.balance-total_price));
                    }
                    System.out.println("Order Placed Successfully.....It will be delivered in 1-2 days.");
                }

            }


        }
    }
}

class categories{
    String name;
    String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private  ArrayList<products> products = new ArrayList<>();

    public ArrayList<products> getProducts() {
        return this.products;
    }

    public void add_product(products p){
        this.products.add(p);
    }

    public void setProducts(ArrayList<products> products) {
        this.products = products;
    }
    public int products_size(){
        return products.size();
    }
}

class products{
    private String name;
    private String id;
    private double price;
    String Description;
    private double discount=0;

    private int original_price;
    private int updated_price;
    private double total_cart_price;
    private double final_price;
    private int tot_amount=0;
    private int stock_quantity=0;

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public int getTot_amount() {
        return tot_amount;
    }

    public void setTot_amount(int tot_amount) {
        this.tot_amount = tot_amount;
    }


    public double getTotal_cart_price() {
        return total_cart_price;
    }

    public void setTotal_cart_price(double total_cart_price) {
        this.total_cart_price = total_cart_price;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public int getUpdated_price() {
        return updated_price;
    }

    public void setUpdated_price(int updated_price) {
        this.updated_price = updated_price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price =  price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void change_stock_quantity( int p){
        this.stock_quantity += p;
    }

}
class admin extends FLIPZON {

    Scanner sc=new Scanner(System.in);
    private String name;
    private int roll_number;
    double effective_price;


    public double getEffective_price() {
        return effective_price;
    }

    public void setEffective_price(double effective_price) {
        this.effective_price = effective_price;
    }


    private  ArrayList<categories> categories = new ArrayList<>();

    public ArrayList<categories> getCategories() {
        return this.categories;
    }

    public void setCategories(ArrayList<categories> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(int roll_number) {
        this.roll_number = roll_number;
    }
    private ArrayList<deals> deals =new ArrayList<>();

    public ArrayList<deals> getDeals() {
        return deals;
    }

    public void setDeals(ArrayList<deals> deals) {
        this.deals = deals;
    }

    public void add_category(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter name of category...");
        String st=sc.nextLine();
        System.out.println("Enter Id for category");
        String id=sc.next();
        if(this.categories.size()==0){
            categories C= new categories();
            products P= new products();
            C.setName(st);
            C.setId(id);
            this.categories.add(C);
            System.out.println("Enter name of Product...");
            sc.nextLine();
            String st1=sc.nextLine();
            System.out.println("Enter Id for Product");
            String id1=sc.next();
            System.out.println("Enter the Description of product....");
            //Add description code here...
            System.out.println("Enter price of product");
            int pr=sc.nextInt();
            System.out.println("Enter the amount to add in stock.....");
            int s=sc.nextInt();
            P.setName(st1);
            P.setId(id1);
            P.setPrice(pr);
            P.setStock_quantity(s);
            this.categories.get(0).add_product(P);
            System.out.println("Product added....");
        }else{
            for(int i=0;i<this.categories.size();i++){
                if(this.categories.get(i).getId().equals(id)){
                    System.out.println("Dear Admin, the category ID is already used!!! Please set a different and a unique category ID");
                }else{
                    categories C= new categories();
                    products P= new products();
                    C.setName(st);
                    C.setId(id);
                    this.categories.add(C);
                    System.out.println("Enter name of Product...");
                    sc.nextLine();
                    String st3=sc.nextLine();
                    System.out.println("Enter Id for Product");
                    String id2=sc.next();
                    System.out.println("Enter the Description of product....");
                    //Add description code here...
                    System.out.println("Enter price of product");
                    int pr=sc.nextInt();
                    System.out.println("Enter the amount to add in stock.....");
                    int s=sc.nextInt();
                    P.setName(st3);
                    P.setId(id2);
                    P.setPrice(pr);
                    P.setStock_quantity(s);
                    this.categories.get(this.categories.size()-1).add_product(P);
                    break;
                }
            }
        }
    }
    public void add_product(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter id of category to add product in it......");
        String st2=sc.next();
        if(this.getCategories().size()>0){
            int i=0;
            while(i<this.getCategories().size()){
                if(Objects.equals(this.categories.get(i).getId(), st2)){
                    System.out.println("Enter name of Product...");
                    sc.nextLine();
                    String st3=sc.nextLine();
                    System.out.println("Enter Id for Product");
                    String id=sc.next();
                    System.out.println("Enter the Description of product....");
                    //Add description code here...
                    System.out.println("Enter price of product");
                    int pr=sc.nextInt();
                    System.out.println("Enter the amount to add in stock.....");
                    int s=sc.nextInt();
                    products P= new products();
                    P.setName(st3);
                    P.setId(id);
                    P.setPrice(pr);
                    P.setStock_quantity(s);
                    this.categories.get(i).add_product(P);
                    break;
                }else{
                    //System.out.println("No such category exists 1");//DEBUGING REQUIRED
                    i++;
                }
            }

        }else{
            System.out.println("No such category exists");

        }

    }
    public void delete_category(){
        System.out.println("Enter Id of category...");
        String st=sc.next();
        if(categories.size()==0){
            System.out.println("No categories added till now........");
        }else{
            for(int i=0;i<categories.size();i++) {
                if (Objects.equals(categories.get(i).getId(), st)) {
                    categories.remove(i);
                    System.out.println("Category deleted.....");
                }else{
                    System.out.println("No such category exists");
                }
            }
        }
    }

    public void delete_product(){
        if(this.categories.size()==0){
            System.out.println("no categories till now...");
        }else{
            System.out.println("Enter id of category.....");
            String st=sc.next();
            sc.nextLine();
            int i=0;
            while(i<this.categories.size()){
                System.out.println("Enter the id of product to delete");
                String st2=sc.nextLine();
                int j=0;
                while(j<this.getCategories().get(i).getProducts().size()){
                    if(Objects.equals(this.getCategories().get(i).getProducts().get(j).getId(), st2)){
                        this.getCategories().get(i).getProducts().remove(j);
                        if(this.categories.size()==0){
                            System.out.println("Category is empty....Enter your choice");
                            System.out.println("""
                                            1.add product
                                            2.Delete category
                                            """);
                            int ch=sc.nextInt();
                            if(ch==1){
                                products P= new products();
                                System.out.println("Enter name of Product...");
                                sc.nextLine();
                                String st1=sc.nextLine();
                                System.out.println("Enter Id for Product");
                                String id1=sc.next();
                                System.out.println("Enter the Description of product....");
                                //Add description code here...
                                System.out.println("Enter price of product");
                                int pr=sc.nextInt();
                                P.setName(st1);
                                P.setId(id1);
                                P.setPrice(pr);
                                this.getCategories().get(i).getProducts().add(P);
                            } else if(ch==2) {
                                categories.remove(i);
                                System.out.println("Category deleted.....");
                            }
                        }else{
                            System.out.println("Product deleted.....");
                        }
                    }else{
                        j++;
                    }
                }
                i++;
            }
        }

    }


    public void set_discount(){
        System.out.println("Dear Admin give the Product ID you want to add discount for");
        System.out.println("Enter product id");
        String st=sc.next();
        if(this.getCategories().size()==0){
            System.out.println("Add categories first");
        }else{
            for(int i=0;i<this.getCategories().size();i++){
                if(this.getCategories().get(i).getProducts().size()==0){
                    System.out.println("Add products first");
                }else{
                    for(int j=0;j<this.getCategories().get(i).getProducts().size();j++){
                        if(Objects.equals(this.getCategories().get(i).getProducts().get(j).getId(), st)){
                            System.out.println("Enter discount for Elite Customers");
                            double di=sc.nextDouble();
                            this.getCategories().get(i).getProducts().get(j).setDiscount(di);
                            System.out.println("Enter discount for Prime Customers");
                            double di2=sc.nextDouble();
                            this.getCategories().get(i).getProducts().get(j).setDiscount(di2);
                            System.out.println("Enter discount for Normal Customers");
                            double di3=sc.nextDouble();
                            this.getCategories().get(i).getProducts().get(j).setDiscount(di3);
                        }else{
                            System.out.println("No such product exists....");
                        }
                    }
                }


            }
        }

    }



}
class deals{
    private ArrayList<products> browse_deals=new ArrayList<>();

    public ArrayList<products> getBrowse_deals() {
        return browse_deals;
    }

    public void setBrowse_deals(ArrayList<products> browse_deals) {
        this.browse_deals = browse_deals;
    }

    public void give_away(admin A){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter ID for product 1");
        String id=sc.next();
        if(A.getCategories().size()==0){
            System.out.println("Add categories first..");
        }else{
            for(int i = 0; i< A.getCategories().size(); i++){
                for(int j=0;j<A.getCategories().get(i).getProducts().size();j++){
                    if(Objects.equals(A.getCategories().get(i).getProducts().get(j).getId(), id)){
                        this.getBrowse_deals().add(A.getCategories().get(i).getProducts().get(j));
                        System.out.println("Enter ID for product 2");
                        String id2=sc.next();
                        for(int k = 0; k< A.getCategories().size(); k++){
                            for(int l=0;l<A.getCategories().get(k).getProducts().size();l++){
                                if(Objects.equals(A.getCategories().get(k).getProducts().get(l).getId(), id2)){
                                    this.getBrowse_deals().add(A.getCategories().get(k).getProducts().get(l));
                                    if(Objects.equals(id, id2)){
                                        System.out.println("Both ids are same, give different IDs");
                                    }else{
                                        System.out.println("Enter the combined price for NORMAL users");
                                        double cm=sc.nextDouble();

                                        System.out.println("Enter the combined price for Prime users");
                                        double cm1=sc.nextDouble();

                                        System.out.println("Enter the combined price for Elite users");
                                        double cm2=sc.nextDouble();
                                    }
                                }else{
                                    System.out.println("Enter valid ID1...");
                                }
                            }
                        }
                    }else{
                        System.out.println("Enter valid ID2... ");
                    }
                }

            }
        }





    }

}

public class Assignment2 {
    public static int num=0;
    public static void menu1(){
        System.out.println("""
                1.Enter as Admin
                2.Explore the product catalog
                3.Show available deals
                4.Enter as customer
                5.Exit the application""");
    }
    public static void menu2(){
        System.out.println("""
                Please choose any one of the following actions.
                1.Add category
                2.Delete category
                3.Add product
                4.Delete product
                5.Set discount on product
                6.Add giveaway deals
                7.Back""");
    }
    public static void menu3(){
        System.out.println("""
                1.signup
                2.login
                3.back""");
    }
    public static void menu4(){
        System.out.println("""
                1) browse products
                2) browse deals
                3) add a product to cart
                4) add products in deal to cart
                5) view coupons
                6) check account balance
                7) view cart
                8) empty cart
                9) checkout cart
                10) upgrade customer status
                11) Add amount to wallet
                12) back""");
    }

    public static void main(String[] args) {
        deals D =new deals();
        customer C =new customer();
        categories Ca=new categories();
        FLIPZON F=new FLIPZON();
        admin A1=new admin();
        A1.setName("Beff");
        A1.setRoll_number(1);
        Scanner sc=new Scanner(System.in);
        System.out.println("WELCOME TO FLIPZON....");
        menu1();
        int a=sc.nextInt();
        while(true){
            if(a==1){
                System.out.println("Enter Name......");
                String nm = sc.next();
                System.out.println("Enter Roll number......");
                int rl = sc.nextInt();
                while(true){
                    if(Objects.equals(nm, A1.getName()) && rl==A1.getRoll_number()){
                        System.out.println("WELCOME " + nm + "!!!!");
                        menu2();
                        int b=sc.nextInt();
                        if(b==1){
                            A1.add_category();
                        }else if(b==2){
                            A1.delete_category();
                        }else if(b==3){
                            A1.add_product();
                        }else if(b==4){
                            A1.delete_product();
                        }else if(b==5){
                            A1.set_discount();
                        }else if(b==6){
                            D.give_away(A1);
                        }else if(b==7) {
                            menu1();
                            a=sc.nextInt();
                            break;
                        }
                    }else{
                        System.out.println("PLEASE ENTER VALID INFO.....");
                        break;
                    }


                }

            }else if(a==2){
                F.Explore_catalog(A1);
                menu1();
                a=sc.nextInt();
            } else if (a==3) {
                C.browse_Deals(A1);
                menu1();
                a=sc.nextInt();
            } else if (a==4) {
                menu3();
                int c=sc.nextInt();
                while(true){
                    if(c==1){
                        C.signup();
                        System.out.println("You have successfully registered on FLIPZON....");
                        break;
                    }else if(c==2){
                        C.login();
                        if (num == -1) {
                            menu3();
                            c=sc.nextInt();
                        }else if(num==1){
                            menu4();
                            int d=sc.nextInt();
                            while(true){
                                if(d==1){
                                    System.out.println("Here is the list of all products");
                                    A1.Explore_catalog(A1);
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==2){
                                    System.out.println("All available deals are shown below");
                                    C.browse_Deals(A1);
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==3){
                                    C.add_to_cart(A1);
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==4){
                                    C.add_to_cart_deals();
                                    System.out.println("Product is added to cart");
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==5){
                                    System.out.println("Coupons");
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==6){
                                    System.out.println("Your Account balance is: "+C.getBalance());
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==7){
                                    C.view_cart();
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==8){
                                    C.empty_cart(A1);
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==9){
                                    C.checkout(A1);
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==10){
                                    C.upgrade_status();
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==11){
                                    C.add_amount();
                                    menu4();
                                    d=sc.nextInt();
                                }else if(d==12){
                                    menu3();
                                    c=sc.nextInt();
                                    break;
                                }
                            }
                        }

                    }else if(c==3){
                        menu1();
                        a=sc.nextInt();
                        break;
                    }
                }
            }else if(a==5) {
                System.out.println("exit");
                break;
            }
        }
    }
}


