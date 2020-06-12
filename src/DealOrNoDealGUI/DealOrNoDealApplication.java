package DealOrNoDealGUI;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DealOrNoDealApplication
{
    public static void main(String[] args)
    {
//        Model model = new Model();
//        View view = new View();
//        Controller controller = new Controller(model, view);
//        model.addObserver(view);
//        view.setVisible(true);
//        BasicConfigurator.configure();
        
        Player player = new Player();
        player.setUsername("Shiv");
        player.setPassword("shiv");
        player.setScore(200);
    
        SessionFactory sessionFactory;
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
       
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        System.out.println("it runs");
        
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
