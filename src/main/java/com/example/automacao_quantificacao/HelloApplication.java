package com.example.automacao_quantificacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//
// Decisões para serem tomadas a respeito do projeto:
// A quantificação levará em consideração mais de um prédio ?
// Realização do algoritmo para exportação da planilha: arquivo xls, csv, excel ou pdf ?
// Será somente uma tela ?
// Como ficará a questão da quantificação quando envolver backbone de fibra ?
//

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("quantificacao_mh_e_backbone.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Tela Inicial");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}