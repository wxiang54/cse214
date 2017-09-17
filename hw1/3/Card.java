public class Card implements Comparable {
    private char _suit;
    private int _rank;
    //J,Q,K,A turn into 11,12,13,14 respectively

    public Card( char suit, int rank ) {
        _suit = suit;
        _rank = rank;
    }

    //precond: string is in format "<suit><rank>"
    public Card( String suit_rank ) {
        _suit = suit_rank.charAt(0);
        _rank = rank_s2i(suit_rank.substring(1));
    }

    public String getRank() {
        return rank_i2s(_rank);
    }
    public char getSuit() {
        return _suit;
    }

    /**********************************************
     * TRANSFORMATION METHODS
     * (my reasoning here was that comparing ints when sorting
     * is much easier than strings in the context of cards)
     **********************************************/
    //rank int to str (11->J, 12->Q, etc.)
    public static String rank_i2s(int i) {
        switch(i) {
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
        }
        return i + ""; //convert to string
    }

    //rank str to int (J->11, Q->12, etc.)
    public static int rank_s2i(String s) {
        switch(s) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
        }
        return Integer.parseInt(s);
    }

    @Override
    public String toString() {
        return _suit + rank_i2s(_rank);
    }

    public int compareTo(Object o) {
        if (o == null) throw new NullPointerException();
        if ( !(o instanceof Card) ) throw new ClassCastException();
        Card c = (Card) o;

        //scrt by suit (S>H>D>C) then rank (A>K>Q>J>10>...>2)
        if (_suit != c._suit)
            //the concept here is that suits are in alphabetical order
            return _suit - c._suit;
        return _rank - c._rank;
    }
}