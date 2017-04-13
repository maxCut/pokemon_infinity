public class Seed
{
    final int randomDeviationAspect = 20;
    final int treeOdds = 5;
    final int tallGrassOdds = 7;
    final int seedRange = 4000;
    final int seedShift = 5000;
    final int bigPrimeOne = 4759;
    final int bigPrimeTwo = 3719;
    final int townSizeX = 30;
    final int townSizeY = 30;
    int seedGen;
    public Seed()
    {
        seedGen = (int)(Math.random()*seedRange)+seedShift;
        System.out.println(seedGen);
    }
    
    public tileType getTile(int x, int y)
    {

        x/=40;//pixels
        y/=40;//pixels


        int randomGened = (bigPrimeOne*x-1)%seedGen + (bigPrimeTwo*y-1)%seedGen + 7*(bigPrimeTwo*y-23)%(seedGen+3*(x/5)+7*(y/5));
        int randomTowns = (bigPrimeOne*(x/townSizeX)-1)%seedGen+ (bigPrimeTwo*(y/townSizeY)-1)%seedGen + 7*(bigPrimeTwo*(y/townSizeY)-23)%(seedGen+3*((x/townSizeX)/5)+7*((y/townSizeY)/5));

        //calculate towns
        if(randomTowns%15==0) //the block is in a town
        {
            return tileType.shortGrass;//right now it deforests but will place town here
        }

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
