package classes;

import java.util.Date;
import java.util.HashMap;

public class PremiumAccount extends Account {

    private HashMap<String,Product> hash_Product;



    public PremiumAccount(String id, Customer customer) {
        super(id, customer);
        hash_Product = new HashMap<String,Product>();
    }


   /* public PremiumAccount(String id, String billing_address, Date open, int balanced) {
        super(id,billing_address,open,balanced);
        hash_Product = new HashMap<String,Product>();
    } */

    public void UpdateHashProduct(Product product)
    {
        hash_Product.put(product.getId(),product);
    }

    public void DeleteFromHashProduct(String id)
    {
        hash_Product.remove(id);
    }

    @Override
    public boolean isPremiumAccount() {
        return true;
    }

    public void DeletePremium(){
        //hash_Product.clear();
        for (Product p:hash_Product.values())
           p.setPremiumAccount(null);
        hash_Product=null;
        this.Delete();
    }


    public void printPremium(){ this.print();
        }

    public void printPremiumInfo(){
        this.printinfo();
        System.out.println("Object connecting: ");
        if(!this.hash_Product.isEmpty())
            for(Product p: hash_Product.values()){
                System.out.println(p);
            }

    }

    public void printProduct(){
        for (Product p:hash_Product.values()) {
            p.print();
        }
    }

    @Override
    public String toString() {
        return "PremiumAccount";
    }

    public HashMap<String, Product> getHash_Product() {
        return hash_Product;
    }


}
