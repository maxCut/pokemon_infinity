import java.awt.Graphics;
import java.awt.image.BufferedImage;
public enum Animations implements Animateable
{
    playerAttacksComputer{
        public void drawAnimation(Graphics g,int frame)
        {
            Battle battleMachine = World.getBattleEmulator();
            Player cha = World.getCharacter();
            BufferedImage playerPokemonBack = cha.getActivePokemon().getBack();
            BufferedImage computerPokemonFront = battleMachine.getComputer().getFront();
                //draw the fighting
                
                battleMachine.fighterLoaderLite(g,cha.getActivePokemon());

                g.drawImage(playerPokemonBack,(int)((54+frame)*World.SCALE),(int)(World.SCALE*153),(int)(100*World.SCALE),(int)(133*World.SCALE),null);//moving player pokemon
                g.drawImage(computerPokemonFront,(int)((245)*World.SCALE),(int)(36*World.SCALE),(int)(100*World.SCALE),(int)(133*World.SCALE),null);

                battleMachine.loadHealthBar(g,cha.getActivePokemon());
                battleMachine.drawSelectorBox(g);
                try{
                    Thread.sleep(10);
                }
                catch(Exception e){}
        }
        public int getFrames()
        {
            return 30;
        }
    }
    

}
