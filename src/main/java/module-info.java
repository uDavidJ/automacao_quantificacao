module com.example.automacao_quantificacao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.automacao_quantificacao to javafx.fxml;
    exports com.example.automacao_quantificacao;
}