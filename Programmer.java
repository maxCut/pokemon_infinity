import java.util.ArrayList;
import java.awt.image.BufferedImage;
public class Programmer
{
    int brainPower;
    int ALERTNESS;
    final BufferedImage FRONTSPRITE;
    final BufferedImage BACKSPRITE;
    String name;
    int exp;
    int level;
    int curentAlertness;
    ArrayList<MovePP> moveset;
    final String NAME;
    boolean awake;
    public Programmer(WildProgrammer wP, int l)
    {
        curentAlertness = wP.getAlertness();
        exp = 0;
        NAME = wP.getName();
        brainPower = wP.getBrainPower();
        ALERTNESS = wP.getAlertness();
        FRONTSPRITE = wP.getFront();
        BACKSPRITE = wP.getBack();
        level = l;
        moveset = new ArrayList<MovePP>();
        addMove(wP.m1());
        addMove(wP.m2());
        addMove(wP.m3());
        addMove(wP.m4());
        awake = true;
    }
    
    
    public boolean isAwake()
    {
        return awake;
    }
    
    public void addMove(Move m)
    {
        moveset.add(new MovePP(m));
    }
    
    public void levelUp()
    {
        if(exp >= 10000)
        {
            level++;
            brainPower+=10;
            ALERTNESS+=10;
            exp = 0;
        }
    }
    
    public void gainEXP(int x)
    {
        exp += x;
        levelUp();
    }
    
    public void takeHit(int d)
    {
        curentAlertness -= d;
        if(curentAlertness <= 0)
        {
            awake = false;
            curentAlertness = 0;
        }
    }
    
    public void healSelf(MovePP m)
    {
        curentAlertness += m.getHeal();
        if(curentAlertness>ALERTNESS)
        {
            curentAlertness=ALERTNESS;
        }
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public int getDamageDone(MovePP m)
    {
        return (int)(m.getDamage()*brainPower);
    }
    
    public int getCurentHealth()
    {
        return curentAlertness;
    }
    
    public int getMaxHealth()
    {
        return ALERTNESS;
    }
    
    public double getHealthPercent()
    {
        return 1.0*curentAlertness/ALERTNESS;
    }
    
    public BufferedImage getFront()
    {
        return FRONTSPRITE;
    }
    
    public BufferedImage getBack()
    {
        return BACKSPRITE;
    }
    
    public String getName()
    {
        return NAME;
    }
    
    public void fullHeal()
    {
        for(MovePP m : moveset)
        {
            m.fullHeal();
        }
        
        awake = true;
        
        curentAlertness = ALERTNESS;
    }
    
    public ArrayList<MovePP> getMoves()
    {
        return moveset;
    }
    
    
}
