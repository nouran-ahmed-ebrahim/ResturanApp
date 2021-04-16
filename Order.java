
package data;

public class Order {
   
    private final int TableNum ;
    private int NumOfOrderedMeal ;
    double totalCost;
    static double TodayProfit;
    static int numOfOrders=0;
    private String [] orderedMeals;

    
    public int getTableNum() {
        return TableNum;
    }

    public int getNumOfOrderedMeal() {
        return NumOfOrderedMeal;
    }

    
    public  void setNumOfOrderedMeal(int NumOfOrderedMeal)
    {
        this.NumOfOrderedMeal = NumOfOrderedMeal;
    }
    
    public void setOrderedMeals(String[] orderedMeals) {
        this.orderedMeals = orderedMeals;
    }

    public String[] getOrderedMeals() {
        return orderedMeals;
    }
    
    
    protected double calcCost()
    {
        double mealCost ,cost=0;
        
        for(String CurrentMeal : orderedMeals)
        {
            mealCost= Menu.mealsPrice[Menu.findMealIndex(CurrentMeal)];
            cost+=mealCost;
        }
        return cost;
    }
    
      public static double getTodayProfit() {
        return TodayProfit;
    }
      
    
    
   public Order (int TableNum ,int NumOfOrderedMeal, String[] Orderedmeals)
   {
      this.NumOfOrderedMeal = NumOfOrderedMeal;
      this.TableNum = TableNum;
      this.orderedMeals = Orderedmeals;
       totalCost = calcCost();
       TodayProfit+=totalCost;
       SaleData.UpdateSaleData(Orderedmeals);
       numOfOrders++;
   }
   
   public static int Findorder(int tableNum , Order[] orders)
   {
       for(int Currentindex =0 ; Currentindex <numOfOrders ; Currentindex++ )
       {
           if(orders[Currentindex].TableNum == tableNum)
           {
               return Currentindex;
           }
       }
       return -1;
   }
   
   private static int FindMealInOrder(String[] orderMeals, int NumOfOrderedMeal ,String mealName)
   {
       for(int CurretMeal = 0 ; CurretMeal< NumOfOrderedMeal ; CurretMeal++)
       {
           if( orderMeals[CurretMeal].equals(mealName) )
           {
               return CurretMeal;
           }
       }
       return -1; //unused
   }
   
   
   public static void ChangeMeal(int tableNum , String OldMeal ,String NewMeal ,Order[] orders )
   {
       int OrderIndex = Findorder(tableNum, orders);
       
       if(OrderIndex >=0)
       {

       int oldMealIndexInSale = SaleData.findMealIndex(OldMeal);
       int oldMealIndexInMenu = Menu.findMealIndex(OldMeal);
       int oldeMealIndexInOrder = 
               FindMealInOrder(
                       orders[OrderIndex].orderedMeals,
                       orders[OrderIndex].NumOfOrderedMeal ,
                       OldMeal
               );
       
       int newMealIndexInSale =Menu.findMealIndex(NewMeal);
               
       SaleData.UpdateTotalProfit(NewMeal);
       SaleData.TotalProfit[oldMealIndexInSale] -= Menu.mealsPrice[oldMealIndexInMenu];
       
       orders[OrderIndex].orderedMeals[oldeMealIndexInOrder] = NewMeal;
       orders[OrderIndex].totalCost += Menu.mealsPrice[ Menu.findMealIndex(NewMeal)];
       orders[OrderIndex].totalCost -= Menu.mealsPrice[ Menu.findMealIndex(OldMeal)];
       
       TodayProfit -= Menu.mealsPrice[oldMealIndexInMenu];
       TodayProfit += Menu.mealsPrice[newMealIndexInSale];
       }
       else 
       {
           System.out.println("Sorry this order doesn't exist , Please check your Data");
           System.out.println();
       }
   }
   public void DisplayOrderData ()
   {
       System.out.println("This order is belong to table number "+ TableNum);
       System.out.println("This Table Order "+NumOfOrderedMeal + " meals");
       System.out.println("Meals :");
       for(int currentMeal=0 ; currentMeal < NumOfOrderedMeal ; currentMeal++)
       {
           System.out.println( (currentMeal+1) + ") "+ orderedMeals[currentMeal]);
       }
       System.out.println("This order costs "+ totalCost);
   }

  
}
