package cmsc22groupproj;

public class Bet {
    private String betType;                                                         //Player/Banker/Tie
    private double amount;

    public Bet(String betType, double amount) {
        this.betType = betType;
        this.amount = amount;
    }

    public String getBetType() {
        return betType;
    }

    public double getAmount() {
        return amount;
    }

    public double resolveBet(Player player, Player banker) {
        int playerScore = player.getTotalScore();
        int bankerScore = banker.getTotalScore();

        if (betType.equals("Player") && playerScore > bankerScore) {
            return amount * 2;
        } else if (betType.equals("Banker") && bankerScore > playerScore) {
            return amount * 1.95;                                                   //1.95 kay may 5% commission para sa bank
        } else if (betType.equals("Tie") && playerScore == bankerScore) {
            return amount * 8;
        } else {
            return 0;
        }
    }
}