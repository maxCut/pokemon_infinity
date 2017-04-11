
public class MovePP
{

    private final int Max_pp;
    private final double damage;
    private final int heal;
    private final String name; 

    private int pp; 

    public MovePP(Move m)
    {
        name = m.getName();
        Max_pp = m.getPP();
        damage = m.getDamage();
        heal = m.getHeal();

        pp = Max_pp;
    }

    public boolean canUse()
    {
        return true;//pp>0;
    }

    public void use()
    {
        if(pp>0)
        {
            pp--;
        }
    }

    public void fullHeal()
    {
        pp = Max_pp;
    }

    public String getName()
    {
        return name;
    }

    public double getDamage()
    {
        return damage;
    }

    public int getHeal()
    {
        return heal;
    }

    public int getMaxPP()
    {
        return Max_pp;
    }

    public int getPP()
    {
        return pp;
    }

}
