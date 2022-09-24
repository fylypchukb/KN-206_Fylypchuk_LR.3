package battles;

import UI.Colors;
import droids.Droid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TeamBattle {
    private ArrayList<Droid> teamA;
    private ArrayList<Droid> teamB;
    private FileWriter BattleLog;
    private File file;

    public TeamBattle(ArrayList<Droid> teamA, ArrayList<Droid> teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public void StartBattle() throws IOException {
        ReorderDroids();
        CreateLog();
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

    private void BattleOrder() throws IOException {
        int a = 0, b = 0;
        boolean isADefeated = false, isBDefeated = false;
        while (true) {
            if (teamA.size() == 0) {
                isADefeated = true;
                System.out.println(Colors.ANSI_GREEN + "Player 2 has won" + Colors.ANSI_RESET);
                BattleLog.write("Player 2 has won");
                break;
            }
            if (teamB.size() == 0) {
                isBDefeated = true;
                System.out.println(Colors.ANSI_GREEN + "Player 1 has won" + Colors.ANSI_RESET);
                BattleLog.write("Player 1 has won");
                break;
            }

            System.out.println(Colors.ANSI_YELLOW + "Player 1: " + Colors.ANSI_RESET);
            Battle.Attack(teamA.get(a), SelectAttack(teamB), BattleLog);
            if (teamB.get(b).getCurrHealth() <= 0) {
                teamB.remove(b);
            }

            System.out.println(Colors.ANSI_CYAN + "Player 2: " + Colors.ANSI_RESET);
            Battle.Attack(teamB.get(b), SelectAttack(teamA), BattleLog);
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
        return Battle.GetDroid(toAttack);
    }

    private void FinishBattle() throws IOException {
        for (Droid item : teamA) {
            item.ResetHealth();
        }
        for (Droid item : teamB) {
            item.ResetHealth();
        }
        FileSave();
    }

    private void CreateLog() throws IOException {
        String name = "E:\\Projects\\Test\\TeamBattle_" + java.time.LocalDateTime.now();
        file = new File(name);
        file.createNewFile();
        BattleLog = new FileWriter(file);
        BattleLog.write("Player 1: ");
        for (Droid item : teamA) {
            BattleLog.write(item.toString());
        }
        BattleLog.write("Player 2: ");
        for (Droid item : teamB) {
            BattleLog.write(item.toString());
        }

        BattleLog.write("\nTeamBattle: ");
    }

    private void FileSave() throws IOException {
        BattleLog.close();
        System.out.println("Do you want to save battle log? (Yes/No)");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equals("No")) {
            file.delete();
        }
        if (input.equals("Yes")) {
            System.out.println("Файл збережено у " + file.getPath());
        }
    }
}
