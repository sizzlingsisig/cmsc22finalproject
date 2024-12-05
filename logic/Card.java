package cmsc22groupproj;

public class Card {
    private String suit;                                                                //declaration
    private String rank;
    
    public Card(String suit, String rank) {                                             //construcort
        this.suit = suit;
        this.rank = rank;
    }
    
    public int getValue() {
        if (rank.equals("A")) return 1;
        if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) return 0;         //face card value 0
        return Integer.parseInt(rank);                                                  //need to parse kay naka string value sya initially sa list
    }
}