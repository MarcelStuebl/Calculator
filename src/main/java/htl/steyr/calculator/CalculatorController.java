package htl.steyr.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CalculatorController {
    public TextField resultTextField;

    private Character operation;
    private float result;

    @FXML
    public void clearButtonClicked(ActionEvent actionEvent) {
        resultTextField.setText("0");
    }

    public void numberButtonCLicked(ActionEvent actionEvent) {
        /**
          Wenn man auf eine Zahl gedrückt wird, soll diese in das Textfeld geschrieben werden!
          Steht bereits eine Zahl im Textfeld, soll die neue Zahl angehängt werden.
         */
        if (resultTextField.getText().equals("+") || resultTextField.getText().equals("-") || resultTextField.getText().equals("×") || resultTextField.getText().equals("÷") || resultTextField.getText().isEmpty()) {
            resultTextField.clear();
        }
        Button button = (Button) actionEvent.getSource();
        if (resultTextField.getText().equals("0")) {
            resultTextField.setText(button.getText());
        } else {
            resultTextField.appendText(button.getText());
        }
    }

    public void mathOperationClicked(ActionEvent actionEvent) {
        /**
         * Erstellen Sie die Logik zum Addieren zweier Zahlen.
         * Ablauf: Es wird die erste Zahl eingegeben.
         * Dann wird eine der vier Operatoren gewählt.
         * Anschließend wird die zweite Zahl eingegeben.
         * Sobald mit der zweiten Zahl zu tippen begonnen wird, soll die erste Zahl in eine Variable gespeichert Werden.
         * Wenn die zweite Zahl eingegeben wurde un der "=" Button geklickt wurde, wird das Ergebnis im Textfeld ausgegeben.
         */
        resultTextField.setText(resultTextField.getText().replace(",", "."));

        if (resultTextField.getText().equals("+") || resultTextField.getText().equals("-") || resultTextField.getText().equals("×") || resultTextField.getText().equals("÷") || resultTextField.getText().isEmpty()) {
            resultTextField.setText(resultTextField.getText());
        } else {
            result = Float.parseFloat(resultTextField.getText());
        }
        Button button = (Button) actionEvent.getSource();
        operation = button.getText().charAt(0);
        resultTextField.setText(operation.toString());
    }

    public void resultButtonClicked(ActionEvent actionEvent) {

        if (operation == '+') {
            result = result + Float.parseFloat(resultTextField.getText());
        } else if (operation == '-') {
            result = result - Float.parseFloat(resultTextField.getText());
        } else if (operation == '×') {
            result = result * Float.parseFloat(resultTextField.getText());
        } else if (operation == '÷') {
            result = result / Float.parseFloat(resultTextField.getText());
        }

        if (result == (long) result) {
            resultTextField.setText(String.format("%d", (long) result));
        } else {
            resultTextField.setText(String.format("%s", result));
        }
    }


    public void invertButtonCLicked(ActionEvent actionEvent) {
        resultTextField.setText(String.valueOf(-Float.parseFloat(resultTextField.getText())));
    }

    public void commaButtonClicked(ActionEvent actionEvent) {
        if (!resultTextField.getText().contains(".")) {
            resultTextField.appendText(".");
        }
    }

    public void removeButtonClicked(ActionEvent actionEvent) {
        String text = resultTextField.getText();
        if (!text.equals("0")) {
            resultTextField.setText(text.substring(0, text.length() - 1));
            if (resultTextField.getText().isEmpty()) {
                resultTextField.setText("0");
            }
        }
    }

    public void textFieldOnKeyUp(KeyEvent keyEvent) {
        resultTextField.setText(resultTextField.getText().replace(",", "."));
        if (resultTextField.getText().charAt(0) == '0') {
            resultTextField.setText(resultTextField.getText().substring(1));
        }
        resultTextField.positionCaret(resultTextField.getText().length());
    }
}









