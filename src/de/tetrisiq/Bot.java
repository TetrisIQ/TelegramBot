package de.tetrisiq;

import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bot extends TelegramLongPollingBot {

    String token = "";
    String botNick = "";
    String docPath = "";


    public void onUpdateReceived(Update update) {
        //TODO: On Update do monitoring stuff
        switch (update.getMessage().getText().toLowerCase()) {
            case "/file" :
                //define Date
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH");
                Date date = new Date();
                String caption = "Build Log from " + dateFormat.format(date) +" Uhr";
                File file = new File(docPath);
                SendDocument doc = new SendDocument()
                        .setChatId(update.getMessage().getChatId())
                        .setNewDocument(file)
                        .setCaption(String.format(caption));
                try {
                    sendDocument(doc); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "/last" :
                


                break;


            default:
                SendMessage message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Aktiv");
                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


        }



    }

    public String getBotUsername() {
        return botNick;
    }
    public void setBotNick(String nick) {
        this.botNick = nick;
    }

    public String getBotToken() {
        return token;
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

    public void setToken(String token) {
        this.token = token;
    }

    public void setdocPath(String docPath) {
        this.docPath = docPath;
    }
}
