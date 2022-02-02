package ru.stqa.pft.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.stqa.pft.telegrambot.entity.Currency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TelegramBotTests extends TelegramLongPollingBot {
  @Override
  public String getBotUsername() {
    return "@Top02Bot";
  }

  @Override
  public String getBotToken() {
    return "5199035196:AAHylpxa8XCenxhEG0cS4rlBifmt0_mXHV0";
  }

  public static void main(String[] args) throws TelegramApiException {
    TelegramBotTests bot = new TelegramBotTests();
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    telegramBotsApi.registerBot(bot);
    System.out.println("Бот запущен...");
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage()) {
      try {
        handleMessage(update.getMessage());
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  private void handleMessage(Message message) throws TelegramApiException {
    //handle command
    if (message.hasText() && message.hasEntities()) {
      Optional<MessageEntity> comandEntity =
              message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
      if(comandEntity.isPresent()){
        //обрезаем команду
        String command = message.getText().substring(comandEntity.get().getOffset(), comandEntity.get().getLength());
        switch (command){
          case "/set_currency":
            //Создаем массив кнопок
            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            for (Currency currency : Currency.values()) {
              //для каждой currency добавляем строчку
              buttons.add(Arrays.asList(
                       InlineKeyboardButton.builder()
                              .text(currency.name())
                              .callbackData("Исходная: " + currency)
                              .build(),
                       InlineKeyboardButton.builder()
                              .text(currency.name())
                              .callbackData("Целевая: " + currency)
                              .build()));
            }
            //отправить сообщение пользователю
            execute(SendMessage.builder().text("Выберите исходную и целевую валюту")
                    .chatId(message.getChatId().toString())
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                    .build());
            return;
        }
      }
    }
  }
}

