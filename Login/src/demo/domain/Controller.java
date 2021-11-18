package demo.domain;

import demo.database.FileHandler;
import demo.ui.UserInterface;

import java.io.IOException;

public class Controller {
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private FileHandler fileHandler = new FileHandler();
    public void start() throws IOException {

        while(isRunning){
            fileHandler.loadUsers();
            ui.menu();
            switch (ui.userInput()){
                case "0" -> exit();
                case "1" -> createUser();
                case "2" -> logIn();
                }
        }
    }

    public void createUser() throws IOException {
        ui.printMessage("Type in user name: ");
        String name = ui.userInput();
        ui.printMessage("Type in password: ");
        String password = ui.userInput();
        User user = new User(name, password);
        fileHandler.addUser(user);
        fileHandler.saveUser(user);
    }

    public void logIn() {
        ui.printMessage("Type in user name: ");
        String name = ui.userInput();
        ui.printMessage("Type in password: ");
        String password = ui.userInput();
        User user = fileHandler.findUser(name, password);
        if(user != null){
            ui.printMessage("Welcome user " + name + " " +  user.getRole());
        } else {
            ui.printMessage("User not found");
        }
    }

    public void exit(){
        isRunning = false;
    }
}
