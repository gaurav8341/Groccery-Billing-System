package groceryfile1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/*import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;*/
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("StaticNonFinalUsedInInitialization")
public class ProductDB {
    private ArrayList<Product> ProductList;
    static DataInputStream dis;
    static DataOutputStream dos;
    static private String filename="Product.dat"; 
    
    static{
        File f=new File(filename);
        try {
              if(!f.exists()){
                dos=new DataOutputStream(new FileOutputStream(filename));
                System.out.println("File created successfully....");
                dos.close();
              }
        } catch (IOException e) {
            //System.out.println(e);
        }
    }
    
     public ProductDB() {
        readDatafile();
     }

    public ArrayList<Product> getProductList() {
        return ProductList;
    }

    public Product getProduct(int index){
        return ProductList.get(index);
    }
    
     public void setProduct(int index,Product pd){
        ProductList.set(index,pd);
        modifyFile();
    }

     public void removeProduct(int index){
         ProductList.remove(index);
         modifyFile();
     }
     
     public void sort(){
        Collections.sort(ProductList);
         modifyFile();
     }
     
    @SuppressWarnings({"Convert2Diamond", "BroadCatchBlock", "TooBroadCatch"})
    final void readDatafile() {
        ProductList=new ArrayList<Product>();
        @SuppressWarnings("UnusedAssignment")
        Product record;
        try {
            dis=new DataInputStream(new FileInputStream(filename));
            while(true)
            {
                int id=dis.readInt();
                String name=dis.readUTF();
                float price =dis.readFloat();
                int stock=dis.readInt();
               record=new Product(id,name,price,stock);
               ProductList.add(record);
            }
        }catch(EOFException e){
        }
        catch(IOException e){
        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            try {
                dis.close();
            } catch (IOException ex) {
            }
        }
               sort();
    }

    void modifyFile() {
        File f=new File(filename);
        f.delete();
        try {
            dos=new DataOutputStream(new FileOutputStream(filename));
            for (Product p : ProductList) {
                writeRecord(p);
            }
            dos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    void writeRecord(Product p) {
        try {
            //dos.writeInt(ProductList.indexOf(p));
           //dos.writeObject(p);
            dos.writeInt(p.getId());
            dos.writeUTF(p.getName());
            dos.writeFloat(p.getPrice());
            dos.writeInt(p.getStock());
        } catch (IOException e) {
                        System.out.println(e);
        }
        
    }

    void addProduct(Product p){
        try {
            dos=new DataOutputStream(new FileOutputStream(filename,true));//to ope in append mode
            writeRecord(p);
            dos.close();
            
        } catch (IOException e) {
                        System.out.println(e);
        }
        ProductList.add(p);
    }
   
    int search(String target){   
        Product s;
        for(int i=0;i<ProductList.size();i++)
        {
            s=ProductList.get(i);
            if(target.equals(s.getName()))
            {
                    return i;      // found
            }//if
        }//for
        return -1;        // not found
    }
     
}
