package com.example.qwerty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class HelloController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private TextField city;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text temp_feels;

    @FXML
    private Text pressure;


    @FXML
    private Button getData;

    @FXML
    private URL location;


    @FXML
    void initialize() {
        getData.setOnAction(actionEvent -> {

            String getUserCity = city.getText().trim();
            System.out.println(getUserCity);
            String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=fa3153a7195f16865af8c5583ed4ae01&units=metric");
            System.out.println(output);

            if(!getUserCity.equals("")) { // Если данные не пустые

                if (!output.isEmpty()) { // Нет ошибки и такой город есть
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                    temp_feels.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like"));
                    temp_max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                    temp_min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
                    pressure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
                }
            }
        });
                }
    private static String getUrlContent (String urlAdress) { //функция которая возвращает строку
        StringBuffer content = new StringBuffer();
        //System.out.println(content);

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            System.out.println(urlConn);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            System.out.println(bufferedReader);

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception e) {
            System.out.println("Такой город был не найден!");
        }
        System.out.println(content);
        return content.toString();

    }
}
