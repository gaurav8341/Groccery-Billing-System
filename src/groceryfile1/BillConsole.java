package groceryfile1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BillConsole {
    private BillDB bd=new BillDB();
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public BillConsole() {
       
    }
    
     int menu(){   int choice=0;
            System.out.println("1--->ShopKeeper Login");
            System.out.println("2--->Salesman Login");
            System.out.println("3--->Exit the program");
            System.out.println("Enter your choice");
            
            try {
            choice=Integer.parseInt( br.readLine());
        } catch (IOException | NumberFormatException e) {
        }
            return choice;
    }//menu
     
        public void start(){
        while (true) {
        
            int choice=menu();
         switch (choice) {
                case 1: // Shopkeeper Login
                        shopkeeper();
                        break;
                case 2: // Salesman Login
                        salesman();
                        break;
                case 3:System.out.println("End of program");
                        System.exit(0);
                
                 break;
            
                 default: System.out.println("You entered wrong choice");
                    }
        }
    }//start
 
        public void salesman(){    ///modifybill is remaining
    while (true) {
        
        int choice=menu_salesman();
        switch (choice) {
            case 1: // Bill a customer
                        billing();
                
                break;
            case 2: // show all bills
                        displayBills();
                        break;
            case 3:
                        searchBill();
                        break;
            /*case 4: modifyBill();
                        break;*/                
            case 5: removeBill();                
            case 6: return ;
            default: System.out.println("You entered wrong choice");
                }
    }
}//salesman
        
        public void removeBill(){
            int index=-1;
            System.out.println("enter the index of the bill to be remoed ");
            try {
                index=Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException e) {
            }
            if (index!=-1&&index<bd.getSize()) {
                 bd.removeBill(index);
                 System.out.println("Bill had been removed successfully ");
            } else {
                 System.out.println("index is invalid........ ");
            }
           
        }
      
        public void searchBill(){
            String customer=null;
            try {
                System.out.println("enter the name of the customer ");
                customer=br.readLine();
            } catch (IOException e) {
            }
            int position=bd.search(customer);
            if(position!=-1){
                System.out.println("Following Bill is at position "+position);
            }
            else
                 System.out.println("Following Bill is not present");
        }
        
        int menu_shopkeeper(){   int choice=0;
        System.out.println("1--->Add a product");
            System.out.println("2--->Display all products");
            System.out.println("3--->Search a product");
            System.out.println("4--->Modify a product");
            System.out.println("5--->Remove a product");
            System.out.println("6--->Back to main menu");
            System.out.println("Enter your choice");
            
            try {
            choice=Integer.parseInt( br.readLine());
        } catch (IOException | NumberFormatException e) {
        }
            return choice;
    }//menu_shopkeeper
        
        public void shopkeeper(){
    while (true) {
        
        int choice=menu_shopkeeper();
        switch (choice) {
            
            case 1: addProduct();
                        
                
                break;
            case 2: displayAllProducts();
                        
                
                break;
            case 3: searchProduct();
                        break;
           /* case 4: modifyProduct();
                        break;       */         
            case 5: removeProduct();
                        break;                                
            case 6:return ;
            default: System.out.println("You entered wrong choice");
                }
    }
}//shopkeeper
        
        public void addProduct(){
            String name = null;
            int stock = 0;
            float price = 0;
            int id=0;
            System.out.println("enter the info of product (id,name,stock,price)");
            ProductDB pd=new ProductDB();
            try {
                id=Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException e) {
            }
            try {
                name=br.readLine();
            }catch(IOException e){
                System.out.println(e);
            }
            try {
                    stock=Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException x) {
                                System.out.println(x);
            }
            try {
                    price=Float.parseFloat(br.readLine());
            } catch (IOException | NumberFormatException q) {
                                System.out.println(q);
            }
            Product p=new Product(id,name,price,stock);
            pd.addProduct(p);
        }
        
        void billing(){
          String customer=null;
          String mobid=null;
          ProductDB pd=new ProductDB();
          Bill b;
          ArrayList<Product> PList=new  ArrayList<>();
          Product record;
          int ch=-1;
          try {
              System.out.println("enter the name of the customer ");
              customer=br.readLine();
              System.out.println("enter the mobile no of the customer ");
              mobid=br.readLine();
          } catch (IOException e) {
          }//only to get info about customer
         
          do{
                System.out.println("choose the product you want(-1 to exit)");
                displayAllProducts();
                try {
                  ch=Integer.parseInt(br.readLine());
              } catch (IOException | NumberFormatException e) {
              }//to get the id of product 
                if(ch<pd.getProductList().size() && ch>=0)
                {
                    int stock=0;
                    System.out.println("enter quantity of product ");
                    try {
                        stock=Integer.parseInt(br.readLine());
                    } catch (IOException | NumberFormatException e) {
                    }//toget stock of product
                    if(stock>0 && stock<=pd.getProduct(ch).getStock()){
                            record=new Product(PList.size()+1,pd.getProduct(ch).getName(),(pd.getProduct(ch).getPrice()*stock),stock);
                            PList.add(record);
                        }
                    else{
                           System.out.println("wrong choice or stock is inefficient ");
                        }
                 }
                
          }while(ch!=-1);
            b=new Bill(customer,mobid,PList);
           bd.addBill(b);
      }
      
      void displayBills(){
          if(bd.getBillList().size()>=0){
             for(int i=0;i<bd.getSize();i++)
                     bd.getBill(i).showbill();
          }
          else{
              System.out.println("  there are no bills present  ");
          }
      }
      
      void displayAllProducts(){
          ProductDB pd=new ProductDB();
          if (pd.getProductList().size()>=0) {
                System.out.println("id \t name \t price \t stock ");
                for(int i=0;i<pd.getProductList().size();i++){
                pd.getProduct(i).displayProduct();
            }
          } else {
              System.out.println(" there is no product inserted ");
          }
        
      }
      
      void searchProduct(){
            ProductDB pd=new ProductDB();
           String name=null;
            try {
                System.out.println("enter the name of the product");
                name=br.readLine();
            } catch (IOException e) {
            }
            int position=pd.search(name);
            if(position!=-1){
                System.out.println("Following Product is at position "+position);
            }
            else
                 System.out.println("Following Product is not present");
      }
      
      void removeProduct(){
          ProductDB pd=new ProductDB();
          int index=-1;
            System.out.println("enter the index of the Product to be remoed ");
            try {
                index=Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException e) {
            }
            if (index!=-1&&index<pd.getProductList().size()) {
                 pd.removeProduct(index);
                 System.out.println("Product had been removed successfully ");
            } else {
                 System.out.println("index is invalid........ ");
            }
      }
      
      int menu_salesman(){   int choice=0;
            System.out.println("1--->Bill a customer");
            System.out.println("2--->Show all Bills");
            System.out.println("3--->Search a Bill");
            System.out.println("4--->Modify a bill");
            System.out.println("5--->Cancel a bill");
            System.out.println("6--->Back to main menu");
            System.out.println("Enter your choice");
            try {
            choice=Integer.parseInt( br.readLine());
        } catch (IOException | NumberFormatException e) {
        }
            return choice;
    }//menu_salesman

}
