import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Game {
    //number of players
    private int players;
    //stack of cards
    private Stack <Integer> stock;
    //discard pile
    private Stack <Integer> discard;
    //list of players
    private ArrayList<PlayerCards> listOfPlayers;

    //constructor
    public Game(int players) {
        this.players = players;
        this.stock= new Stack<>();
        this.discard= new Stack<>();
        this.listOfPlayers=new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            stock.push(i);
            Collections.shuffle(stock);
        }
        //take the top of the stack and put it in the discard pile
        discard.push(stock.pop());
        if (players >= 2) {
            //create two players
            PlayerCards player1 = new PlayerCards();
            PlayerCards player2 = new PlayerCards();
            //add players to the list of players
            listOfPlayers.add(player1);
            listOfPlayers.add(player2);
        }
        if (players >= 3) {
            PlayerCards player3 = new PlayerCards();
            listOfPlayers.add(player3);
        }
        if (players == 4) {
            PlayerCards player4 = new PlayerCards();
            listOfPlayers.add(player4);
        }
        for (PlayerCards player : listOfPlayers) {
            dealCards(player);
        }
    }


    public void playGame() {
        //while no one wins keep playing
        while (!checkWin()) {
            for (int i = 0; i < players; i++) {
                //print out players cards and the top of the discard pile
                System.out.println("Player "+ (i+1) +" cards: " + listOfPlayers.get(i).toString());
                System.out.println("Top of discard pile: " + discard.peek());
                Scanner scanner = new Scanner(System.in);
                int choice=2;
                while(choice !=0 && choice !=1){
                    System.out.println("Would you like to pick a card from the stock (0) or from the discard pile(1)?");
                    choice = scanner.nextInt();}
                if (choice == 0) {
                    System.out.println("The card you picked is: " + stock.peek());
                    int choice2=2;
                    while(choice2 !=0 && choice2 !=1){
                        System.out.println("Would you like to put it in the discard pile (0) or exchange it? (1)");
                        choice2 = scanner.nextInt();}
                    //put the card the player picked into the discard pile
                    if (choice2 == 0) {
                        checkIfStockIsEmpty();
                        discard.push(stock.pop());
                    }
                    else if (choice2 == 1) {
                        System.out.println("which slot would you like to exchange this card for?");
                        int choice3 = scanner.nextInt();
                        //exchange the card
                        discard.push(listOfPlayers.get(i).removeCard(choice3 -1));
                        checkIfStockIsEmpty();
                        listOfPlayers.get(i).addCardsAtPosition(choice3 -1, (Integer) stock.pop());
                        System.out.println("Player "+ (i+1) +" cards: " + listOfPlayers.get(i).toString());
                        if(checkWin()){
                            return;
                        }

                    }
                } else{
                    //exchange the card
                    System.out.println("The card you picked is: " + discard.peek());
                    System.out.println("which slot would you like to exchange this card for?");
                    int choice3 = scanner.nextInt();
                    int cardToRemove=listOfPlayers.get(i).getCardAtPosition(choice3 -1);
                    listOfPlayers.get(i).removeCard(choice3 -1);
                    listOfPlayers.get(i).addCardsAtPosition(choice3 -1, (Integer) discard.pop());
                    discard.push(cardToRemove);
                    System.out.println("Player "+ (i+1) +" cards: " + listOfPlayers.get(i).toString());
                    if(checkWin()){
                        return;
                    }
                }
            }
        }
    }

    public void checkIfStockIsEmpty() {
        if (stock.isEmpty()) {
            //if stock is empty turn over the discard pile and make it the stock pile but leave the top card in the discard pile
            Integer discardTop=discard.pop();
            for (int i = 0; i < discard.size(); i++) {
                stock.push(discard.pop());
            }
            discard.push(discardTop);
        }
    }
    public boolean checkWin(){
        //a player wins when their cards are in ascending order
        boolean win=true;
        for (int i = 0; i < listOfPlayers.size(); i++) {
            win=true;
            for (int j=0; j< listOfPlayers.get(i).amountCards()-1;j++){
                //if cards are not in ascending order then there is no win
                if (listOfPlayers.get(i).getCardAtPosition(j)>listOfPlayers.get(i).getCardAtPosition(j+1)){
                    win=false;
                    break;
                } }
            if (win){
                System.out.println("RACK-O! Player "+(i+1)+" Wins!");
                return true;
            }}
        return win;
    }
    public void dealCards(PlayerCards player){
        for (int i = 0; i < 10; i++) {
            checkIfStockIsEmpty();
            player.addCards(stock.pop());
            //reverse the cards because each card that is dealt is supposed to go to the end of the array
            player.reverseCards();
    }
}
}


