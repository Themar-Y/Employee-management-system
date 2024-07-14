/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructproject;

import java.util.Map;
//Themar yassir 2210456
//Hadeel Al-mokhtar  2210414


public class FinalProjectDS {

    
 public class NODE {
String em_name;
 String add;
 String Day;
 String phone_num;
 double hr;
 double sal;
 long ID;
 NODE next;

public NODE(long ID,String Day,String add, String em_name,double hr, String phone_num,double sal){

this.add=add;
this.hr=hr;
this.em_name=em_name;
this.Day=Day;
this.phone_num=phone_num;
this.sal=sal;
this.next=null;
this.ID=ID;
}
}
    
    
    
 NODE head;
 
 
 public void insertemp(long ID, String Day, String add, String em_name, double hr, String phone_num, double sal) {
    NODE N1 = new NODE(ID, Day, add, em_name, hr, phone_num, sal);

    if (head == null) {
        head = N1;
    } else {
        
        NODE temp = head;
        while (temp.next != null && sal > temp.next.sal) {
            temp = temp.next;
        }
        if (head == temp) {
            N1.next = head;
            head = N1;
        } else {
            N1.next = temp.next;
            temp.next = N1;
        }

        NODE outer = N1;
        NODE inner, prev = null;
        while (outer != null) {
            inner = outer.next;
            prev = outer;
            while (inner != null && inner.sal < prev.sal) {
                prev.next = inner.next;
                inner.next = outer;
                outer = inner;
                inner = prev.next;
            }
            outer = outer.next;
        } }}
 
   
   public NODE searching(long ID) {
    NODE temp = head;

    while (temp != null) {
    if (temp.ID == ID) {
   break;
   }
    temp = temp.next;
    }

  return temp;
   }
   
    
    public  int deletemp(long ID){
         
         NODE temp=head,prev=null;
         if (temp != null && temp.ID == ID) {
            head = temp.next; 
            return 0;
        }
         while (temp != null && temp.ID != ID) {
            prev = temp;
            temp = temp.next;
        }
        if(temp==null){
            return -1;
        }
        prev.next=temp.next;
        return 0;
   }
   
    
public String showemployees() {
  NODE temp = head;
  StringBuilder sb = new StringBuilder();

  while (temp != null) {
    sb.append("Employees Details :\n");
    sb.append("- ID: " + temp.ID + "\n");
    sb.append("- Employee Name: " + temp.em_name + "\n");
    sb.append("- Employee Address :" + temp.add + "\n");
    sb.append("- Employee Phone number: " + temp.phone_num + "\n");
    sb.append("- Salary: " + temp.sal + "\n");
    sb.append("- First day of work: " + temp.Day + "\n");
    sb.append("- Working hours: " + temp.hr + "\n\n"); // Added newline for better formatting
    temp = temp.next;
  }

  return sb.toString().isEmpty() ? "No employees found." : sb.toString();
}

   

 
    public void updateSal(long ID) {
    
    NODE temp = searching(ID);

    if (temp != null) {
        double bonus = Math.max(0, temp.hr - 32) * 2;  
        temp.sal += temp.sal * bonus / 100;
    }
}
  
    
    
    
    public boolean checkID(long ID) {
  NODE temp = head;

  while (temp != null) {
    if (temp.ID == ID) {
      return true; 
    }
    temp = temp.next;
  }

  return false; 
}
    
    
    
   public void updateEmployee(long id, Map<String, Object> updates) {
  NODE temp = searching(id);

  if (temp != null) {
    if (updates.containsKey("name")) {
      temp.em_name = (String) updates.get("name");
    }
    if (updates.containsKey("day")) {
      temp.Day = (String) updates.get("day");
    }
    if (updates.containsKey("phone")) {
      temp.phone_num = (String) updates.get("phone");
    }
    if (updates.containsKey("address")) {
      temp.add = (String) updates.get("address");
    }
    if (updates.containsKey("hr")) {
      temp.hr = (double) updates.get("hr");
    }
    if (updates.containsKey("salary")) {
      temp.sal = (double) updates.get("salary");
    }
  } else {
    
    System.out.println("Error: Employee with ID " + id + " not found.");
  }
}

}