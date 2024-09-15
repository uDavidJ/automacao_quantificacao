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


public class QuantificacaoMhEBackboneController implements Initializable {

    @FXML
    private TextField campo_backbone_andar;

    @FXML
    private ComboBox<String> box_categoria_fibra;

    @FXML
    private TextField campo_pares_andar;

    @FXML
    private TextField campo_pe_direito;

    @FXML
    private CheckBox box_bandeja_deslizante;

    @FXML
    private CheckBox box_area_reservada;

    @FXML
    private CheckBox box_reguas_fechamento;

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
    void botao_calculo_acao(ActionEvent event) throws IOException {

        Calculos.campo_pts_pavimentos=Integer.parseInt(campo_pts_pavimentos.getText());

        if ( !(campo_n_pavimentos.getText() == null) && !(campo_n_pavimentos.getText().isEmpty()))
            Calculos.campo_n_pavimentos = Integer.parseInt(campo_n_pavimentos.getText());
        else
            Calculos.campo_n_pavimentos = 1;

        Calculos.campo_dt_malha=Integer.parseInt(campo_dt_malha.getText());
        Calculos.categoria=categoria.getValue();
        Calculos.campo_voz=campo_voz.getText();
        Calculos.campo_seg=campo_seg.getText();

        if(box_bandeja_deslizante.isSelected())
            Calculos.bandeja_deslizante=true;

        if(!(campo_seg.getText() == null) && (Integer.parseInt(campo_seg.getText()) != 0))
            Calculos.DVR=true;

        if(box_area_reservada.isSelected())
            Calculos.area_reservada=true;

        if(box_reguas_fechamento.isSelected())
            Calculos.setReguaFechamento(true);

        Calculos.calcula();

        HelloApplication.renderizaTelas("tabela");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ArmazenaEscolha.escolha.equals("malha_horizontal")) {
            campo_pares_andar.setDisable(true);
            campo_pe_direito.setDisable(true);
            campo_backbone_andar.setDisable(true);
            box_categoria_fibra.setDisable(true);
            campo_n_pavimentos.setDisable(true);
        }

        List<String> lista = new ArrayList<>();
        lista.add("Cat 5e");
        lista.add("Cat 6");
        lista.add("Cat 6a");

        ObservableList<String> obscategorias = FXCollections.observableArrayList(lista);
        categoria.setItems(obscategorias);

        lista = new ArrayList<>();
        lista.add("FOMMIG 50x125");
        lista.add("SM 9x125");

        obscategorias = FXCollections.observableArrayList(lista);
        box_categoria_fibra.setItems(obscategorias);
    }

}
