package droids;

public class Droid {
    protected String Name;
    protected int Health = 100;
    protected int Damage = 30;

    public Droid(String name, int health, int damage) {
        Name = name;
        Health = health;
        Damage = damage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }
}
