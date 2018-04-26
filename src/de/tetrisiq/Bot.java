package de.tetrisiq;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {
        //TODO: On Update do monitoring stuff

        //switch (update.getMessage().getText()) {

        //}
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("Aktiv");
            System.out.println(update.getMessage().getChatId());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }

    public String getBotUsername() {
        return "GTuxBot";
    }

    public String getBotToken() {
        return "570306112:AAFa4wn7eLD46UayvjKqud1qSEL5EDIWaZg";
    }

    public void sendMessage(String text, String chatID) {
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(chatID)
                .setText(text);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
