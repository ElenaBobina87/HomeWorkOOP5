package notebook.view;


import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.mapper.impl.UserMapper;

import java.util.Scanner;

public class UserView {
    private final UserController userController;
    //private final Controller controller;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            UserMapper um = new UserMapper();
            switch (com) {
                case DELETE:
                    String userID = prompt("Enter user id: ");
                    userController.deleteUser(userID);
                    break;
                case LIST:
                    System.out.println(userController.readAll());
                    break;
                case CREATE:
                    User u = um.createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, um.createUser());
            }
        }
    }

    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    
}
