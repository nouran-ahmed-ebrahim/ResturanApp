
package data;


public class SaleData {
    
     static int numOfSaledMeals=0;     
     static double [] TotalProfit = new double[10];
     static String [] mealName = new String[10];
    
      protected static void AddItem(double price ,String MealName)
        {
            mealName[numOfSaledMeals]= MealName;
            TotalProfit[numOfSaledMeals]=price;
            numOfSaledMeals++;
        }
        
        
     public static void DisplaySaleData()
         {
             for(int currentMeal = 0; currentMeal<numOfSaledMeals; currentMeal++ )
             {
                 if(TotalProfit[currentMeal] >0)
                 System.out.println( mealName[currentMeal]+" it cost "+TotalProfit[currentMeal]);
             }
             if (numOfSaledMeals==0)
                 System.out.println("Empty");
                 System.out.println();
         }
       
       public static  int findMealIndex(String MealName)
        {
            int MealIndex = -5 ;
            for(int  currentMealIndex = 0; currentMealIndex<numOfSaledMeals; currentMealIndex++ )
            {
                if(MealName.equals(mealName[currentMealIndex]) )
                {
                    MealIndex=currentMealIndex;
                    break;
                }
            }
            return MealIndex;
        }
       
         protected static void UpdateSaleData(String[] Orderedmeals)
         {
           for (String meal : Orderedmeals )
           {        
          SaleData.UpdateTotalProfit(meal);
          }
        }
       
        public static void UpdateTotalProfit (String MealsName){
          
         int MealIndex = findMealIndex(MealsName);
         
         if(MealIndex != -5)
         {
             TotalProfit[MealIndex]+= Menu.mealsPrice[Menu.findMealIndex(MealsName)];
         }
         else
         {
             MealIndex=Menu.findMealIndex(MealsName);
           if (MealIndex == -5)
            {
                System.out.println("Sorry, invaled meal ");
                System.out.println();
            }
           else
           {
               double price = Menu.mealsPrice[Menu.findMealIndex(MealsName) ];
               AddItem(price, MealsName);
           }
         }
    }
   
     public static void FindBestSallerMeal()
     {
          int BestMeal = 0;
            for(int  currentMealIndex = 1; currentMealIndex<numOfSaledMeals; currentMealIndex++ )
            {
                if(TotalProfit[BestMeal] < TotalProfit[currentMealIndex] )
                {
                    BestMeal=currentMealIndex;  
                }
            }
            if(mealName[BestMeal] !=null)
            {
              System.out.println("The Best Saller meal is "+mealName[BestMeal]); 
            }
            else
            {
                System.out.println("there is no best saller Meal");  
            }
           System.out.println(); 
     }
     
      public static void FindLeastSalingMeal()
       {
          int LeastMeal = 0;
            for(int  currentMealIndex = 1; currentMealIndex<numOfSaledMeals; currentMealIndex++ )
            {
                if(TotalProfit[LeastMeal] > TotalProfit[currentMealIndex] )
                {
                    LeastMeal=currentMealIndex;  
                }
            }
           if(mealName[LeastMeal] !=null)
            {
              System.out.println("The Least saling meal is "+mealName[LeastMeal]); 
            }
            else
            {
                System.out.println("there is no least saling meal");  
            }
         System.out.println();
       }
   
}


