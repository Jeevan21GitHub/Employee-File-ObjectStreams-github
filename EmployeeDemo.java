import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

class Employee implements Serializable{
    int empno;
    String empName;
    int service;
    int salary;
    Employee(int empno,String empName,int service,int salary){
        this.empno=empno;
        this.empName=empName;
        this.service=service;
        this.salary=salary;  
    }
    public String toString(){
        return empno+" "+empName+" "+service+" "+salary;
    }
}

public class EmployeeDemo {
    public static void main(String args[]) throws Exception{
        ArrayList<Employee> ls=new ArrayList<Employee>();
        Scanner sc=new Scanner(System.in);
        File file=new File("D:\\Java\\EmployeeDetail.txt");
        ObjectOutputStream oos=null;
        ObjectInputStream ois=null;
        ListIterator itr=null;

        if(file.isFile()){
            ois=new ObjectInputStream(new FileInputStream(file));
            ls=(ArrayList<Employee>) ois.readObject();
            ois.close();
        }

        int choice=-1;
        do{
          System.out.println("1.insert");
          System.out.println("2.display");
          System.out.println("3.Increamnet who eligible for 2 year service");
          System.out.println("0.exit");
          choice=sc.nextInt();
          switch(choice){
            case 1:  System.out.println("Enter the number of employees");
                int n=sc.nextInt();
                for(int i=0;i<n;i++){
                System.out.println("Enter the employee no");
                int empno=sc.nextInt();
                System.out.println("Enter the employee name");
                String name=sc.next();
                System.out.println("Enter the service");
                int service=sc.nextInt();
                System.out.println("Enter the employee salary");
                int salary=sc.nextInt();
                
                ls.add(new Employee(empno, name,service,salary));
                 }
                 oos=new ObjectOutputStream(new FileOutputStream(file));
                 oos.writeObject(ls);
                 oos.close();
                 
                 break;
            case 2:{
                System.out.println("________________________________________________");
                itr=ls.listIterator();
                while(itr.hasNext()){
                    System.out.println(itr.next());
                }
                System.out.println("___________________________________________________");
            }
            break;
            case 3:{
               if(file.isFile()){
                ois=new ObjectInputStream(new FileInputStream(file));
                ls=(ArrayList<Employee>) ois.readObject();
                ois.close();
                itr=ls.listIterator();
                while(itr.hasNext()){
                    Employee e=(Employee) itr.next();
                    if(2<=e.service){
                        int newSalary=(e.salary*3/100)+e.salary;
                        itr.set(new Employee(e.empno,e.empName,e.service,newSalary));
        
                    }
                }
                System.out.println("________________________________________________");
                itr=ls.listIterator();
                while(itr.hasNext()){
                    System.out.println(itr.next());
                }
                System.out.println("___________________________________________________");
                oos=new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(ls);
                oos.close();
               }
            }break;
          
          }
        }while(choice!=0);

    }
}