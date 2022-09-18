package droids;

public class Droid {
    protected String Name;
    protected int currHealth = 100;
    protected int Damage = 30;

    protected int defaultHealth = 100;

    public Droid(String name, int health, int damage) {
        Name = name;
        currHealth = health;
        defaultHealth = health;
        Damage = damage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



    public int getDamage() {
        return Damage;
    }

    public int getCurrHealth() {
        return currHealth;
    }

    public void setCurrHealth(int currHealth) {
        this.currHealth = currHealth;
    }

    @Override
    public String toString() {
        return Name;
    }

    public void ResetHealth(){
        currHealth = defaultHealth;
    }

    //TODO: Список предметів
}
