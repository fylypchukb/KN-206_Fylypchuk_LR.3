package battles;

import UI.Colors;
import droids.Droid;

import java.util.*;

public class TeamBattle {
    private ArrayList<Droid> teamA;
    private ArrayList<Droid> teamB;

    public TeamBattle(ArrayList<Droid> teamA, ArrayList<Droid> teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public void StartBattle() {
        ReorderDroids();
        BattleOrder();
        FinishBattle();
    }

    private void ReorderDroids() {
        Random r = new Random();
        int from = 0;
        int toA = teamA.size();
        int toB = teamB.size();
        for (int i = 0; i < toA || i < toB; i++) {
            if (i != toA - 1) {
                int swapIndexA = r.nextInt(toA - from) + from;
                Collections.swap(teamA, i, swapIndexA);
            }
            if (i != toB - 1) {
                int swapIndexB = r.nextInt(toB - from) + from;
                Collections.swap(teamB, i, swapIndexB);
            }
        }
    }

    private void BattleOrder() {
        int larger = Math.max(teamA.size(), teamB.size());
        int a = 0, b = 0;
        boolean isADefeated = false, isBDefeated = false;
        while (true) {
            if (teamA.size() == 0) {
                isADefeated = true;
                System.out.println(Colors.ANSI_GREEN + "Player 2 has won" + Colors.ANSI_RESET);
                break;
            }
            if (teamB.size() == 0) {
                isBDefeated = true;
                System.out.println(Colors.ANSI_GREEN + "Player 1 has won" + Colors.ANSI_RESET);
                break;
            }

            System.out.println(Colors.ANSI_YELLOW + "Player 1: " + Colors.ANSI_RESET);
            Battle.Attack(teamA.get(a), SelectAttack(teamB));
            if (teamB.get(b).getCurrHealth() <= 0) {
                teamB.remove(b);
            }

            System.out.println(Colors.ANSI_CYAN + "Player 2: " + Colors.ANSI_RESET);
            Battle.Attack(teamB.get(b), SelectAttack(teamA));
            if (teamA.get(a).getCurrHealth() <= 0) {
                teamA.remove(a);
            }

            a++;
            if (a >= teamA.size())
                a = 0;

            b++;
            if (b >= teamB.size())
                b = 0;
        }
    }

    private Droid SelectAttack(List<Droid> toAttack) {
        System.out.println("Select who to attack");
        for (int i = 0; i < toAttack.size(); i++) {
            System.out.println(i + ": " + toAttack.get(i) + " - " + toAttack.get(i).getCurrHealth());
        }

        Scanner scan = new Scanner(System.in);
        return toAttack.get(scan.nextInt());
    }

    private void FinishBattle() {
        for (Droid item : teamA) {
            item.ResetHealth();
        }
        for (Droid item : teamB) {
            item.ResetHealth();
        }
    }
}
