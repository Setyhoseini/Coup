package Logic.Game;

import GUI.Card;
import Logic.Player.BotType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class Loader {
    static GsonBuilder gsonBuilder= new GsonBuilder();

    public static void saveTypes(BotType bot2, BotType bot3, BotType bot4) {
        Vector<BotType> botTypes = new Vector<>();
        botTypes.add(bot2);
        botTypes.add(bot3);
        botTypes.add(bot4);
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String botJson = gson.toJson(botTypes);
        try {
            FileWriter writer = new FileWriter(System.getProperty("user.dir")+"//src//"+ "botTypes" +".txt");
            writer.write(botJson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector loadList() {
        try {
            File file = new File(System.getProperty("user.dir") + "//src//" + "playersCards" + ".txt");
            Vector playersCards;
            Scanner scanner = new Scanner(file);
            StringBuilder cardsJson = new StringBuilder();
            while (scanner.hasNext()){
                cardsJson.append(scanner.nextLine());
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            playersCards = gson.fromJson(cardsJson.toString(),Vector.class);
            return playersCards;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector loadTypes() {
        try {
            File file = new File(System.getProperty("user.dir") + "//src//" + "botTypes" + ".txt");
            Vector botTypes;
            Scanner scanner= new Scanner(file);
            StringBuilder botJson = new StringBuilder();
            while (scanner.hasNext()){
                botJson.append(scanner.nextLine());
            }
            GsonBuilder gsonBuilder= new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            botTypes = gson.fromJson(botJson.toString(),Vector.class);
            return botTypes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector<BotType> types() {
        Vector<BotType> types = new Vector<>();
        Vector loadedTypes = loadTypes();
        Vector<String> names = new Vector<>();
        names.add(String.valueOf(Objects.requireNonNull(loadedTypes).get(0)));
        names.add(String.valueOf(loadedTypes.get(1)));
        names.add(String.valueOf(loadedTypes.get(2)));
        for (String s : names) {
            switch (s) {
                case "Paranoid":
                    types.add(BotType.Paranoid);
                    break;
                case "Nerd":
                    types.add(BotType.Nerd);
                    break;
                case "Cautious_Assassin":
                    types.add(BotType.Cautious_Assassin);
                    break;
                case "Coup_Lover":
                    types.add(BotType.Coup_Lover);
                    break;
            }
        }
        return types;
    }

    public static Vector<Card> cards() {
        Vector<Card> finalList = new Vector<>();
        Vector<String> load = new Vector<>();
        Vector names = loadList();
        load.add(String.valueOf(Objects.requireNonNull(names).get(0)));
        load.add(String.valueOf(Objects.requireNonNull(names).get(1)));
        load.add(String.valueOf(Objects.requireNonNull(names).get(2)));
        load.add(String.valueOf(Objects.requireNonNull(names).get(3)));
        load.add(String.valueOf(Objects.requireNonNull(names).get(4)));
        load.add(String.valueOf(Objects.requireNonNull(names).get(5)));
        load.add(String.valueOf(Objects.requireNonNull(names).get(6)));
        load.add(String.valueOf(Objects.requireNonNull(names).get(7)));
        for (String s : load) {
            switch (s) {
                case "Ambassador":
                    finalList.add(Card.Ambassador);
                    break;
                case "Assassin":
                    finalList.add(Card.Assassin);
                    break;
                case "Contessa":
                    finalList.add(Card.Contessa);
                    break;
                case "Duke":
                    finalList.add(Card.Duke);
                    break;
                case "Captain":
                    finalList.add(Card.Captain);
                    break;
            }
        }
        return finalList;
    }
}