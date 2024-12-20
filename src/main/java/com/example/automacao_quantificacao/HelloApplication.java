package com.example.automacao_quantificacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage cena_stage;
    private static Scene cena_tela_inicial;
    private static Scene cena_quantificacao_mh;
    private static Scene cena_tabela;
    private static Scene cena_quantificacao_mh_e_backbone;

    @Override
    public void start(Stage stage) throws IOException {
        cena_stage=stage;
        //a
        stage.setTitle("Tela Inicial");

        FXMLLoader tela_inicial_loader = new FXMLLoader(HelloApplication.class.getResource("tela_inicial.fxml"));
        cena_tela_inicial = new Scene(tela_inicial_loader.load(), 400, 150);


        stage.setScene(cena_tela_inicial);
        stage.show();
    }

    public static void iniciaTelas(String arg) {
        switch (arg){
            case "quantificacao_mh_e_backbone":
                cena_stage.setScene(cena_quantificacao_mh);
                break;

            case "tabela":
                cena_stage.setScene(cena_tabela);
                break;

        }
    }

    public static void renderizaTelas(String arg) throws IOException{
        switch (arg) {
            case "quantificacao_mh_e_backbone":
                FXMLLoader tela_quantificacao_mh_loader = new FXMLLoader(HelloApplication.class.getResource("quantificacao_mh_e_backbone.fxml"));
                cena_quantificacao_mh = new Scene(tela_quantificacao_mh_loader.load(), 800, 500);
                HelloApplication.iniciaTelas(arg);
                break;

            case "tabela":
                FXMLLoader tela_tabela_loader = new FXMLLoader(HelloApplication.class.getResource("tabela.fxml"));
                cena_tabela = new Scene(tela_tabela_loader.load(), 600, 400);
                HelloApplication.iniciaTelas(arg);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}