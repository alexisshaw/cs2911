package Game.CLIPlayer;

import Game.Die;
import card.Card;
import Game.Disk;

import java.util.List;

public class CliPlayerPrinter {
    private final Player player;

    public CliPlayerPrinter(Player player) {
        this.player = player;
    }

    void printDice() {
        printDice(player.getMyView().getDice(),"You have the following dice:", "You have no dice." );
    }

    void printPlayerStatistics() {
        System.out.printf((char) 27 + "[1m" + "%17s%-10s %-10s" + (char) 27 + "[0m\n", "",
                player.getMyView().getPlayerName(0), player.getMyView().getPlayerName(1));
        System.out.printf((char) 27 + "[1m" + "%-17s" + (char) 27 + "[0m" + "%-10d %-10d\n", "Victory Points",
                player.getMyView().getVictoryPoints(0), player.getMyView().getVictoryPoints(1));
        System.out.printf((char) 27 + "[1m" + "%-17s" + (char) 27 + "[0m" + "%-10d %-10d\n", "Money",
                player.getMyView().getMoney(0), player.getMyView().getMoney(1));
        System.out.println();
    }

    void printField() {
        System.out.printf("Current State of the Field:\n");
        System.out.printf("%18s  ", player.getMyView().getPlayerName(0));
        System.out.printf((char) 27 + "[1m" + "  Dice  " + (char) 27 + "[0m");
        System.out.printf("  %-18s", player.getMyView().getPlayerName(1));
        System.out.print("\n");
        for (Disk d: Disk.diskList()) {
            System.out.printf("%18s  ", emptyIfNull(player.getMyView().getField(0).get(d)));
            System.out.printf((char) 27 + "[1m" + "  %7s  " + (char) 27 + "[0m", d.toString());
            System.out.printf("  %-18s", emptyIfNull(player.getMyView().getField(1).get(d)));
            System.out.print("\n");
        }
        System.out.print('\n');
    }

    void printHand() {
        printCards(player.getMyView().getHand(), "Your hand consists of the following cards:", "You have no cards in your hand.\n");
    }

    void printCards(List<Card> cards, String Message, String emptyMessage) {
        if (cards.size() == 0) {
            System.out.println(emptyMessage);
        } else {
            System.out.println(Message);
            int i = 0;
            for (Card card : cards) {
                System.out.printf((char) 27 + "[1m" + "   %d: " + (char) 27 + "[0m" + "%-18s :" + (char) 27 + "[34m" + " $%d" + (char) 27 + "[0m\n",
                        i + 1, card.toString(), card.getPrice());
                i++;
            }
        }
        System.out.print("\n");
    }

    static String getBanner() {
        return (char) 27 + "[2J" + (char) 27 + "[1;1H" +
                "        RRRRRRRRRRRRRRRRR                                                                  \n" +
                "        R::::::::::::::::R                                            Version 2.0          \n" +
                "        R::::RRRRRRR:::::R                                                                 \n" +
                "        R::::R     R:::::R                                                                 \n" +
                "        R::::R     R:::::R   ooooooooooo      mmmmmmm    mmmmmmm     aaaaaaaaaaaaa         \n" +
                "        R::::R     R:::::R oo:::::::::::oo  mm:::::::m  m:::::::mm   a::::::::::::a        \n" +
                "        R::::RRRRRR:::::R o:::::::::::::::om::::::::::mm::::::::::m  aaaaaaaaa:::::a       \n" +
                "        R:::::::::::::RR  o:::::ooooo:::::om::::::::::::::::::::::m           a::::a       \n" +
                "        R::::RRRRRR:::::R o::::o     o::::om:::::mmm::::::mmm:::::m    aaaaaaa:::::a       \n" +
                "        R::::R     R:::::Ro::::o     o::::om::::m   m::::m   m::::m  aa::::::::::::a       \n" +
                "        R::::R     R:::::Ro::::o     o::::om::::m   m::::m   m::::m a::::aaaa::::::a       \n" +
                "        R::::R     R:::::Ro::::o     o::::om::::m   m::::m   m::::ma::::a    a:::::a       \n" +
                "        R::::R     R:::::Ro:::::ooooo:::::om::::m   m::::m   m::::ma::::a    a:::::a       \n" +
                "        R::::R     R:::::Ro:::::::::::::::om::::m   m::::m   m::::ma:::::aaaa::::::a       \n" +
                "        R::::R     R:::::R oo:::::::::::oo m::::m   m::::m   m::::m a::::::::::aa:::a      \n" +
                "        RRRRRR     RRRRRRR   ooooooooooo   mmmmmm   mmmmmm   mmmmmm  aaaaaaaaaa  aaaa      \n" +
                "\n";
    }

    String getMyBanner() {
        return getBanner() + "\nIt is " + player.getName() + "'s turn\n\n";
    }

    public void printGameState() {
        System.out.println(getMyBanner());

        printField();
        printHand();
        printPlayerStatistics();
        printDice();
    }
    private static String emptyIfNull(Object input){
        if(input == null){
            return "";
        } else {
            return input.toString();
        }
    }

    public void printDisks(List<Disk> disks, String Message, String emptyMessage) {
        if (disks.size() == 0) {
            System.out.println(emptyMessage);
        } else {
            System.out.println(Message);
            int i = 0;
            for (Disk card : disks) {
                System.out.printf((char) 27 + "[1m" + "   %d: " + (char) 27 + "[0m" + "%-18s :" + (char) 27 +"[0m\n",
                        i + 1, card.toString());
                i++;
            }
        }
        System.out.print("\n");
    }
    public void printDice(List<Die> dice, String Message, String emptyMessage) {
        if (dice.size() == 0) {
            System.out.println(emptyMessage);
        } else {
            System.out.println(Message);
            int i = 0;
            for (Die die : dice) {
                System.out.printf((char) 27 + "[1m" + "   %d: " + (char) 27 + "[0m" + "%-18d :" + (char) 27 + "[0m\n",
                        i + 1, die.getDieValue());
                i++;
            }
        }
        System.out.print("\n");
    }
}
