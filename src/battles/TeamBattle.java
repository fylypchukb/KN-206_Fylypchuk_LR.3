package battles;

import UI.Colors;
import droids.Droid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TeamBattle {
    private final ArrayList<Droid> teamA;
    private final ArrayList<Droid> teamB;
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
            if (i < toA) {
                int swapIndexA = r.nextInt(toA - from) + from;
                Collections.swap(teamA, i, swapIndexA);
            }
            if (i < toB) {
                int swapIndexB = r.nextInt(toB - from) + from;
                Collections.swap(teamB, i, swapIndexB);
            }
        }

        Battle.ShowTeams(teamA, teamB);
    }

    private void BattleOrder() throws IOException {
        int a = 0, b = 0;
        while (true) {
            System.out.print(Colors.ANSI_YELLOW + "\nPlayer 1 " + Colors.ANSI_RESET);
            AttackProc(a, teamA, teamB);
            if (teamB.size() == 0) {
                System.out.println(Colors.ANSI_GREEN + "Player 1 has won" + Colors.ANSI_RESET);
                BattleLog.write("Player 1 has won");
                break;
            }

            System.out.println(Colors.ANSI_CYAN + "\nPlayer 2 " + Colors.ANSI_RESET);
            AttackProc(b, teamB, teamA);
            if (teamA.size() == 0) {
                System.out.println(Colors.ANSI_GREEN + "Player 2 has won" + Colors.ANSI_RESET);
                BattleLog.write("Player 2 has won");
                break;
            }

            a++;
            if (a >= teamA.size())
                a = 0;

            b++;
            if (b >= teamB.size())
                b = 0;
        }
    }

    private void AttackProc(int index, ArrayList<Droid> attacker, ArrayList<Droid> defender) throws IOException {
        if (index >= attacker.size())
            index = 0;

        System.out.print(Colors.ANSI_CYAN + "with " + attacker.get(index) + ": " + Colors.ANSI_RESET);
        int attackIndex = SelectAttack(defender);
        Battle.Attack(attacker.get(index), defender.get(attackIndex), BattleLog);
        if (defender.get(attackIndex).getCurrHealth() <= 0) {
            defender.remove(attackIndex);
        }
    }

    private int SelectAttack(List<Droid> toAttack) {
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
        LocalDateTime now = LocalDateTime.now();

        String name = "E:\\Projects\\Test\\TeamBattle_" + dtf.format(now) + ".txt";
        file = new File(name);
        file.createNewFile();
        BattleLog = new FileWriter(file);
        BattleLog.write("Player 1: ");
        for (Droid item : teamA) {
            BattleLog.write(item.toString() + "\n");
        }
        BattleLog.write("Player 2: ");
        for (Droid item : teamB) {
            BattleLog.write(item.toString() + "\n");
        }

        BattleLog.write("\nTeamBattle: \n");
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
