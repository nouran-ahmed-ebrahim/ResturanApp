
package data;

import Utils.Pair;
import java.util.*;

public class Cutomer {
    
    static Order orders[] = new Order[10];
    
    
    private static int TableValidation(int tableNum, Order[] orders)
    {
       int  orderIndex = Order.Findorder(tableNum, orders) ,choise=0;
         Scanner input = new Scanner(System.in);
       
                 do
                {
                    if (orderIndex == -1)
                    {  
                    System.out.println("Sorry this table dose not exist ");
                    System.out.println("1)cancel the operation      2) Enter table num");
                    choise= input.nextInt();
                    }
                   if(choise == 2)
                    {
                    System.out.println("Enter your table number ,please : ");
                    tableNum=input.nextInt();
                    orderIndex = Order.Findorder(tableNum, orders);
                   }
                }while (orderIndex==-1 && choise !=1);
                if(choise !=1)
                {
                    return tableNum;
                }
                else
                {
                    System.out.println("Canceled");
                    System.out.println();
                    return -1;
                }
                    
    }
    
    private static int numeOfMealValidation(int numOfChangedMeals,int custOrder, Order[] orders)
    {
       int  choise=0;
       
         Scanner input = new Scanner(System.in);
       
                 do
                {
                    if(numOfChangedMeals > orders[custOrder].getNumOfOrderedMeal() )
                   {
                     System.out.println("you ordered less than " + numOfChangedMeals +" meals");
                      System.out.println("1)cancel the operation      2) Enter  number of meals");
                    choise= input.nextInt();
                   } 
                   if(choise == 2)
                    {
                    System.out.println("Enter number of meals you want to change ,please : ");
                    numOfChangedMeals=input.nextInt();
                   }
                }while ( numOfChangedMeals > orders[custOrder].getNumOfOrderedMeal() && choise !=1);
                if(choise !=1)
                {
                    return numOfChangedMeals;
                }
                else
                {
                    System.out.println("Canceled");
                    System.out.println();
                    return -1;
                }
                    
    }
    
    private static void changeOrderMeals ( int tableNum ,int custOrder)
    {
     if ( tableNum != -1) 
       {
           int numOfChangedMeals ;
            String oldmeal , newmeal;
            Scanner input = new Scanner(System.in);     
            
         System.out.print("enter number of meals you want to change : ");
         numOfChangedMeals = input.nextInt();
                 
          numOfChangedMeals = numeOfMealValidation(numOfChangedMeals,custOrder, orders);
                     
         if (numOfChangedMeals != -1) 
            {
              System.out.println("enter old meal name : ");
              input.nextLine();
              oldmeal=input.nextLine();
                 
             while(Menu.findMealIndex(oldmeal) == -5 )
               {
                  System.out.println("Sorry this meal not in our menu , please enter anther menu");
                  System.out.print("enter old meal name : ");
                  oldmeal=input.nextLine();
                 }
                 
                 System.out.print("enter new meal name : ");
                 newmeal=input.nextLine();
                
                while(Menu.findMealIndex(newmeal) == -5)
                 {  
                   System.out.println("Sorry this meal not in our menu , please enter anther menu");
                   System.out.print("enter new meal name : ");
                   oldmeal=input.nextLine();
                  }
              
            Order.ChangeMeal(tableNum, oldmeal, newmeal, orders);
           }       
        } 
        System.out.println("Changed");
    }
    
    private static void addMealToOrder(int custOrder , int tableNumber)
    {
      if(tableNumber != -1)
      {
        int oldMealsNum =orders[custOrder].getNumOfOrderedMeal(), numOfAddedMeals ,finalMealsNum  ;
        String [] newMeals;
        Pair data = new Pair();
        Scanner input = new Scanner(System.in);
        
        
        data = MakeOrder(tableNumber);
       
        SaleData.UpdateSaleData(data.str);
       
         
        //create final  meals set orders[custOrder].getOrderedMeals()+data.str
        finalMealsNum = oldMealsNum + data.number;
        newMeals = new String[finalMealsNum];
        System.arraycopy(orders[custOrder].getOrderedMeals(),0,newMeals,0,oldMealsNum );
        System.arraycopy(data.str, 0 , newMeals, oldMealsNum, data.number);
        
          //change profit
        Order.TodayProfit -= orders[custOrder].totalCost;            //delete order cost
        orders[custOrder].totalCost = 0 ; //change total cost
        //change number of the all meal
        orders[custOrder].setNumOfOrderedMeal(data.number + oldMealsNum);
        
       
        //change meals 
        orders[custOrder].setOrderedMeals(newMeals); 
        orders[custOrder].totalCost = orders[custOrder].calcCost(); //change total cost
        
        Order.TodayProfit += orders[custOrder].totalCost;             // add order cost
             
      }
    }
    
    
    private static int TableEmpty (int tableNumber)
    {
         int  choise=0;
         boolean isEmpty;
         Scanner input = new Scanner(System.in);
       
                 do
                {
                    isEmpty = true;
                    choise = 0;
                   for(int currentOrder =0 ; currentOrder<Order.numOfOrders ; currentOrder++ )
                     {
                        if(orders[currentOrder].getTableNum() == tableNumber)
                        {
                            isEmpty = false;
                            break;
                        }
                     }
                   if (!isEmpty)
                    {
                      System.out.println("This table is not empty");
                      System.out.println("1)cancel the operation      2) Enter  number of meals");
                      choise= input.nextInt();
                    } 
                   if(choise == 2)
                    {
                    System.out.print("Enter your table number ,please : ");
                    tableNumber=input.nextInt();
                   }
                }while ( !isEmpty && choise !=1);
                if(choise !=1)
                {
                    return tableNumber;
                }
                else
                {
                    System.out.println("Canceled");
                    System.out.println();
                    return -1;
                }
    }
            
   private static Pair MakeOrder(int tabNum )
   {
     int numOfmeals;
     Pair p1 = new Pair();
     String []meals;
     String meal;
     Scanner input = new Scanner(System.in);
     
                 if (tabNum != -1)
                 {
                   System.out.print("enter number of meals you want to order : ");
                   numOfmeals=input.nextInt();
                   while(numOfmeals==0)
                    {
                     System.out.println("You cant enter 0 , please enter valid data"); 
                     System.out.print("enter number of meals you want to order : ");
                     numOfmeals=input.nextInt();
                   }
                 
                 
                   meals = new String[numOfmeals];
                 
                   for (int Mealindex = 0 ; Mealindex < numOfmeals ; Mealindex++  )
                   {
                     System.out.println("enter your meal : ");
                     
                     if(Mealindex ==0)
                     {
                         input.nextLine();
                     }
                    meal = input.nextLine();
                     while(Menu.findMealIndex(meal) == -5)
                     {
                      System.out.println("Sorry this meal is not on our menu");
                      System.out.println("enter your meal : ");
                      meal = input.nextLine();
                     }
                       
                    meals[Mealindex]=meal;
                    
                   }
                   System.out.println("");
                   System.out.println("15 minutes and your order will be prepared");
                   System.out.println("");
                   
                   p1.str=meals;
                   p1.number = numOfmeals;
                   
                  return p1; 
                 }  
                 return null;
        }
    
   static void searchOperation()
   {
       Scanner input = new Scanner(System.in);
        String meal;
                System.out.print("enter new meal name : ");
                input.nextLine();
                meal = input.nextLine();
                Menu.DisplayMeal(meal);
   }
   
   static void makeOrderOperation()
   {
        Scanner input = new Scanner(System.in);
        int tabNum;
                Pair data ;
                System.out.print("enter your Table number : ");
                tabNum=input.nextInt();
               
               if(Order.numOfOrders != 0 )
                 {
                  tabNum = TableEmpty(tabNum) ;
                 }
               data = MakeOrder(tabNum);
             orders[Order.numOfOrders]= new Order(tabNum, data.number , data.str);
   }
   
   static void changeOperation()
   {
        Scanner input = new Scanner(System.in);
          int tableNum , custOrder ,TypeofChange;
               
                 System.out.print("enter your Table number : ");
                 tableNum=input.nextInt();
                 tableNum = TableValidation(tableNum, orders);
                 custOrder=Order.Findorder(tableNum, orders);
                 
                 System.out.println("1) change meals       2) Add meal");
                 TypeofChange = input.nextInt();
                 
                 switch (TypeofChange)
                 {
                     case 1:
                         changeOrderMeals(tableNum ,custOrder);
                         break;
                         
                     case 2:
                         addMealToOrder(custOrder , tableNum);
                         break;
                         
                        default:
                            System.out.println("Invalid choise ");
                        
                 }
   }
   
static void showOrderOperation  ()
{
    Scanner input = new Scanner(System.in);
        System.out.println("Enter your table number ,please : ");
               int  tableNum=input.nextInt();
                tableNum=TableValidation(tableNum, orders);
                
                if(tableNum != -1)
                {
               int orderIndex = Order.Findorder(tableNum, orders);
                orders[orderIndex].DisplayOrderData();
                }
}      
   
static void showCost()
{
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your table number ,please : ");
                int tableNum=input.nextInt();
                tableNum=TableValidation(tableNum, orders);
                 if( tableNum != -1)
                {
                    int orderIndex = Order.Findorder(tableNum, orders);
                    System.out.println("Your order costs " + orders[orderIndex].totalCost + " LE");
                }
              
}
  static void displayOperations()
  {
        System.out.println("                welcom in our resturant ");
        System.out.println();
        System.out.println("choose Operation Number");
        System.out.print("1) Display menu    2) Make an Order    3) change your order's meal   ");
        System.out.print("4) Search about meal   5) Display our best meal   6)Show Order's Data ");
        System.out.println("   7) Show order cost");
        System.out.println();
  }
  
   //operation
  public static void  cutomerOperation()
    {
        int numOfOperation ;
         Scanner input = new Scanner(System.in);
        boolean invalid = true ;
        String AntherOperation;
        System.out.println();
        
        displayOperations();
  
       
        do{
        System.out.print("Enter operation number : ");
        numOfOperation = input.nextInt();
        switch(numOfOperation){
            
            case 1:
                 Menu.DisplayMenu();
                 break;
                 
            case 2:
               makeOrderOperation();
               break;
                
            case 3:
              
               changeOperation();
                 break;
                
            case 4:
                
               searchOperation();
                break;
                
            case 5:
                SaleData.FindBestSallerMeal();
                break;
            
            case 6:
             showOrderOperation();
               
                break;
                
            case 7:
              showCost();
                break;
                
            
                
            default:
                System.out.println("invalid operation");
        }
        
        do{
            System.out.print("Do you want anther operation (yes / no) : ");
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
        
       
    }
    
}
