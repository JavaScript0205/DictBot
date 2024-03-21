package bot;

import map.MapUz;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    MapUz<String, List<String>> dict;

    public Bot() {
        dict = new MapUz<>();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        User usr = msg.getFrom();
        String text = msg.getText();

        SendMessage sm = new SendMessage();
        sm.setChatId(msg.getChatId());
        //TODO: "/start", "/help", "/remove hello", "/contains hello"
        if (text.equals("/help")) {
            sm.setText("""
                    Foydalanuchi qo`llanmasi:
                    /start - Botga kirish;
                    /put#so`z#ma`nolari, ma`nolari,... - Lug`atga so`z qo`shish;
                    /show - Array(Massiv, List)ga qo`shilgan elementlarni ko`rish;
                    /remove so`z - Lug`atdan o`chirish;
                    /contains so`z - Solishtirish (true/false);
                    /help - Yordam;
                    """);
        } else if (text.equals("/start")) {
            sm.setText(usr.getId().toString().equals("1618991665")
                    ? usr.getFirstName() + usr.getLastName() + " - foydalanuvchi turi: Admin"
                    : usr.getFirstName() + usr.getLastName() + " - foydalanuvchi turi: User");

        } else if (text.startsWith("/put")) {
            String[] parts = text.split("#");
            String key = parts[1];
            String values = parts[2];
            String[] words = values.split(",");

            List<String> list = List.of(words);

            dict.put(key, list);
            sm.setText("Success");

        } else if (text.equals("/show")) {
            sm.setText(dict.toString());//TODO beautify this
        } else if (text.equals("/remove")) {

        } else {
            sm.setText(dict.get(text).toString());//TODO beautify this
        }

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "simple_pdp_bot";
    }

    @Override
    public String getBotToken() {
        return "6933294343:AAE03Ccbw0DpgEsIo2SHqxA_0ToxXP27i7Y";
    }
}
