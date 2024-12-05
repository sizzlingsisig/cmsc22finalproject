package logic;

public class Deck {
    private Card[] cards;  // Declaration of the list of cards
    private int currentIndex;  // Index to track the current position in the deck

    public Deck() {
        cards = new Card[52];  // Initialization of the list with 52 slots
        int index = 0;  // Initialization of index to 0

        // Populate the deck with 52 cards
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};  // Define suits
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};  // Define ranks

        for (int suit = 0; suit < suits.length; suit++) {  // Loop through suits
            for (int value = 0; value < ranks.length; value++) {  // Loop through ranks
                cards[index++] = new Card(suits[suit], ranks[value], index);  // Create a new card
            }
        }

        shuffle();  // Shuffle upon creation
        currentIndex = 0;  // Reset the current index
    }

    public void shuffle() {
        // Simple shuffle
        for (int i = cards.length - 1; i > 0; i--) {  // From last until 2nd card
            int j = (int) (Math.random() * (i + 1));  // Generate a random index
            Card temp = cards[i];  // Hold the card at index i
            cards[i] = cards[j];  // Swap the cards
            cards[j] = temp;  // Complete the swap
        }
    }

    public Card drawCard() {
        if (currentIndex < cards.length) {
            return cards[currentIndex++];  // Return the current card and increment the index
        } else {
            return null;  // No more cards
        }
    }

    public boolean hasCards() {
        return currentIndex < cards.length;  // Check if there are cards left
    }
}