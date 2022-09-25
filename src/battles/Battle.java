package battles;

import UI.Colors;
import droids.Droid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle {
    public static void Attack(Droid attacker, Droid defender, FileWriter log) throws IOException {
        defender.setCurrHealth(defender.getCurrHealth() - attacker.getDamage());
        System.out.println(attacker + " -> " + defender + " - " + " -" + attacker.getDamage() + " HP");
        log.write(attacker + " -> " + defender + " - " + " -" + attacker.getDamage() + " HP\n");

    }

    public static int GetDroid(List<Droid> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.get(i) + " - " + list.get(i).getCurrHealth());
        }

        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static void ShowTeams(ArrayList<Droid> teamA, ArrayList<Droid> teamB) {
        System.out.println(Colors.ANSI_YELLOW + "\nFirst team: " + Colors.ANSI_RESET);
        for (int i = 0; i < teamA.size(); i++) {
            System.out.println(i + ": " + teamA.get(i) + " - " + teamA.get(i).getCurrHealth() + " HP");
        }

        System.out.println(Colors.ANSI_YELLOW + "\nSecond team: " + Colors.ANSI_RESET);
        for (int i = 0; i < teamB.size(); i++) {
            System.out.println(i + ": " + teamB.get(i) + " - " + teamB.get(i).getCurrHealth() + " HP");
        }
    }
}
