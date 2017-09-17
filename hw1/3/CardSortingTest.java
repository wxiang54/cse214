public class CardSortingTest {
    public static void main(String[] args) {
        String[] cardsForPlayer1 = {
                "S4", "D8", "C4", "D3", "D5", "DJ", "S3",
                "D4", "DA", "SJ", "D7", "H10", "D6"
        };

        Card[] cards = new Card[13];
        for (int i = 0; i < cardsForPlayer1.length; i++){
            Card mCard = new Card(cardsForPlayer1[i]);
            cards[i] = mCard;
        }

        int id = 1;
        Player player = new Player(id);

        System.out.println("player.setCards(<cards>) ->");
        player.setCards(cards);
        player.printCards();
        //should print: S4 D8 C4 D3 D5 DJ S3 D4 DA SJ D7 H10 D6

        System.out.println("\nplayer.sortCards() ->");
        player.sortCards();
        player.printCards();
        //should print: SJ S4 S3 H10 DA DJ D8 D7 D6 D5 D3 C4
    }
}