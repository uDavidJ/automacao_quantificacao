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
    private CheckBox box_exaustor;

    @FXML
    void botao_calculo_acao(ActionEvent event) throws IOException {

        //Conversoes e variaveis
        Integer tomadas = Integer.parseInt(campo_pts_pavimentos.getText()) * 2;
        Integer pav = Integer.parseInt(campo_n_pavimentos.getText());
        Integer patchCords = tomadas;
        Integer malha_horizontal = Integer.parseInt(campo_dt_malha.getText()) * tomadas;
        Integer PP;
        Integer OF;
        double tamanho_rack = 0.0;
        int[] rack_informacoes;
        Integer tamanho_bandeja;
        String modelo_rack;
        String categoria_cabo = categoria.getValue();


//        ObjetoColuna ob = new ObjetoColuna("Área de trabalho", "",0, 0);
//        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao das tomadas
        ObjetoColuna ob = new ObjetoColuna("Tomada RJ-45 fêmea (" + categoria_cabo + ")", "Unid.",
                tomadas,
                pav*tomadas);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos espelhos
        ob = new ObjetoColuna("Espelho de conexão 2x4 (" + categoria_cabo + ")", "Unid.",
                tomadas/2,
                (tomadas/2)*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos patch cords
        if(!(campo_voz.getText() == "" || campo_voz.getText() == "0"))
            patchCords = patchCords - Integer.parseInt(campo_voz.getText());

        ob = new ObjetoColuna("Cordão de ligação (Patch cord), (Cor: Azul) (Tamanho: 3M) (" + categoria_cabo + ")", "Unid.",
                patchCords,
                tomadas*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao cabo UTP rigido

        if(malha_horizontal%305 != 0)
            malha_horizontal=(malha_horizontal/305)+1;
        else
            malha_horizontal=malha_horizontal/305;

        ob = new ObjetoColuna("Cabo UTP rígido para malha horizontal (" + categoria_cabo + ")", "Cxs",
                malha_horizontal,
                malha_horizontal*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos patch cables
        if(!(campo_voz.getText() == "" || campo_voz.getText() == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Amarelo) (Tamanho: 2M) (" + categoria_cabo + ")", "Unid.",
                    Integer.parseInt(campo_voz.getText()),
                    Integer.parseInt(campo_voz.getText()) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(!(campo_seg.getText() == "" || campo_seg.getText() == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Vermelho) (Tamanho: 2M) (" +categoria_cabo + ")", "Unid.",
                    Integer.parseInt(campo_seg.getText()),
                    Integer.parseInt(campo_seg.getText()) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(!(campo_dados.getText() == "" || campo_dados.getText() == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Azul) (Tamanho: 2M) (" + categoria_cabo + ")", "Unid.",
                    Integer.parseInt(campo_dados.getText()),
                    Integer.parseInt(campo_dados.getText()) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        //Patch Panel
        if(tomadas%24 != 0)
            PP = (tomadas/24)+1;
        else
            PP = tomadas/24;

        ob = new ObjetoColuna("Painel de telecomunicações 24 portas (Altura: 1U) (" + categoria_cabo + ")", "Unid.",
                PP,
                PP*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //SwitcheS (mesma quantidade de PP)
        ob = new ObjetoColuna("Switch 24 portas (Altura: 1U)", "Unid.",
                PP,
                PP*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Organizadores frontais de cabos
        OF = 2*PP;
        ob = new ObjetoColuna("Organizador frontal de cabo (Altura: 1U)", "Unid.",
                OF,
                OF*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Montagem do rack
        if(radio_bandeja_fixa.isSelected())
            tamanho_bandeja=4;
        else
            tamanho_bandeja=3;

        tamanho_rack = (double) ((PP*2) + OF + tamanho_bandeja);

        System.out.println(tamanho_rack);

        if (box_DVR.isSelected())
            tamanho_rack+=2;

        tamanho_rack = tamanho_rack*1.5;
        System.out.println(tamanho_rack);

        rack_informacoes = calculaTamanhoRack(tamanho_rack);

        if(box_area_reservada.isSelected())
        {
            modelo_rack="Aberto";
            ob = new ObjetoColuna("Organizador lateral para rack " + String.valueOf(rack_informacoes[0]) + "U",
                    "Unid.", rack_informacoes[1]*2, rack_informacoes[1]*pav);
        }

        else
            modelo_rack="Fechado";

        ob = new ObjetoColuna("Rack (" + modelo_rack + ")" + "(Tamanho: " +
                String.valueOf(rack_informacoes[0]) + "U)", "Unid.",
                rack_informacoes[1],
                rack_informacoes[1]*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);



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

    public static void calculaMaterialMalhaHorizontal() {

    }

    public static int[] calculaTamanhoRack(double tamanho_rack) {

        double[] tamanhos_rack = {4.0, 6.0, 8.0, 10.0, 12.0, 16.0, 20.0, 24.0, 28.0, 32.0, 36.0, 40.0, 44.0};
        int quantidade_rack = 1;
        int[] vetor = new int[2];

        while(true) {
            for(int i = 0; i<tamanhos_rack.length; i++) {
                if(tamanho_rack==tamanhos_rack[i]) {
                    tamanho_rack=tamanhos_rack[i];
                    vetor[0] = (int) tamanho_rack;
                    vetor[1] = quantidade_rack;
                    return vetor;
                }else if (i != 0 && i != (tamanhos_rack.length-1)) {
                    if(tamanho_rack > tamanhos_rack[i-1] && tamanho_rack < tamanhos_rack[i+1]) {
                        tamanho_rack = tamanhos_rack[i+1];
                        vetor[0] = (int) tamanho_rack;
                        vetor[1] = quantidade_rack;
                        return vetor;
                    }
                }
            }
            tamanho_rack/=2;
            quantidade_rack++;
        }

    }

}
