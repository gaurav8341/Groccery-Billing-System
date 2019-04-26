package groceryfile1;

import java.util.ArrayList;

public class Bill implements Comparable{
    //private int id;
    private String customer;
    private String mobno;
    private float total=0;
    private ArrayList<Product> ingList;//ingredients list in this case stock is amt of product buyed and price is total price 

    public ArrayList<Product> getIngList() {
        return ingList;
    }

    public void setIngList(ArrayList<Product> ingList) {
        this.ingList = ingList;
    }

    //  private int i;
    public Bill() {
    }

    public Bill(String customer, String mobno, ArrayList<Product> ingList) {
        this.customer = customer;
        this.mobno = mobno;
        this.ingList = ingList;
        for(int i=0;i<this.ingList.size();i++){
            total+=this.ingList.get(i).getPrice();
        }
    }
  
    public String getCustomer() {
        return customer;
    }

    public String getMobno() {
        return mobno;
    }

    public float getTotal() {
        return total;
    }
    
    public void addtopurchaseDetails(String name,int quantity){
                   ProductDB pd=new ProductDB();
                   int k=pd.search(name);
                   Product p;
                   if(k!=-1)
                   {
                     p=pd.getProduct(k);//
                     p.setStock(quantity);
                     p.setPrice(pd.getProduct(k).getPrice()*quantity);
                     total+=p.getPrice();
                     ingList.add(p);
                   }
                   else
                       System.out.println("we dont have "+ name +" right now.....pls come again later for that  ");
    }
    
    void removeProduct(int index){
        ingList.remove(index);
    }
    
    void showbill() {
         ProductDB pd=new ProductDB();
        System.out.println("customer  "+customer+"  mobile no  "+mobno);
        System.out.println();
        
         System.out.println("name"+"\t"+"quantity"+"\t"+"cost of product");
        for(int k=0;k<ingList.size();k++)
        {
            //System.out.println(pd.getProduct(purchaseDetails[k][0]).getName()+"\t"+purchaseDetails[k][1]+"\t"+pd.getProduct(purchaseDetails[k][0]).getPrice()*purchaseDetails[k][1]);
            System.out.println(ingList.get(k).getName()+"\t"+ingList.get(k).getStock()+"\t"+ingList.get(k).getPrice());
        }
        System.out.println("total cost = "+total);
    }

    @Override
    public int compareTo(Object o) {
        Bill b=(Bill)o;
        return customer.compareTo(b.getCustomer());
    }

}
