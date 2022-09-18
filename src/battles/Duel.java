package battles;

import UI.Colors;
import droids.Droid;

public class Duel {
    private Droid a;
    private Droid b;

    public Duel(Droid a, Droid b) {
        this.a = a;
        this.b = b;
    }

    public void StartDuel() {
        System.out.println(Colors.ANSI_RED + "\n" + a + " vs " + b + "" + Colors.ANSI_RESET);
        DuelOrder();
        FinishDuel();
    }

    private void DuelOrder(){
        while(true){
            Battle.Attack(a, b);
            if(b.getCurrHealth()<=0)
                break;
            Battle.Attack(b,a);
            if(a.getCurrHealth()<=0)
                break;
        }
    }

    private void FinishDuel(){
        a.ResetHealth();
        b.ResetHealth();

        //Todo: переможець отримує один з предметів програвшого
    }
}
