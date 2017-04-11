import java.awt.Graphics;
import java.awt.image.BufferedImage;
public enum Animations implements Animateable
{
    playerAttacksComputer{
        public void drawAnimation(Graphics g)
        {
            Battle battleMachine = World.getBattleEmulator();
            Player cha = World.getCharacter();
            BufferedImage playerPokemonBack = cha.getActivePokemon().getBack();
            BufferedImage computerPokemonFront = battleMachine.getComputer().getFront();
            for(int i = 0; i <30; i++)
            {
                //draw the fighting
                
                battleMachine.fighterLoaderLite(g,cha.getActivePokemon());

                g.drawImage(playerPokemonBack,54+i,153,null);//moving player pokemon
                g.drawImage(computerPokemonFront,245,36,null);

                battleMachine.loadHealthBar(g,cha.getActivePokemon());
                battleMachine.drawSelectorBox(g);
                try{
                    Thread.sleep(10);
                }
                catch(Exception e){}
            }

        }
    }
    

}
