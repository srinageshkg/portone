package com.dcp.portone.leet.collectns;

import java.time.LocalDateTime;
import java.util.*;

public class FuncHash {

    public static void main(String[] args) {

        PlayingCard aceHearts = new PlayingCard("Heart", "Ace");
        PlayingCard kingClubs = new PlayingCard("Club", "King");
        PlayingCard queenSpades = new PlayingCard("Spade", "Queen");
        PlayingCard jackDiamonds = new PlayingCard("Diamond", "Jack");
        PlayingCard kingClubsdup = new PlayingCard("Club", "King");

        List<PlayingCard> cards = new ArrayList<>(Arrays.asList(aceHearts, kingClubs,queenSpades, jackDiamonds, kingClubsdup));
        cards.forEach(s-> System.out.println(s +": "+ s.hashCode()));

        Set<PlayingCard> deck = new HashSet<>();
        for (PlayingCard card: cards) {
            if (!deck.add(card)) {
                System.out.printf("Found a Duplicate for "+ card);
            }
        }
        System.out.println("deck = " + deck);

        List<Card> tdeck = Card.getStandardDeck();
        Card.printDeck(tdeck);

        deckDemo();
    }
    public static void cardControler() {
        Comparator<Card> sortRankReverseSuit = Comparator.comparing(Card::rank).reversed().thenComparing(Card::suit);
        int randomInBetween = new Random().nextInt(10, 99);
        for (int i=0; i<5; i++) {
            System.out.println(" = " + new Random().nextInt(50));
        }
    }

    public static void deckDemo() {
        CharSequence cs = "dsfdls";
        LocalDateTime dt = LocalDateTime.now();
        System.out.printf("%tD\t\t", dt);
        System.out.printf("%tr\t\t", dt);
//        System.out.printf("%tc\t\t", dt);
        System.out.printf("%1$tD %1$tT : %2$s%n", dt, cs);

        Comparator<Card> sortRankReverseSuit = Comparator.comparing(Card::rank).reversed().thenComparing(Card::suit);

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        System.out.println("Size of Arrays fill is: " + Arrays.asList(cardArray).size());
        Card.printDeck(List.of(cardArray),"Ace of Hearts", 1);

        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards,aceOfHearts);
        System.out.println("Size of Collection fill is: " + cards.size());
        Card.printDeck(cards,"Ace Of Hearts Nothing...", 2);

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);
        Card.printDeck(acesOfHearts, "Ace of Hearts Collection nCopies", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB,'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "King of Clubs Collection nCopies", 1);

        Collections.addAll(cards, cardArray);
        Collections.addAll(cards, cardArray);
        System.out.println("Size of Collection addAll is: " + cards.size());
        Card.printDeck(cards, "Card Collection with Aces added", 2);

        Collections.copy(cards, kingsOfClubs);
        System.out.println("Size of Collection copy is: " + cards.size());
        Card.printDeck(cards, "Card Collection with Kings added", 2);

        cards = List.copyOf(kingsOfClubs);
        // Card.printDeck(cards);

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        Collections.shuffle(deck);
        Card.printDeck(deck, "Deck shuffle", 4);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Deck of Cards", 4);

        var sortingAlgorithm = Comparator.comparing(Card::rank).thenComparing(Card::suit);
        var sortBySuit = Comparator.comparing(Card::suit).thenComparing(Card::rank);
        Collections.sort(deck, sortingAlgorithm);
        Card.printDeck(deck, "Standard Deck Sorted by Rank and then Suit", 13);

        Collections.reverse(deck);
        //sublists
        //int firstIndexOfKings =
        List<Card> kings = new ArrayList<>(deck.subList(4,8));
        Card.printDeck(kings, "Kings in Deck", 1);
        List<Card> tens = new ArrayList<>(deck.subList(16,20));
        Card.printDeck(tens, "Tens in Deck", 1);

        int subListIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("subListIndex = " + subListIndex);
        System.out.println("Contains = " + deck.containsAll(tens));

        boolean disJoint = Collections.disjoint(deck, tens);
        System.out.println("disJoint = " + disJoint);
        boolean disJoint2 = Collections.disjoint(kings, tens);
        System.out.println("disJoint2 = " + disJoint2);

        Collections.sort(deck,sortingAlgorithm);
        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);
        int foundIndex = Collections.binarySearch(deck,tenOfHearts, sortingAlgorithm);
        System.out.println("foundIndex = " + foundIndex);
        System.out.println(deck.get(foundIndex));
        System.out.println("foundIndex = " + deck.indexOf(tenOfHearts));

        System.out.println("Freequency of tenOfHearts = " + Collections.frequency(deck,tenOfHearts));
        System.out.println("Best Card = " + Collections.max(deck,sortingAlgorithm));
        System.out.println("Worst Card= " + Collections.min(deck,sortingAlgorithm));

        deck.sort(sortBySuit);
        Card.printDeck(deck," Sorted by Suit and then Rank :", 4);
        //Collections.reverse(deck);
        //Card.printDeck(deck," Sorted by Suit and then Rank :", 4);

        List<Card> copied = new ArrayList<>(deck.subList(0,13));
        Collections.rotate(copied, 4);
        System.out.println("copied = " + copied);
        copied = new ArrayList<>(deck.subList(13,26));
        Collections.rotate(copied, -4);
        System.out.println("copied = " + copied);
        copied = new ArrayList<>(deck.subList(26,39));
        for (int i= 0; i< copied.size()/2; i++){
            Collections.swap(copied,i,copied.size()-1-i);
        }
        System.out.println("Manual Reverse = " + copied);
        copied = new ArrayList<>(deck.subList(39,52));
        Collections.reverse(copied);
        System.out.println("Manual Reverse rotate = " + copied);
        Collections.rotate(copied, 7);
        System.out.println("Manual Reverse rotate = " + copied);

    }
}

class PlayingCard{
    private String suit;
    private String face;
    private int internalHash;

    public PlayingCard(String suit, String face) {
        this.suit = suit;
        this.face = face;
        this.internalHash = 1;
    }

    @Override
    public String toString() {
        return "PlayingCard{" + "face='" + face + '\'' + ", suit='" + suit + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        //if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        //PlayingCard that = (PlayingCard) o;
        //return Objects.equals(suit, that.suit) && Objects.equals(face, that.face);
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(suit, face);
        return super.hashCode();
    }
}
