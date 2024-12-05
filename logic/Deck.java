package cmsc22groupproj;

public class Deck {
    private Card[] cards;                                                   //declaration sng list cards
    private int currentIndex;                                               //index to track for later

    public Deck() {
        cards = new Card[52];                                               //initialization of list with 52 slots
        int index = 0;                                                      //initialization od index sa 0

        //populate the deck with 52 cards
        for (int suit = 0; suit < 4; suit++) {                              //from 4 suits
            for (int rank = 1; rank <= 13; rank++) {                        //from 1 to 13 ranks
                cards[index++] = new Card(rank);
            }
        }

        shuffle();                                                          //shuffle upon creation
        currentIndex = 0;                                                   //reset the current index
    }

    public void shuffle() {
        //simple shuffle
        for (int i = cards.length - 1; i > 0; i--) {                        //from last until 2nd card
            int j = (int) (Math.random() * (i + 1));                        //when at i make a j position (randomize between 0 to i) for swapping later
            Card temp = cards[i];                                           //temp holds i elements
            cards[i] = cards[j];                                            //the cards that is located at index i will be swapped to index j which is before that card or any random generated index for that i [bale if i ta bi is 0 tas ang generated nga j saiya is 4 si index 4 na ang ma store sa index 0]
            cards[j] = temp;                                                //since j has been transfered to index i the elements from index i will be stored in index j [in sximpler terms ging swap sila HAHAHAHHAHAHAHAHA]                                            
        }
    }

    public Card drawCard() {
        if (currentIndex < cards.length) {
            return cards[currentIndex++];                                   //if may unod return ang 1st card tas increment
        } else {
            return null;                                                    //ubos na cards
        }
    }

    public boolean hasCards() {
        return currentIndex < cards.length;
    }
}