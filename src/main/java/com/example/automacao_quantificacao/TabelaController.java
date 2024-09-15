package com.example.automacao_quantificacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TabelaController implements Initializable {

    @FXML
    private TableColumn<?, ?> coluna_nome;

    @FXML
    private TableColumn<?, ?> coluna_unidade;

    @FXML
    private TableView<ObjetoColuna> tabela;

    @FXML
    private TableColumn<?, ?> valor_andar;

    @FXML
    private TableColumn<?, ?> valor_total;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ObjetoColuna> data = FXCollections.observableList(ArmazenaListaObjetosColuna.lista);

        coluna_nome.setCellValueFactory(new PropertyValueFactory("nome_classe"));
        coluna_unidade.setCellValueFactory(new PropertyValueFactory("descricao_classe"));
        valor_andar.setCellValueFactory(new PropertyValueFactory("quantidade_classe"));
        valor_total.setCellValueFactory(new PropertyValueFactory("totalQuantidade_classe"));

        tabela.setItems(data);
    }
}