package com.example.automacao_quantificacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuantificacaoMhController implements Initializable {

    @FXML
    private ToggleGroup grupo;

    @FXML
    private RadioButton radio_bandeja_deslizante;

    @FXML
    private RadioButton radio_bandeja_fixa;

    @FXML
    private Button botao_calculo;

    @FXML
    private CheckBox box_area_reservada;

    @FXML
    private CheckBox box_dados;

    @FXML
    private CheckBox box_reguas_fechamento;

    @FXML
    private CheckBox box_seg;

    @FXML
    private CheckBox box_voz;

    @FXML
    private TextField campo_dados;

    @FXML
    private TextField campo_dt_malha;

    @FXML
    private TextField campo_n_pavimentos;

    @FXML
    private TextField campo_pts_pavimentos;

    @FXML
    private TextField campo_seg;

    @FXML
    private TextField campo_voz;

    @FXML
    private ComboBox<String> categoria;

    @FXML
    private CheckBox box_DVR;



    @FXML
    void botao_calculo_acao(ActionEvent event) throws IOException {

        Calculos.campo_pts_pavimentos=Integer.parseInt(campo_pts_pavimentos.getText());
        Calculos.campo_n_pavimentos=Integer.parseInt(campo_n_pavimentos.getText());
        Calculos.campo_dt_malha=Integer.parseInt(campo_dt_malha.getText());
        Calculos.categoria=categoria.getValue();
        Calculos.campo_dados=campo_dados.getText();
        Calculos.campo_voz=campo_voz.getText();
        Calculos.campo_seg=campo_seg.getText();

        if(radio_bandeja_deslizante.isSelected())
            Calculos.bandeja_deslizante=true;
        else
            Calculos.bandeja_fixa=true;

        if(box_DVR.isSelected())
            Calculos.DVR=true;
        if(box_area_reservada.isSelected())
            Calculos.area_reservada=true;

        Calculos.calcula();

        HelloApplication.renderizaTelas("tabela");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>();
        lista.add("Cat 5e");
        lista.add("Cat 6");
        lista.add("Cat 6a");

        ObservableList<String> obscategorias = FXCollections.observableArrayList(lista);
        categoria.setItems(obscategorias);
    }

}
