package com.example.automacao_quantificacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class QuantificacaoMhController {

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
    private ComboBox<?> categoria;

    @FXML
    void botao_calculo_acao(ActionEvent event) {

    }

}
