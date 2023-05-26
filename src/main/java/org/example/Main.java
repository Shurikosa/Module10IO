package org.example;

import java.io.*;


public class Main {

    private static final String PATH = "/home/oleksandr/IdeaProjects/Module10IO/src/main/resources/file.txt";
    public static void main(String[] args) {

    chekCorrectNumberFormat(PATH);

    }

    public static void chekCorrectNumberFormat(String path){
        File file = new File(PATH);
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
}