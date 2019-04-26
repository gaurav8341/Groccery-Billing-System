/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groceryfile1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Rajput
 */
public class BillDB {
    private ArrayList<Bill> BillList;
    static DataInputStream dis;
    static DataOutputStream dos;
    static private String filename="Bill.dat"; 
    
     static{
        File f=new File(filename);
        try {
              if(!f.exists())
             {
                dos=new DataOutputStream(new FileOutputStream(filename));
                System.out.println("File created successfully....");
                dos.close();
              }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     public BillDB() {
        readDatafile();
     }

    public ArrayList<Bill> getBillList() {
        return BillList;
    }

    public Bill getBill(int index){
        return BillList.get(index);
    }
    
     public void setBill(int index,Bill pd){
        BillList.set(index,pd);
        modifyFile();
    }

     public void removeBill(int index){
         BillList.remove(index);
         modifyFile();
     }
     
     public void sort(){
        Collections.sort(BillList);
         modifyFile();
     }
     
    void modifyFile() {
        File f=new File(filename);
        f.delete();
        try {
            dos=new DataOutputStream(new FileOutputStream(filename));
            for(int i=0;i<BillList.size();i++)
            {
                Bill p=BillList.get(i);
                writeRecord(p);
            }
            dos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void writeRecord(Bill p) {
        try {
            dos.writeUTF(p.getCustomer());
            dos.writeUTF(p.getMobno());
            writeList(p.getIngList());//for writing list in file
        } catch (Exception e) {
        }
        
    }

    void addBill(Bill p){
        try {
            dos=new DataOutputStream(new FileOutputStream(filename,true));//to ope in append mode
            writeRecord(p);
            dos.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        BillList.add(p);
    }
   
    int search(String target){   
        Bill s;
        for(int i=0;i<BillList.size();i++)
        {
            s=BillList.get(i);
            if(target.equals(s.getCustomer()))
            {
                    return i;      // found
            }//if
        }//for
        return -1;        // not found
    }

    public void writeList(ArrayList<Product> ingList ) {
        try {
                 ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename));
                 for(int i=0;i<ingList.size();i++)
                 {
                     oos.writeObject(ingList.get(i));
                 }
                 oos.close();

        } catch (EOFException e) {
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
     void readDatafile() {
        BillList=new ArrayList<Bill>();
        Bill record=null;
        try {
            dis=new DataInputStream(new FileInputStream(filename));
            while(true)
            {
                String customer=dis.readUTF();
                String mobid=dis.readUTF();
                ArrayList<Product> ingList=readList(); 
                record=new Bill(customer,mobid,ingList);
                BillList.add(record);
            }
        }catch(EOFException e){
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        try {
            dis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private ArrayList<Product> readList() {
        ArrayList<Product> ingList=new ArrayList<Product>();
        try {
                ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename));
                ingList=(ArrayList)ois.readObject();
                ois.close();
        } catch (EOFException e) {
        }catch(Exception e){
            System.out.println(e);
        }
        return ingList;
    }
    
    public int getSize(){
        return BillList.size();
    }
    
    public void showBillList(){
         System.out.println("index"+"\t"+"Customer"+"\t"+"Mobno");
        for(int i=1;i<=BillList.size();i++){
            System.out.println(i+BillList.get(i).getCustomer()+"\t"+BillList.get(i).getMobno());
        }
    }

   
}
