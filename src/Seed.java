public class Seed
{
    final int randomDeviationAspect = 20;
    final int treeOdds = 5;
    final int tallGrassOdds = 7;
    final int seedRange = 4000;
    final int seedShift = 5000;
    final int bigPrimeOne = 4759;
    final int bigPrimeTwo = 3719;
    final static int townSizeX = 9;
    final static int townSizeY = 10;
    int seedGen;
    final static int xShift = 1000001;
    final static int yShift = 1000001;
    public Seed()
    {
        seedGen = (int)(Math.random()*seedRange)+seedShift;
        System.out.println(seedGen);
    }
    
    public int testRandom(int x, int y)//purely for tests
    {
        
        x+=xShift;
        y+=yShift;

        x/=(int)(40*World.SCALE);//pixels
        y/=(int)(40*World.SCALE);//pixels

        int xBlock = x/townSizeX;//make positive
        int yBlock = y/townSizeY;//find a better way to do this

        int randomTowns = ((seedGen*bigPrimeOne*xBlock-1)%bigPrimeTwo+bigPrimeTwo)%bigPrimeTwo 
            + ((seedGen*bigPrimeTwo*yBlock-1)%bigPrimeOne +bigPrimeOne)%bigPrimeOne
            + ((seedGen*bigPrimeOne*xBlock-1)%bigPrimeTwo+bigPrimeTwo)*((seedGen*bigPrimeTwo*yBlock-1)%bigPrimeOne +bigPrimeOne);

        return randomTowns%13;
    }
    public tileType getTile(int x, int y)
    {

        x+=xShift;//make positive
        y+=yShift;//TODO find a better way to do this

        x/=(int)(40*World.SCALE);//pixels
        y/=(int)(40*World.SCALE);//pixels
        
        int randomGened = ((seedGen*bigPrimeOne*x-1)%bigPrimeTwo+bigPrimeTwo)%bigPrimeTwo 
            + ((seedGen*bigPrimeTwo*y-1)%bigPrimeOne +bigPrimeOne)%bigPrimeOne
            + ((seedGen*bigPrimeOne*x-1)%bigPrimeTwo+bigPrimeTwo)*((seedGen*bigPrimeTwo*y-1)%bigPrimeOne +bigPrimeOne);

        
        int xBlock = x/townSizeX;
        int yBlock = y/townSizeY;
        int randomTowns = ((seedGen*bigPrimeOne*xBlock-1)%bigPrimeTwo+bigPrimeTwo)%bigPrimeTwo 
            + ((seedGen*bigPrimeTwo*yBlock-1)%bigPrimeOne +bigPrimeOne)%bigPrimeOne
            + ((seedGen*bigPrimeOne*xBlock-1)%bigPrimeTwo+bigPrimeTwo)*((seedGen*bigPrimeTwo*yBlock-1)%bigPrimeOne +bigPrimeOne);


        //calculate towns

        //adjacent towns?
        //above and to the left
        int xABlock = xBlock-1;
        int yABlock = yBlock-1;
        int adjTowns = ((seedGen*bigPrimeOne*xABlock-1)%bigPrimeTwo+bigPrimeTwo)%bigPrimeTwo 
            + ((seedGen*bigPrimeTwo*yABlock-1)%bigPrimeOne +bigPrimeOne)%bigPrimeOne
            + ((seedGen*bigPrimeOne*xABlock-1)%bigPrimeTwo+bigPrimeTwo)*((seedGen*bigPrimeTwo*yABlock-1)%bigPrimeOne +bigPrimeOne);

        boolean adjacentTowns = adjTowns%13==1||(xABlock==1111&&yABlock==1000);
        //above directly
        xABlock++;
        adjTowns = ((seedGen*bigPrimeOne*xABlock-1)%bigPrimeTwo+bigPrimeTwo)%bigPrimeTwo 
            + ((seedGen*bigPrimeTwo*yABlock-1)%bigPrimeOne +bigPrimeOne)%bigPrimeOne
            + ((seedGen*bigPrimeOne*xABlock-1)%bigPrimeTwo+bigPrimeTwo)*((seedGen*bigPrimeTwo*yABlock-1)%bigPrimeOne +bigPrimeOne);
        adjacentTowns=adjacentTowns||(adjTowns%13==1||(xABlock==1111&&yABlock==1000));


        //above to the right 
        xABlock++;
        adjTowns = ((seedGen*bigPrimeOne*xABlock-1)%bigPrimeTwo+bigPrimeTwo)%bigPrimeTwo 
            + ((seedGen*bigPrimeTwo*yABlock-1)%bigPrimeOne +bigPrimeOne)%bigPrimeOne
            + ((seedGen*bigPrimeOne*xABlock-1)%bigPrimeTwo+bigPrimeTwo)*((seedGen*bigPrimeTwo*yABlock-1)%bigPrimeOne +bigPrimeOne);
        adjacentTowns=adjacentTowns||(adjTowns%13==1||(xABlock==1111&&yABlock==1000));

        //to the right 
        yABlock++;
        adjTowns = ((seedGen*bigPrimeOne*xABlock-1)%bigPrimeTwo+bigPrimeTwo)%bigPrimeTwo 
            + ((seedGen*bigPrimeTwo*yABlock-1)%bigPrimeOne +bigPrimeOne)%bigPrimeOne
            + ((seedGen*bigPrimeOne*xABlock-1)%bigPrimeTwo+bigPrimeTwo)*((seedGen*bigPrimeTwo*yABlock-1)%bigPrimeOne +bigPrimeOne);
        adjacentTowns=adjacentTowns||(adjTowns%13==1||(xABlock==1111&&yABlock==1000));

        if((randomTowns%13==1 && !adjacentTowns)||(xBlock==1111&&yBlock==1000)) //the block is in a town
        {
            return tileType.viridianCity;//place a town here
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
