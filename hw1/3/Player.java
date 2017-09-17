public class Player {
    private Card[] _cards;
    private int _id;

    public Player(int id) {
        _id = id;
    }
    public Card[] getCards() {
        return _cards;
    }
    public void setCards(Card[] cards) {
        _cards = cards;
    }

    public void printCards() {
        for (Card c : _cards)
            System.out.print(c + " ");
        System.out.println();
    }

    public void sortCards() {
        //INSERTION SORT O(n^2)
        for (int i = 1; i < _cards.length; i++) {
            for (int check = i-1;
                 check >= 0 && _cards[check].compareTo(_cards[check+1]) < 0;
                 //run when check not negative and current ind > prev ind
                 //(sorting by descending order)
                 check--) {
                swapCards(check, check+1);
            }
        }
    }

    public void swapCards(int ind1, int ind2) {
        Card tmp = _cards[ind1];
        _cards[ind1] = _cards[ind2];
        _cards[ind2] = tmp;
    }

    public static void main(String[] args) {

    }
}