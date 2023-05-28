package org.example;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final String PATH_TO_NUMBERS = "src/main/resources/numbers.txt";
    private static final String PATH_TO_USERS = "src/main/resources/users.txt";
    private static final String PATH_TO_JSON_FILE = "src/main/resources/users.json";
    public static void main(String[] args) {

    chekCorrectNumberFormat(PATH_TO_NUMBERS);
    convertUsersListToJsonFile(createUsersList(readUsersFromFile(PATH_TO_USERS)));

    }

    public static void chekCorrectNumberFormat(String path){
        File file = new File(path);
        try (FileReader fileReader = new FileReader(file)){

            char[]buf = new char[(int) file.length()];
            fileReader.read(buf);
            String fileContent = new String(buf);
            String[] numbers = fileContent.split("\n");
            for (String number : numbers) {
                if(number.matches("\\(?([0-9]{3})\\)?(-?)([0-9]{3})(-)([0-9]{4})") ||
                        number.matches("\\(?([0-9]{3})\\)( )([0-9]{3})(-)([0-9]{4})")){
                    System.out.println(number);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] readUsersFromFile (String path){
        File file = new File(path);
        String[] users;
        try(FileReader fileReader = new FileReader(file)){
            char[] buf = new char[(int) file.length()];
            fileReader.read(buf);
            String fileContent = new String(buf);
            users = fileContent.split("\n");
            return users;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> createUsersList (String[] usersToRedag){
        List<User> usersList = new ArrayList<>();
        for ( String fields : usersToRedag) {
            String[] usersFields = fields.split(" ");
            User user = new User(usersFields[0],usersFields[1]);
            usersList.add(user);
        }
        return usersList;
    }

    public static void convertUsersListToJsonFile(List<User> usersList){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter writer = new FileWriter(PATH_TO_JSON_FILE)){
            gson.toJson(usersList, writer);
            System.out.println("JSON-file created");
        }catch (IOException e){
            System.err.println("Error in writing to the JSON-file ");
        }


    }
}