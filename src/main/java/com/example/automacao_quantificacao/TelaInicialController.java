package com.example.automacao_quantificacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class TelaInicialController {

    @FXML
    private Button botao_prosseguir;

    @FXML
    private CheckBox box_bo;

    @FXML
    private CheckBox box_mh;

    @FXML
    void botao_prosseguir_acao(ActionEvent event) {
        if(box_mh.isSelected())
            HelloApplication.gerenciadorTelas("quantificacao_mh");

    }

}
