package battles;

import droids.Droid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Battle {
    public static void Attack(Droid attacker, Droid defender, FileWriter log) throws IOException {
        defender.setCurrHealth(defender.getCurrHealth() - attacker.getDamage());
        System.out.println(attacker + " -> " + defender + " - " + " -" + attacker.getDamage() + " HP");
        log.write(attacker + " -> " + defender + " - " + " -" + attacker.getDamage() + " HP");

    }

    public static Droid GetDroid(List<Droid> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.get(i) + " - " + list.get(i).getCurrHealth());
        }

        Scanner scan = new Scanner(System.in);
        return list.get(scan.nextInt());
    }
}
