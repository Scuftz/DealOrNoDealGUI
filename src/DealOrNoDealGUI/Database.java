package DealOrNoDealGUI;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;

/**
 * PDC Assignment 2
 * This is the Database Class
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class Database 
{
    /**
     * Variables
     */
    protected SessionFactory sessionFactory;
    protected Configuration configuration;
    protected ServiceRegistry serviceRegistry;
    
    /**
     * Constructor
     */
    public Database()
    {
        configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
    }
    
    /**
     * This method adds a player to the database
     * @param player   A player to be added to the database
     */
    public void addPlayerToDB(Player player)
    {   
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        session.close();
        System.out.println("Player Added to DB!");        
    }
    
    /**
     * This method checks the player's login attempt
     * @param un
     * @param pw
     * @return 
     */
    public boolean checkLogin(String un, String pw)
    {
        Session session = sessionFactory.openSession();
        Player checkPlayer;
        try
        {
            checkPlayer = (Player)session.get(Player.class, un);
            if(checkPlayer.getPassword().equals(pw))
            {
                System.out.println("Login Success"); //Matching username + password
                session.close();
            }
            else
            {
                System.out.println("Incorrect Password"); //Username exists, password not matching
                session.close();
                return false;
            }
        }
        catch (NullPointerException ex) //If player doesn't exist in the DB, create new player
        {
            System.out.println("Adding new player");
            checkPlayer = new Player(un, pw);
            session.close();
            addPlayerToDB(checkPlayer);
        }   
        return true;
    }
    
    /**
     * This method gets the all time high score of the game
     * @return   The highest score that exists in the DB
     */
    public int getAllTimeHighScore()
    {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Player.class).setProjection(Projections.max("highscore"));
        Integer topScore = (Integer)criteria.uniqueResult();
        session.close();
        return topScore;
    }
    
    /**
     * This method gets an individual player's high score
     * @param username   Name of player to be searched
     * @return   The player's high score
     */
    public int getPlayerHighScore(String username)
    {
        Session session = sessionFactory.openSession();
        Player playerRetrieval = (Player)session.get(Player.class, username);
        session.close();
        return playerRetrieval.getHighscore();
    }
    
    /**
     * This method updates a player's score
     * @param username   Username of player
     * @param score   The score they just got from playing the game
     */
    public void updateScore(String username, int score)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Player updatingPlayer = (Player)session.get(Player.class, username);
        updatingPlayer.setHighscore(score);
        session.update(updatingPlayer);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * This method closes the session factory upon quitting the game
     */
    public void closeDatabase()
    {
        this.sessionFactory.close();
    }
}