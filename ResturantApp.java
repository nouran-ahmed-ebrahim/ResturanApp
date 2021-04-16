
package resturantapp;
import data.*;
import java.util.*; 

public class ResturantApp {
    
    static void FillData()
    {
        Admins.FillCurrentAdminsData();
        Menu.intialMenu();
    }
    static void adminLogin()
    {
        Scanner input=new Scanner(System.in);
         String adminName, adminPassword;
                
                System.out.println("Enter your name : ");
                input.nextLine();
                        
                adminName = input.nextLine();
                
                System.out.println("Enter password : ");
                adminPassword = input.nextLine();
                
                Admins.authenticate(adminName, adminPassword);
    }
    public static void main(String[] args) {
        
        FillData();
       
        
        char AntherOperation;
        int operationNumber , OptionNum;
        Scanner input=new Scanner(System.in);
        
        System.out.println("                 Resturant ");
        System.err.println(); 
        do{
        System.out.println("Choose user Type ");
        System.out.println("1)Admin              2) Customer");
        System.out.print("Enter your type's number  : ");
        operationNumber = input.nextInt();
        
   
         switch(operationNumber)
         {
            case 1:
                adminLogin();
                break;
                
            case 2: 
                Cutomer.cutomerOperation();
                break;
                
            default:
                System.out.println("Invalid choise");
         } 
          System.out.println("choose option : ");
          System.out.println("1) Change your user type      2) Close ");
           OptionNum=input.nextInt();
     } while(OptionNum !=2);
        System.out.println();
        System.out.println("GoodBye");
     }
    
}
