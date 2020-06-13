package DealOrNoDealGUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
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
        try
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
            session.close();
        }
        catch (ConstraintViolationException e)
        {
            System.out.println("Username already exists!");
        }
    }
    
    public boolean checkLogin(String un, String pw)
    {
        Session session = sessionFactory.openSession();
        try
        {
            Player checkPlayerDetails = (Player)session.get(Player.class, un);
            if(checkPlayerDetails.getPassword().equals(pw))
            {
                System.out.println("Login success");
                session.close();
                return true;
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
            System.out.println("Player: " + un + " does not exist");
            session.close();
            return false;
        }   
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
    
//Initial JDBC
//    private static final String USERNAME = "pdc";
//    private static final String PASSWORD = "pdc";
//    private static final String URL = "jdbc:derby://localhost:1527/UserDB;create=true";
//    Connection conn;
//    
//    public Database()
//    {
//        this.dbSetup();
//    }
//    
//    public void dbSetup()
//    {
//        try
//        {
//            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        }
//        catch (SQLException e)
//        {
//            
//        }
//        
//    }
    
    public static void main(String[] args)
    {
//        Database db = new Database();
//        System.out.println(db.conn);
    }
}
