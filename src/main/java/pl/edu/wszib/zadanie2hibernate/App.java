package pl.edu.wszib.zadanie2hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.wszib.zadanie2hibernate.AppConfiguration.AppConfiguration;
import pl.edu.wszib.zadanie2hibernate.dao.IProductDAO;
import pl.edu.wszib.zadanie2hibernate.services.GUI;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        GUI gui = context.getBean(GUI.class);
        gui.showMenu();
    }
}
