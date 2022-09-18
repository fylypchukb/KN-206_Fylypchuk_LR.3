package battles;

import droids.Droid;

public class Battle {
    public static void Attack(Droid attacker, Droid defender){
        defender.setCurrHealth(defender.getCurrHealth() - attacker.getDamage());
    }
}
