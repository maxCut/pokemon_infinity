import java.util.*;
import java.awt.Graphics;
public final class AnimationQueue
{
    private static Queue<Animateable> animationQueue;
    private AnimationQueue()
    {
        animationQueue = new  LinkedList<Animateable>();
    }

    public static void initialize()
    {
        animationQueue = new  LinkedList<Animateable>();
    }

    public static void addAnimation(Animateable clip)
    {
        animationQueue.add(clip);
    }

    public static boolean containsAnimations()
    {
        return !animationQueue.isEmpty();
        //return animationQueue.peek()!=null;
    }

    public static void playAllInQueue(Graphics g)
    {
        while(containsAnimations())
        {
            Animateable clip = animationQueue.poll();
            clip.drawAnimation(g);
        }
    }

}
