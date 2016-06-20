public class Seed
{
    final int randomDeviationAspect = 20;
    final int treeOdds = 5;
    final int tallGrassOdds = 7;
    final int seedRange = 4000;
    final int seedShift = 5000;
    final int bigPrimeOne = 4759;
    final int bigPrimeTwo = 3719;
    int seedGen;
    public Seed()
    {
        seedGen = (int)(Math.random()*seedRange)+seedShift;
        System.out.println(seedGen);
    }
    
    public tileType getTile(int x, int y)
    {

        x/=40;
        y/=40;

        int randomGened = (bigPrimeOne*x-1)%seedGen + 7*(bigPrimeTwo*y-23)%(seedGen+3*(x/5)+7*(y/5));
        
        //sudo random aspect
        int rando = randomGened%randomDeviationAspect;
        if (rando == 0)
            return tileType.shortGrass;
        if (rando == 1)
            return tileType.tallGrass;
        if (rando == 2)
            return tileType.tree;


        if (rando<treeOdds)
            return tileType.tree;
        if (rando<tallGrassOdds)
            return tileType.tallGrass;
        return tileType.shortGrass;
    }
} 
