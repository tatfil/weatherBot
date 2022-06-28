package demo.example.main;

import demo.example.model.ForecastRequest;
import demo.example.model.WeatherUnit;
import demo.example.parser.WeatherParserTomorrowIO;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class WeatherBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        // Return bot username
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "tg.properties";


        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appProps.getProperty("tg.username");
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "tg.properties";


        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appProps.getProperty("tg.token");
    }

    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            WeatherParserTomorrowIO weatherParserTomorrowIO = new WeatherParserTomorrowIO();
            ForecastRequest forecastRequest = new ForecastRequest();
            forecastRequest.setLocation("60.0911, 29.9502");
            Map<String, WeatherUnit> map = new LinkedHashMap<>();

            try {
                String str = weatherParserTomorrowIO.getResponse(forecastRequest);
                map = weatherParserTomorrowIO.convertRawDataToMap(str);

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Set variables
            String message_text = weatherParserTomorrowIO.formatForecastData(map);
            String chat_id = update.getMessage().getChatId().toString();

            SendMessage message = new SendMessage(); // Create a message object
            message.setChatId(chat_id);
            message.setText(message_text);

            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
