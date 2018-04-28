package de.tetrisiq;


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Config file not found!\n");
        }
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        BufferedReader br;
        List<String> settings = new ArrayList<String>();
        Bot bot = new Bot();
        try {
            br = new BufferedReader(new FileReader(args[0]));

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                settings.add(sCurrentLine);
            }

            bot.setToken(settings.get(0));
            bot.setBotNick(settings.get(1));
            bot.setdocPath(settings.get(2));
            bot.setLogfile();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("TelegramBot Started\n");

        bot.sendMessage("Build Job Finish", settings.get(settings.size() - 1));


    }

}

