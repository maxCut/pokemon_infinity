
/**
 * Enumeration class Moves - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Move
{
    Cuddle(10,.5,10,"Cuddle"),
    Nap(5,0,60,"Nap"),
    WKP(5,1,0,"WKP"),
    JavaDocs(20,.6,0,"JavaDocs"),
    SingAlong(10,0,0,"Sing Along"),
    Procrasitinate(10,0,0,"Procrasitinate"),
    nullPointerException(5,.9,0,"nullPointerException"),
    CompileTimeError(10,.8,0,"CompileTimeError"),
    Bite(10,.8,0,"Bite"), 
    StealChair(20,.5,0,"Steal Chair"),
    MakeWaffles(5,0,100,"Make Waffles"),
    NodeJs(9,.5,40,"Node JS"),
    Row(5,.5,0,"Row"),
    TwinHit(9,.6,0, "Twin Hit");
    
    
    private String name;
    private int pp;
    private double damage;
    private int heal;
    
    Move(int p , double d, int h,String n)
    {
        pp = p; 
        damage = d;
        heal = h;
        name = n;
    }
    String getName()
    {
        return name;
    }
    
    double getDamage()
    {
        return damage;
    }
    
    int getHeal()
    {
        return heal;
    }
    
    int getPP()
    {
        return pp;
    }
}
