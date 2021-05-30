import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author (your name here)
 * @version 0.1
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }

    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
        setMemory(5);
        setFlag(1, false);
        setFlag(2, true);
    }
    

    /**
     * Do what a greep's gotta do.
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().
        if (carryingTomato()) {
            if(atShip()) {
                dropTomato();
                setFlag(1, false);
            }
            else {
                //(int)(Math.random() * 20) + 
                if(!(atWater() || atWorldEdge())){
                    if(getMemory() > 0){
                      setMemory(getMemory() - 1);
                      turn(-(4 + getMemory() * 3));
                    }
                    else
                      turnHome();
                }
                else if((atWater() || atWorldEdge())){
                    turn((int)(Math.random() * 10) + 65 + getMemory());
                    setMemory(6);
                }
                move();
            }
        }
        else {
            checkFood();
            if((atWater() || atWorldEdge())){
                 turn((getFlag(2)?1:-1)*((int)(Math.random() * 65) + 90));
                 setFlag(2, !getFlag(2));
            }
            if(!getFlag(1)){
              move();
              setMemory(6);
            }
        }
    }

    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if(tomatoes != null) {
            loadTomato();
            if(getMemory() > 0){
              move();
              setMemory(getMemory() - 1);
            } else {
                turn(60);
                move();
            }
            setFlag(1, true);
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
        else
          setFlag(1, false);
    }


    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Anonymous";  // write your name here!
    }


    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if(carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}