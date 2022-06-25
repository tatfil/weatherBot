package demo.example.main;

import demo.example.model.ForecastRequest;
import demo.example.model.WeatherUnit;
import demo.example.parser.WeatherParserTomorrowIO;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

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
