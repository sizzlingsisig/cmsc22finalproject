package logic;

import java.util.ArrayList;
import java.util.List;

public class BetHistory {
    private List<Bet> bets;

    public BetHistory() {
        this.bets = new ArrayList<>();
    }

    public void addBet(Bet bet) {
        bets.add(bet);
    }

    public void displayBets() {
        System.out.println("Bet History:");
        for (Bet bet : bets) {
            System.out.println("Bet Type: " + bet.getBetType() + ", Amount: " + bet.getAmount());
        }
    }

    public List<Bet> getBets() {
        return bets;
    }
}