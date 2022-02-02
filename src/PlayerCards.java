import java.util.ArrayList;
import java.util.Collections;


//players class
public class PlayerCards {

    private ArrayList<Integer> cards;

    //each player has a list of cards
    PlayerCards(){
        this.cards=new ArrayList<>();
    }
    public void addCards(int card){
        cards.add(card);
    }
    public void addCardsAtPosition(int position, int card){
        cards.add(position,card);
    }
    public int amountCards(){
        return cards.size();
    }
    public int removeCard(int index){
       int removed= cards.remove(index);
       return removed;
    }
    public void reverseCards(){
        Collections.reverse(this.cards);
    }
    public String toString(){
        StringBuilder builder= new StringBuilder();
        builder.append(cards);
        return builder.toString();
    }
    public int getCardAtPosition(int position){
        return cards.get(position);
    }

    }



