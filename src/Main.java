
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice=1;
        while(choice !=2 && choice !=3 && choice !=4){
            System.out.println("How many people are playing? 2-4");
            choice = scanner.nextInt();}
            Game game=new Game(choice);
            game.playGame();
    }}