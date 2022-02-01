package ru.stqa.pft.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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
    if(update.hasMessage()){
      Message message = update.getMessage();
      if(message.hasText()){
        try {
          execute(SendMessage.builder()
                  .chatId(message.getChatId().toString()).text("Вы отправилииии: \n\n" + message.getText() ).build());
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

