package com.example.automacao_quantificacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;

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
    void botao_calculo_acao(ActionEvent event) throws IOException {

        //Conversoes
        Integer tomadas = Integer.parseInt(campo_pts_pavimentos.getText()) * 2;
        Integer pav = Integer.parseInt(campo_n_pavimentos.getText());
        Integer patchCords = tomadas;
        Integer malha_horizontal = Integer.parseInt(campo_dt_malha.getText()) * tomadas;
        Integer PP;
        Integer OF;
        Integer bandeja_fixa;
        String modelo_rack;


        //Quantificacao das tomadas
        ObjetoColuna ob = new ObjetoColuna("Tomada RJ-45 fêmea", "Unid.",
                tomadas,
                pav*tomadas);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos espelhos
        ob = new ObjetoColuna("Espelho de conexão 2x4", "Unid.",
                tomadas/2,
                (tomadas/2)*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos patch cords
        if(!(campo_voz.getText() == "" || campo_voz.getText() == "0"))
            patchCords = patchCords - Integer.parseInt(campo_voz.getText());

        ob = new ObjetoColuna("Cordão de ligação (Patch cord), (Cor: Azul) (Tamanho: 3M)", "Unid.",
                patchCords,
                tomadas*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao cabo UTP rigido

        if(malha_horizontal%305 != 0)
            malha_horizontal=(malha_horizontal/305)+1;
        else
            malha_horizontal=malha_horizontal/305;

        ob = new ObjetoColuna("Cabo UTP rígido para malha horizontal", "Cxs",
                malha_horizontal,
                malha_horizontal*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos patch cables
        if(!(campo_voz.getText() == "" || campo_voz.getText() == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Amarelo) (Tamanho: 2M)", "Unid.",
                    Integer.parseInt(campo_voz.getText()),
                    Integer.parseInt(campo_voz.getText()) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(!(campo_seg.getText() == "" || campo_seg.getText() == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Vermelho) (Tamanho: 2M)", "Unid.",
                    Integer.parseInt(campo_seg.getText()),
                    Integer.parseInt(campo_seg.getText()) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(!(campo_dados.getText() == "" || campo_dados.getText() == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Azul) (Tamanho: 2M)", "Unid.",
                    Integer.parseInt(campo_dados.getText()),
                    Integer.parseInt(campo_dados.getText()) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        //Patch Panel
        if(tomadas%24 != 0)
            PP = (tomadas/24)+1;
        else
            PP = tomadas/24;

        ob = new ObjetoColuna("Painel de telecomunicações 24 portas (Altura: 1U)", "Unid.",
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

        HelloApplication.renderizaTelas("tabela");

//        for (ObjetoColuna e : ArmazenaListaObjetosColuna.lista) {
//            System.out.println(e.getNome_classe());
//            System.out.println(e.getQuantidade_classe());
//            System.out.println(e.getTotalQuantidade_classe());
//        }
    }

}
