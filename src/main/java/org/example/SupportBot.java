package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SupportBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // тут можно сообщения от пользователей обработать, или позже дописать функционал
    }
    @Override
    public String getBotUsername() {
        return "CompassionHelperBot";
    }

    @Override
    public String getBotToken() {
        return "6633909020:AAGuibUGLw42XJdluuX2mdQxNCGuwMihk84"; // Укажите ваш токен бота (получить у бот фазер)
    }

    public static void main(String[] args) {
        SupportBot bot = new SupportBot();
        bot.startSupport();
    }

    private void startSupport() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sendSupportMessage();
            }
        }, 0, 2 * 60 * 60 * 1000); // Повторять каждые два часа (в миллисекундах)
    }

    private void sendSupportMessage() {
        String randomMessage = SupportMessage.getRandomMessage();

        // Замените на реальный ID чата
        String CHAT_ID = "-4076111004";
        SendMessage message = SendMessage.builder()
                .chatId(CHAT_ID)
                .text(randomMessage)
                .build();

        try {
            execute(message);
        } catch (TelegramApiException e) {
            Logger.getLogger(SupportBot.class.getName()).log(Level.SEVERE, "Exception while sending support message", e);
        }
    }
}
