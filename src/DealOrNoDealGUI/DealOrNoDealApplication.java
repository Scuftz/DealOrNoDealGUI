package DealOrNoDealGUI;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DealOrNoDealApplication
{
    public static void main(String[] args)
    {
//        Player player = new Player("newuser", "pass");
//        Model model = new Model();
//        View view = new View();
//        Controller controller = new Controller(model, view);
//        model.addObserver(view);
//        view.setVisible(true);

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
    
        try
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Player player = (Player)session.get(Player.class, "Leo"); //null pointer except if player dont exist
            
            player.setScore(420);
            session.update(player);
//            session.save(player);
            session.getTransaction().commit();
            session.close();
        }
        catch (ConstraintViolationException e)
        {
            System.out.println("Username already exists!");
        }
//        BasicConfigurator.configure();
        
//    
//        
        
//        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(p2);
//        session.getTransaction().commit();
//        session.close();
//        sessionFactory.close();
//        System.out.println("it runs");
    }
}
