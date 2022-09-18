import battles.Duel;
import droids.Knight;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(6);
        System.out.println(list.size());
        list.remove(1);
        list.remove(0);
        System.out.println(list.size());
    }
}