package com.example.automacao_quantificacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class QuantificacaoMhEBackboneController implements Initializable {



    @FXML
    private ComboBox<String> box_categoria_fibra;

    @FXML
    private TextField campo_pares_fibra_EF;

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
    private TextField campo_n_predios;

    @FXML
    private RadioButton radio_button_backbone_secundario;

    @FXML
    private CheckBox box_backbone_primario;

    @FXML
    private TextField campo_dados;

    @FXML
    private CheckBox box_backbone_secundario;

    @FXML
    private TextField campo_pares_fibra_BBS;

    @FXML
    private TextField campo_pts_pavimentos;

    @FXML
    private TextField campo_seg;

    @FXML
    private TextField campo_voz;

    @FXML
    private ComboBox<String> categoria;

    @FXML
    private Text texto_fibra_backbone_secundario;

    @FXML
    private Text texto_distancia_predios;

    @FXML
    void botao_calculo_acao(ActionEvent event) throws IOException {

        if ((campo_pts_pavimentos != null) && !campo_pts_pavimentos.getText().isEmpty())
            Calculos.campo_pts_pavimentos=Integer.parseInt(campo_pts_pavimentos.getText());

        if ( !(campo_n_pavimentos.getText() == null) && !(campo_n_pavimentos.getText().isEmpty()))
            Calculos.campo_n_pavimentos = Integer.parseInt(campo_n_pavimentos.getText());
        else
            Calculos.campo_n_pavimentos = 1;


        if(ArmazenaEscolha.escolha.contains("malha")) {
            if ((campo_dt_malha != null) && !campo_dt_malha.getText().isEmpty())
                Calculos.campo_dt_malha=Integer.parseInt(campo_dt_malha.getText());

            Calculos.categoria=categoria.getValue();
            Calculos.campo_voz=campo_voz.getText();
            Calculos.campo_seg=campo_seg.getText();
            Calculos.campo_dados=campo_dados.getText();

            if(box_bandeja_deslizante.isSelected())
                Calculos.bandeja_deslizante=true;

            if(!(campo_seg.getText() == null) && !campo_seg.getText().isEmpty())
                if (Integer.parseInt(campo_seg.getText()) != 0)
                    Calculos.DVR=true;

            if(box_area_reservada.isSelected())
                Calculos.area_reservada=true;

            if(box_reguas_fechamento.isSelected())
                Calculos.setReguaFechamento(true);

            Calculos.calcula();
        }

        if (ArmazenaEscolha.escolha.contains("backbone")) {
            Calculos.setPeDireito(Integer.parseInt(campo_pe_direito.getText()));
            Calculos.setFibra(box_categoria_fibra.getValue());
            Calculos.setFibrasAndar(Integer.parseInt(campo_pares_fibra_EF.getText()));

            Calculos.calculafibra();
        }

        Calculos.calcula_rack_set(Calculos.tamanho_rack_set);

        if(ArmazenaEscolha.escolha.contains("backbone"))
            Calculos.calcula_rack_seq();

        if(ArmazenaEscolha.getBe()) {
            if ((campo_pares_fibra_BBS != null) && !campo_pares_fibra_BBS.getText().isEmpty())
                Calculos.setParesExterno(Integer.parseInt(campo_pares_fibra_BBS.getText()));

            if ((campo_n_predios != null) && !campo_n_predios.getText().isEmpty())
                Calculos.setDistanciaExterna(Integer.parseInt(campo_n_predios.getText()));

            Calculos.calcula_backbone_externo();
        }

        HelloApplication.renderizaTelas("tabela");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ArmazenaEscolha.escolha.equals("malha_horizontal")) {
            campo_pares_fibra_EF.setDisable(true);
            campo_pe_direito.setDisable(true);
            box_categoria_fibra.setDisable(true);
        } else if(ArmazenaEscolha.escolha.equals("backbone")) {
            campo_pts_pavimentos.setDisable(true);
            campo_dt_malha.setDisable(true);
            categoria.setDisable(true);
            campo_voz.setDisable(true);
            campo_seg.setDisable(true);
            campo_dados.setDisable(true);
        }

        List<String> lista = new ArrayList<>();
        lista.add("CAT 5e");
        lista.add("CAT 6");
        lista.add("CAT 6a");

        ObservableList<String> obscategorias = FXCollections.observableArrayList(lista);
        categoria.setItems(obscategorias);

        lista = new ArrayList<>();
        lista.add("1GB");
        lista.add("10GB");

        obscategorias = FXCollections.observableArrayList(lista);
        box_categoria_fibra.setItems(obscategorias);
    }
    @FXML
    void radio_button_backbone_secundario_acao(ActionEvent event) {
        if(radio_button_backbone_secundario.isSelected()) {
            ArmazenaEscolha.setBe(true);
            campo_pares_fibra_BBS.setOpacity(1.0);
            campo_n_predios.setOpacity(1.0);
            texto_fibra_backbone_secundario.setOpacity(1.0);
            texto_distancia_predios.setOpacity(1.0);
        } else {
            ArmazenaEscolha.setBe(false);
            campo_pares_fibra_BBS.setOpacity(0.0);
            campo_n_predios.setOpacity(0.0);
            texto_fibra_backbone_secundario.setOpacity(0.0);
            texto_distancia_predios.setOpacity(0.0);
        }
    }
}
