/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groceryfile1;

import java.io.Serializable;

/**
 *
 * @author Rajput
 */
public class Product implements Comparable, Serializable
{
    private int id;
    private String name;
    private float price;
    private int stock;

    public Product() {
    }

  

    public Product(int id,String name, float price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
  
    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

  public void displayProduct() {
        System.out.println(id+"\t"+name + " \t " + price +" \t " + stock );
    }
   
    @Override
    public int compareTo(Object o) {
        Product p=(Product)o;
        return (name.compareTo(p.name));
    }
    
    
    }
