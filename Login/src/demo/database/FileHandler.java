package demo.database;

import demo.domain.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler implements Database {
    private ArrayList<User> users = new ArrayList<>();
    File file = new File("data/users.txt");


/*
    public User findUser(String name, String password){

        //TODO Change later to real code
        return new User("John Smith", "1234");
    }

 */


    public User findUser(String name, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name) && users.get(i).getPassword().equals(password)) {
                return users.get(i);
            }
        }
        return null;
    }


    public void addUser(User user) {
        users.add(user);
    }

    public void saveUser(User user) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        for (int i = 0; i < users.size(); i++) {
            String result = "";
            result += users.get(i).getName();
            result += ";";
            result += users.get(i).getPassword();
            result += ";";
            result += users.get(i).getRole();
            result += ";";
            writer.write(result);
            writer.newLine();
        }
        writer.close();
        System.out.println("Saved");
    }

    public void loadUsers() throws IOException {
        Scanner sc = new Scanner(file);
        sc.useDelimiter(";");
        while (sc.hasNextLine()) {
            String name = sc.next();
            String password = sc.next();
            String role = sc.next();
            User user = new User(name, password);
            addUser(user);
            sc.nextLine();
        }
    }

}
