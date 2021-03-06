package launcher;

import database.Boostraper;
import model.User;
import view.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static boolean BOOTSTRAP = false;

    public static User user;

    public static void main(String[] args) {
        bootstrap();

        ComponentFactory componentFactory = ComponentFactory.instance(false);
        componentFactory.getLoginView().setVisible();


    }


    private static void bootstrap() {
        if (BOOTSTRAP) {
            try {
                new Boostraper().execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
