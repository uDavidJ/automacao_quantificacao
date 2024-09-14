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

    private static Stage cena_stage;
    private static Scene cena_tela_inicial;
    private static Scene cena_quantificacao_mh;
    private static Scene cena_quantificacao_mh_e_backbone;

    @Override
    public void start(Stage stage) throws IOException {
        cena_stage=stage;

        stage.setTitle("Tela Inicial");

        FXMLLoader tela_inicial_loader = new FXMLLoader(HelloApplication.class.getResource("tela_inicial.fxml"));
        cena_tela_inicial = new Scene(tela_inicial_loader.load(), 400, 150);

        FXMLLoader tela_quantificacao_mh_loader = new FXMLLoader(HelloApplication.class.getResource("quantificacao_mh.fxml"));
        cena_quantificacao_mh = new Scene(tela_quantificacao_mh_loader.load(), 800, 500);

        stage.setScene(cena_tela_inicial);
        stage.show();
    }

    public static void gerenciadorTelas(String arg) {
        switch (arg){
            case "quantificacao_mh":
                cena_stage.setScene(cena_quantificacao_mh);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}