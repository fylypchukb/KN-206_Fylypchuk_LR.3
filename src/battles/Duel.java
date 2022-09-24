package battles;

import UI.Colors;
import droids.Droid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duel {
    private Droid a;
    private Droid b;

    private File file;
    private FileWriter DuelLog;

    public Duel(Droid a, Droid b) {
        this.a = a;
        this.b = b;
    }

    public void StartDuel() throws IOException {
        System.out.println(Colors.ANSI_RED + "\n" + a + " vs " + b + "" + Colors.ANSI_RESET);
        CreateLog();
        DuelOrder();
        FinishDuel();
    }

    private void DuelOrder() throws IOException {
        while (true) {
            Battle.Attack(a, b, DuelLog);
            if (b.getCurrHealth() <= 0) {
                System.out.println(a + "is a winner!!!");
                DuelLog.write(a + "is a winner");
                break;
            }
            Battle.Attack(b, a, DuelLog);
            if (a.getCurrHealth() <= 0) {
                System.out.println(b + "is a winner!!!");
                DuelLog.write(b + "is a winner");
                break;
            }
        }
    }

    private void FinishDuel() throws IOException {
        a.ResetHealth();
        b.ResetHealth();
        FileSave();

        //Todo: переможець отримує один з предметів програвшого
    }

    private void CreateLog() throws IOException {
        String name = "E:\\Projects\\Test\\Duel_" + java.time.LocalDateTime.now();
        file = new File(name);
        file.createNewFile();
        DuelLog = new FileWriter(file);
        DuelLog.write("Player 1: " + a);

        DuelLog.write("Player 2: " + b);

        DuelLog.write("\nDuel: ");
    }

    private void FileSave() throws IOException {
        DuelLog.close();
        System.out.println("Do you want to save duel log? (Yes/No)");

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
