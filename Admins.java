
package data;
import java.util.*; 

public class Admins {

   static int CurrentAdminsNumber=0;
   static String [][] Authentication=new String[10][2];//Authentication
    
   public static void FillCurrentAdminsData()
   {
      Authentication[0][0]="manager";
      Authentication[0][1]="IamManager";
      Authentication[1][0]="Chef1";
      Authentication[1][1]="password";
       CurrentAdminsNumber+=2;
   }
   
    //validate - authenticate
      public static void authenticate(String AdminName , String AdminPassword)
      {
         int AdminDataIndex=-5; 
          for(int CurrentAdmin=0 ; CurrentAdmin<CurrentAdminsNumber; CurrentAdmin++)
          {
              if( Authentication[CurrentAdmin][0].equals(AdminName) &&  Authentication[CurrentAdmin][1].equals(AdminPassword) )
              {
                 AdminDataIndex = CurrentAdmin;
                  break;
              }
           }
          if(AdminDataIndex == -5)
          {
              System.out.println("Incorrect Data");
              System.out.println();
          }
          else
          {
              AdminFeatures(AdminDataIndex);
          }
      }
   
    public static void AddNewAdmin(String AdminName , String AdminPassword)
    {
        //current >= 10
        if(CurrentAdminsNumber >= 10) {
            System.out.println("Sorry you can not add new one , addmins number is complete");
        }
        Authentication[CurrentAdminsNumber][0] = AdminName;
        Authentication[CurrentAdminsNumber][1] = AdminPassword;
        CurrentAdminsNumber++;
        
        System.out.println(AdminName+" is admin now");
        System.out.println();
    }
    
    static void addAdminOperation()
    {
         Scanner input = new Scanner(System.in);
         System.out.println("enter admin name : ");
                input.nextLine();
                String NewAdminName = input.nextLine();
                
                System.out.println("enter admin password : ");
                String NewAdminPassword = input.nextLine();
                
                AddNewAdmin(NewAdminName, NewAdminPassword );
    }
    
    
    static void addMealOperation()
    {
         Scanner input = new Scanner(System.in);
         double mealPrice;
                String mealName;
                
                System.out.println("Enter new meal name : ");
                input.nextLine();
                mealName = input.nextLine();
                
                System.out.println("Enter meal's price");
                mealPrice = input.nextDouble();
                
                Menu.AddMeal(mealPrice, mealName);
    }
    
    
    static void modifyMealOperation()
    {
        Scanner input = new Scanner(System.in);
         double NewPrice;
         String ModifedMealName;
         System.out.println("Enter meal's name :");
         input.nextLine();
         ModifedMealName = input.nextLine();
           
         System.out.println("Enter the new price :");
         NewPrice = input.nextDouble();
                
         Menu.modifyMealPrice(ModifedMealName, NewPrice);
}
    
    static void showTotalProfit ()
    {
          double profit=Order.getTodayProfit();
                if(profit <1)
                {
                    System.out.println("there is no sales today");
                }
                else
                {
                System.out.println("the total profit now is : "+profit );
                }
    }
    
    static void adminOperations(String adminName)
    {
         System.out.println("                 welcom "+adminName);
        System.out.println();
        System.out.println("choose Operation Number");
        System.out.print("1) Add admin    2) Display Sale Data    3) Show best saller    ");
        System.out.print("4) Show least saling   5) Add meal to the menu    ");
        System.out.println("6) Modify meal's Price    7) TodayProfit ");
  
    }
    
    
    public static void AdminFeatures(int AdminDataIndex)
    {
        int numOfOperation;
        boolean invalid=true;
        String AntherOperation;
        Scanner input = new Scanner(System.in);
          
        adminOperations( Authentication[AdminDataIndex][0]);
      
       
        do{
        System.out.print("Enter opNumper : ");
        numOfOperation = input.nextInt();
        switch(numOfOperation){
            
            case 1:
               addAdminOperation();
                 break;
                 
            case 2:
                SaleData.DisplaySaleData();
                break;
                
            case 3:
                SaleData.FindBestSallerMeal();
                break;
                
            case 4:
                SaleData.FindLeastSalingMeal();
                break;
                
            case 5:
               addMealOperation();
                break;
                
            case 6:
               modifyMealOperation();
                break;
                
            case 7 :
              showTotalProfit();
                break;
                
            default:
                System.out.println("invalid operation");
        }
        
        do
        {    System.out.print("Do you want anther operation (yes/no) : ");
            AntherOperation = input.next();
            switch(AntherOperation)
            {
                case "yes":
                case "Yes":
                case "No" :
                case "no" :
                   invalid = false;
                   break;
                 default:
                     System.out.println("invalid choise");
                     System.out.println();
            }
        }while(invalid);    
            
            System.out.println();    
      } while ("yes".equals(AntherOperation) || "Yes".equals(AntherOperation) );
        
        System.out.println("Good bye " + Authentication[AdminDataIndex][0]);
    }
    
   
}
