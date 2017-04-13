import java.util.*;
import java.awt.Graphics;
public final class AnimationQueue
{
    private static Queue<Tuple<Animateable,Integer>> animationQueue;
    private AnimationQueue()
    {
        animationQueue = new  LinkedList<Tuple<Animateable,Integer>>();
    }

    public static void initialize()
    {
        animationQueue = new  LinkedList<Tuple<Animateable,Integer>>();
    }

    public static void addAnimation(Animateable clip)
    {
        for(int i = 0; i<clip.getFrames();i++)
        {
            animationQueue.add(new Tuple(clip,new Integer(i)));
        }
        World.frame.repaint(); //play animation on add
    }

    public static boolean containsAnimations()
    {
        return !animationQueue.isEmpty();
        //return animationQueue.peek()!=null;
    }

    public static void playClip(Graphics g)
    {
            Tuple<Animateable,Integer> clip = animationQueue.poll();
            clip.x.drawAnimation(g,clip.y);
    }

}
