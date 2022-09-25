package UI;

import battles.Battle;
import battles.Duel;
import battles.TeamBattle;
import droids.Archer;
import droids.Droid;
import droids.Knight;
import droids.Sorcerer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainScreen {

    private ArrayList<Droid> userA;
    private ArrayList<Droid> userB;


    public void Startup() throws IOException {
        System.out.println(Colors.ANSI_GREEN + "Welcome to Droid Attack" + Colors.ANSI_RESET);
        while (true) {
            System.out.println("\nСтворити команду - 1");
            System.out.println("Показати команди - 2");
            System.out.println("Розрочати дуель - 3");
            System.out.println("Розпочати битву команда на команду - 4");
            System.out.println("Відтворити файл - 5");
            System.out.println("Завершити гру - 6\n");

            Scanner scan = new Scanner(System.in);
            int input = scan.nextInt();
            switch (input) {
                case 1:
                    CreateTeam();
                    break;
                case 2:
                    ShowTeams();
                    break;
                case 3:
                    PreStartDuel();
                    break;
                case 4:
                    PreStartTeamBattle();
                    break;
                case 5:
                    ReadFile();
                    break;
                case 6:
                    return;
            }
        }
    }

    private void CreateTeam() {
        System.out.print("\nНомер команди: ");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        if (input == 1)
            userA = FormTeam();
        if (input == 2)
            userB = FormTeam();
    }


    private ArrayList<Droid> FormTeam() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Droid> team = new ArrayList<>();
        int i = 0;
        while (true) {
            System.out.println("Виберіть " + (i + 1) + "-ого дройда: ");
            System.out.println("1: Archer - 100 HP - 30 DMG");
            System.out.println("2: Knight - 120 HP - 20 DMG");
            System.out.println("3: Sorcerer - 80 HP - 40 DMG");
            System.out.println("0: Закінчити формувати команду");

            int input = scan.nextInt();
            switch (input) {
                case 1:
                    team.add(new Archer(i + 1));
                    i++;
                    break;
                case 2:
                    team.add(new Knight(i + 1));
                    i++;
                    break;
                case 3:
                    team.add(new Sorcerer(i + 1));
                    i++;
                    break;
                case 0:
                    return team;
            }
        }
    }

    private void ShowTeams() {
        if (userA == null || userB == null) {
            System.out.println("Not all teams are formed");
            return;
        }

        Battle.ShowTeams(userA, userB);
    }

    private void PreStartDuel() throws IOException {
        System.out.println("User1: Select whom to attack with");
        Droid first = userA.get(Battle.GetDroid(userA));
        System.out.println("User2: Select whom to attack with");
        Droid second = userB.get(Battle.GetDroid(userB));

        Duel duel = new Duel(first, second);
        duel.StartDuel();
    }

    private void PreStartTeamBattle() throws IOException {
        TeamBattle teamBattle = new TeamBattle(userA, userB);
        teamBattle.StartBattle();
    }

    private void ReadFile() {
        System.out.println("Name of file to read: ");
        Scanner scanConsole = new Scanner(System.in);
        String fileName = scanConsole.nextLine();
        File file = new File(fileName);
        try {
            System.out.println();
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNextLine()) {
                System.out.println(scanFile.nextLine());
            }
            System.out.println(Colors.ANSI_RED + "\nEnd of file" + Colors.ANSI_RESET);
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading file");
        }
    }
}
