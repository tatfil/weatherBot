package demo.example.main;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main {
    public static void main(String[] args) throws Exception {

        TelegramBotsApi botsApi = null;
        try {
            // Instantiate Telegram Bots API
            botsApi = new TelegramBotsApi(DefaultBotSession.class);

            // Register our bot
            botsApi.registerBot(new WeatherBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
