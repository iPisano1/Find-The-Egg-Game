import java.util.*;

public class FindTheEgg{

   static Scanner input = new Scanner(System.in);
   public int lives = 0;
   public int eggs = 0;
   public int bombs = 0;

   public static void main(String[] args){
      
      FindTheEgg game = new FindTheEgg();
      char[][] gameboard = new char[5][5];
       
      for (int i = 0; i < gameboard.length; i++) {
         for (int j = 0; j < gameboard[i].length; j++) {
            gameboard[i][j] = '-';
         }
      }
      
      // Select Difficulty
      game.selectDifficulty();
      
      // Random Placement Config
      game.placeItems(gameboard, '*', game.bombs);
      game.placeItems(gameboard, '0', game.eggs);
      
      // Play Game 
      game.PlayGame(gameboard);
      
   }
   
   public void selectDifficulty(){
   
      System.out.println("=========== Find The Egg Game ==========");
      System.out.println("Select Difficulty: ");
      System.out.println("1. Easy\n2. Normal\n3. Hard");
      System.out.print("Input: ");
      int difficulty = input.nextInt();
      
      // GAME CONFIGURATION
      switch(difficulty){
         
         // EASY DIFFICULTY
         case 1:
            lives = 6;
            eggs = 1;
            bombs = 6;
            break;
         
         // NORMAL DIFFICULTY
         case 2:
            lives = 3;
            eggs = 3;
            bombs = 10;
            break;
         
         // HARD DIFFICULTY
         case 3:
            lives = 1;
            eggs = 5;
            bombs = 15;
            break;
         default:
            System.out.println("========================================");
            System.out.println("Invalid Input.");
            selectDifficulty();
            break;
      }
   }

   public void placeItems(char[][] gameboard, char item, int count) {
      Random rand = new Random();
      int placed = 0;
      
      while (placed < count) {
         int row = rand.nextInt(gameboard.length);
         int col = rand.nextInt(gameboard[0].length);
         
         if (gameboard[row][col] == '-') {
            gameboard[row][col] = item;
            placed++;
         }
      }
      
   }

   public void PlayGame(char[][] gameboard){
   
      System.out.println("=========== Find The Egg Game ==========\n");
      
      System.out.println("\tFind the Egg and Avoid the Bombs!\n");
      
      while(lives > 0 || eggs == 0){
         
         int rows = 1;
         
         System.out.println("\t\t\tLives: " + lives + " | Eggs Left: " + eggs);
         System.out.println("\n\t\t\tCol 1\tCol 2\tCol 3\tCol 4\tCol 5");
         System.out.println("");
         for(int i = 0; i < gameboard.length; i++){
            System.out.print("Row " + rows + "\t");
            for(int j = 0; j < gameboard[i].length; j++){
               if(gameboard[i][j] == 'X'){
                  System.out.print("\t[ " + gameboard[i][j] + " ]");
               }else{
                  System.out.print("\t[ " + "-" + " ]");
               }
            }
            System.out.println();
            rows++;
         }
         
         System.out.print("\nEnter Row Placement(1-5): ");
         int placementRow = input.nextInt();
         System.out.print("Enter Column Placement(1-5): ");
         int placementColumn = input.nextInt();
         
         placementRow--;
         placementColumn--;
         
         System.out.println("\n========================================");
         
         if(placementRow >= 5 || placementColumn >= 5 || placementRow < 0 || placementColumn < 0){
            System.out.println("\n\t\t\tInvalid Placement.\n");
         }else if(gameboard[placementRow][placementColumn] == '0'){
            gameboard[placementRow][placementColumn] = 'X';
            System.out.println("\n\t\t\tYou Found an Egg!\n");
            eggs--;
         }else if(gameboard[placementRow][placementColumn] == '*'){
            gameboard[placementRow][placementColumn] = 'X';
            System.out.println("\n\t\t\tYou Found a Bomb!\n");
            lives--;
         }
         else{
            gameboard[placementRow][placementColumn] = 'X';
            System.out.println("\n\t\t\tYou Found Nothing!\n");
         }
          
         if(eggs == 0){
            for(int i = 0; i < gameboard.length; i++){
               for(int j = 0; j < gameboard[i].length; j++){
                  System.out.print("\t[ " + gameboard[i][j] + " ]");
               }
               System.out.println();
            }
            System.out.println("\n\tYou Win! You Found all the Eggs.");
            break;
         }else if(lives == 0){
            for(int i = 0; i < gameboard.length; i++){
               for(int j = 0; j < gameboard[i].length; j++){
                  System.out.print("\t[ " + gameboard[i][j] + " ]");
               }
               System.out.println();
            }
            System.out.println("\n\tYou Lose! You Run Out of Lives.");
            break;
         }
         
      }
      
      
   }

}