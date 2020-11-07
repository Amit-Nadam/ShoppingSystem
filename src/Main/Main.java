package Main;
import classes.*;
import enums.UseState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static enums.UseState.Active;

public class Main {

    public static void main(String[] args) {
        //when the user log in we add him to hashmap "connected" by the login_id
        HashMap<String, WebUser> users= new HashMap<String, WebUser>();//all the users in the system
        HashMap<String,Product> AllProducts= new HashMap<String, Product>();
        HashMap<String,Object> AllObjects= new HashMap<String, Object>();
        HashMap<String, ArrayList<String>> IdList= new HashMap<String, ArrayList<String>>();
        Account connected;

        //////////Already exist in the System (נתון לנו)/////////////
        Supplier moshe=new Supplier("123","Moshe");
        Product bamba=new Product("Bamba", "Bamba",4,moshe);
        Product raman=new Product("Raman", "Raman", 50, moshe);
        WebUser webUser=new WebUser("Dani");
        webUser.setPassword("Dani123");
        Account account=new Account();
       //need to finish that!!!???!!



        while (true){
            System.out.println("1: Add WebUser");
            System.out.println("2: Remove WebUser ");
            System.out.println("3: Log In");
            System.out.println("4: Add Product ");
            System.out.println("5: Delete Product");
            System.out.println("6: ShowAllObjects");
            System.out.println("7: ShowObjectId ");

            Scanner scanner=new Scanner(System.in);
            int num=scanner.nextInt();
            String num2=scanner.next();
            String login;
            String password;
            Order last_order=null;

            switch (num) {
                case 1://Add WebUser


                case 2://Remove WebUser
                    System.out.println("Please enter a login id for remove");
                    num2 = scanner.next();
                    WebUser forRemove = users.get(num2);
                    ArrayList<String> templist = IdList.get(num2);
                    for (String s : templist) {
                        if (AllObjects.containsKey(s))
                            AllObjects.remove(s);
                    }
                    IdList.remove(num2);
                    AllObjects.remove(num2);
                    forRemove.Delete();
                    users.remove(num2);

                case 3://Log In
                    boolean out = true;
                    System.out.println("Please enter a login id");
                    login = scanner.next();
                    if (users.containsKey(login)) {
                        System.out.println("Please enter a Password");
                        password = scanner.next();
                        if (users.get(login).getPassword() == password) {
                            account = users.get(login).getCustomer().getAccount();//This is
                            while (out == true) {
                                users.get(login).setState(Active);
                                System.out.println("1: Make order");
                                System.out.println("2: Display order");
                                System.out.println("3: Add a Link Product");
                                System.out.println("4: Logout");
                                int userchoos = scanner.nextInt();
                                switch (userchoos) {
                                    case 1://Make order
                                        System.out.println("Please enter id of the seller");
                                        num2 = scanner.next();
                                        WebUser seller = users.get(num2);
                                        if (seller.getCustomer().getAccount().isPremiumAccount()) {
                                            boolean buy = true;
                                            while (buy == true) {
                                                seller.getCustomer().getAccount().printorders();
                                                System.out.println("Please enter id of the product you want");
                                                String prod = scanner.next();
                                                System.out.println("Please enter quantity");
                                                String quan = scanner.next();
                                                /*
                                                need to remove from seller?
                                                need to put in buyer?
                                                 */

                                                last_order = seller.getCustomer().getAccount().getorder(prod);
                                                System.out.println("Do you want to continue buying?(yes/no");
                                                String stay = scanner.next();
                                                if (stay == "no")
                                                    buy = false;
                                            }
                                        } else {
                                            System.out.println("This is not a premium account");
                                        }


                                        break;


                                    case 2://Display order
                                        last_order.printinfo();
                                        break;

                                    case 3://Link Product

                                    case 4://LogOut
                                        System.out.println("Please enter a login id");
                                        String id_log_out = scanner.next();
                                        WebUser log_out = users.get(id_log_out);
                                        log_out.setState(UseState.Blocked);
                                        out = false;
                                        break;
                                }
                            }

                        }

                    }


                case 4:// Add Product
                    System.out.println("Enter the Product Id");
                    String product_id = scanner.next();

                    System.out.println("Enter the Name of Product");
                    String product_name = scanner.next();

                    System.out.println("Enter the price of Product ");
                    String product_price = scanner.next();

                    System.out.println("Enter the Supplier Id");
                    String supplier_id = scanner.next();

                    System.out.println("Enter the Name of Supplier ");
                    String supplier_name = scanner.next();

                    Supplier new_supplier = new Supplier(supplier_id, supplier_name);
                    Product new_product = new Product(product_id, product_name, Integer.parseInt(product_price), new_supplier);

                    AllProducts.put(product_id, new_product);
                    AllObjects.put(product_id, new_product);
                    AllObjects.put(supplier_id, new_supplier);
                    ArrayList<String> s = new ArrayList<String>();
                    s.add(supplier_id);
                    IdList.put(product_id, s);
                    break;


                case 5://Delete Product
                    System.out.println("Please enter a product id tp delete");
                    num2 = scanner.next();
                    Product product = AllProducts.get(num2);
                    ArrayList<String> tempproduct = IdList.get(num2);
                    for (String a : tempproduct) {
                        if (AllObjects.containsKey(a))
                            AllObjects.remove(a);
                    }
                    IdList.remove(num2);
                    AllObjects.remove(num2);
                    product.Delete();
                    AllProducts.remove(num2);
                    break;


                case 6://ShowAllObjects
                    for (String idO : AllObjects.keySet()) {
                        Object Ob = AllObjects.get(idO);
                        System.out.println("Object's id:" + idO + ", object's name: " + Ob.toString());
                    }
                    for (String Id : users.keySet()) {
                        WebUser user = users.get(Id);
                        System.out.println("Object's id:" + Id + ", object's name: " + user.toString());
                    }
                    for (String idP : AllProducts.keySet()) {
                        Product proud = AllProducts.get(idP);
                        System.out.println("Object's id:" + idP + ", object's name: " + proud.toString());
                    }


                case 7://ShowObjectById
                    System.out.println("Enter the Object Id");
                    String object_id = scanner.next();
                    Object ob = AllObjects.get(object_id);
                    if (ob instanceof PremiumAccount) {
                        PremiumAccount temp = (PremiumAccount) ob;
                        temp.printinfo();
                    } else if (ob instanceof Account) {
                        Account temp = (Account) ob;
                        temp.printinfo();
                    } else if (ob instanceof Customer) {
                        Customer temp = (Customer) ob;
                        temp.printinfo();
                    } else if (ob instanceof Payment) {
                        Payment temp = (Payment) ob;
                        temp.printinfo();
                    } else if (ob instanceof LineItem) {
                        LineItem temp = (LineItem) ob;
                        temp.printinfo();
                    } else if (ob instanceof Order) {
                        Order temp = (Order) ob;
                        temp.printinfo();
                    } else if (ob instanceof Product) {
                        Product temp = (Product) ob;
                        temp.printinfo();
                    } else if (ob instanceof ShoppingCart) {
                        ShoppingCart temp = (ShoppingCart) ob;
                        temp.printinfo();
                    } else if (ob instanceof Supplier) {
                        Supplier temp = (Supplier) ob;
                        temp.printinfo();
                    } else if (ob instanceof WebUser) {
                        WebUser temp = (WebUser) ob;
                        temp.printinfo();
                    }

            }

        }



    }
}
