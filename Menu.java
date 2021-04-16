
package data;


public class Menu {
  
        static int numOfMeals=0;     
        static double [] mealsPrice = new double[10];
        static String [] mealsName = new String[10];
        //static Meal[] meals = new Meal[10];
        
        public static void intialMenu () //be in utils
        {
            mealsName[0]= "Meet & Spagetii";
            mealsPrice[0]=52;
            mealsName[1]= "checkin & rice";
            mealsPrice[1]=50.5;         
            numOfMeals+=2;
        }
        public static void AddMeal(double price ,String MealName)
        {
            mealsName[numOfMeals]= MealName;
            mealsPrice[numOfMeals]=price;
            numOfMeals++;
            System.out.println("one meal added");
            System.out.println();
        }
        public static void DisplayMenu()
         {
             System.out.println("         Menu");
             System.out.println();
             
             for(int currentMeal = 0; currentMeal<numOfMeals; currentMeal++ )
             {
                 System.out.println( mealsName[currentMeal]+" it cost "+mealsPrice[currentMeal]);
             }
             System.out.println();
         }
          public static int findMealIndex(String MealName)
        {
            int MealIndex = -5 ;
            for(int  currentMealIndex = 0; currentMealIndex<numOfMeals; currentMealIndex++ )
            {
                if(MealName.equals(mealsName[currentMealIndex]) )
                {
                    MealIndex=currentMealIndex;
                    break;
                }
            }
            return MealIndex;
        }
        
        public static void DisplayMeal(String MealName)
        {
           int MealIndex = findMealIndex(MealName);
            if (MealIndex == -5)
            {
                System.out.println("Sorry, this meal not in our menu");
                System.out.println();
            }
            else
            {
               System.out.println("Meal "+MealName+" costs " + mealsPrice[MealIndex]);
               System.out.println();
            }
        }
       
        
        public static void modifyMealPrice(String MealName , double NewPrice)  //if  it will change old and new
        {
           int MealIndex = findMealIndex(MealName);
            if (MealIndex == -5)
            {
                System.out.println("Sorry, this meal not in the menu");
                System.out.println();
            }
            else
            {
               mealsPrice[MealIndex]=NewPrice;   
               System.out.println("Meal "+MealName+" costs" + " has changed to "+ mealsPrice[MealIndex]);
               System.out.println();
            }
        }
}
