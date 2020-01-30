package pl.edu.wszib.zadanie2hibernate.AppConfiguration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.zadanie2hibernate.dao.IProductDAO;
import pl.edu.wszib.zadanie2hibernate.dao.impl.ProductDAOimpl;
import pl.edu.wszib.zadanie2hibernate.services.GUI;
import pl.edu.wszib.zadanie2hibernate.services.impl.GUIimpl;

@Configuration
public class AppConfiguration {

    @Bean
    public SessionFactory hibernateSessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }

    @Bean
    IProductDAO productDAO(SessionFactory sessionFactory){
        return new ProductDAOimpl(sessionFactory);
    }

    @Bean
    GUI gui(IProductDAO productDAO){
        return new GUIimpl(productDAO);
    }
}
