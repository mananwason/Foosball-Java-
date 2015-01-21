package GameComponents;

import java.util.Random;
public class Coin 
{
    public static String sideUp;
    private int hcount;
    private int tcount;
    public static final String HEADS = "HEAD";
    public static final String TAILS = "TAIL";
    public int choice;
    public Coin()
    {
        this.sideUp = HEADS;
        this.hcount = 0;
        this.tcount = 0;
    }

    public void toss()
    {
        Random rand = new Random();
        choice = rand.nextInt(2);
        if (choice == 0) 
            sideUp = HEADS;
        else
            sideUp = TAILS;    
    }

    public String getsideup()
    {
        System.out.println(sideUp);
        return sideUp;
    }

}