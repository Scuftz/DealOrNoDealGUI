package DealOrNoDealGUI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author shivn
 */
public class Database 
{
    protected SessionFactory sessionFactory;
    protected Configuration configuration;
    protected ServiceRegistry serviceRegistry;
    
    public Database()
    {
        configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
    }
    
    public void addPlayerToDB(Player player)
    {   
        System.out.println("Adding Player to DB! " + player.getUsername() + ", " + player.getPassword());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        session.close();
        System.out.println("Player Added to DB! " + player.getUsername() + ", " + player.getPassword());
    }
    
    public boolean checkLogin(String un, String pw)
    {
        Session session = sessionFactory.openSession();
        Player checkPlayer;
        try
        {
            checkPlayer = (Player)session.get(Player.class, un);
            if(checkPlayer.getPassword().equals(pw))
            {
                System.out.println("Login success");
                session.close();
            }
            else
            {
                System.out.println("Incorrect Password");
                session.close();
                return false;
            }
        }
        catch (NullPointerException ex)
        {
            System.out.println("Adding new player");
            checkPlayer = new Player(un, pw);
            session.close();
            addPlayerToDB(checkPlayer);
        }   
        return true;
    }
    
    public int getPlayerHighScore(Player player)
    {
        Session session = sessionFactory.openSession();
        Player playerRetrieval = (Player)session.get(Player.class, player.getUsername());
        session.close();
        return playerRetrieval.getHighscore();
    }
    
    public void updateScore(Player player, int score)
    {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
//            Player player = (Player)session.get(Player.class, "Leo"); //null pointer except if player dont exist
            Player updatingPlayer = (Player)session.get(Player.class, player.getUsername());
            updatingPlayer.setHighscore(score);
            session.update(updatingPlayer);
            session.getTransaction().commit();
            session.close();
    }
    
    public void closeDatabase()
    {
        this.sessionFactory.close();
    }
}