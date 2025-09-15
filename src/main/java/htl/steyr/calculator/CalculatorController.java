package htl.steyr.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class CalculatorController {
    public TextField resultTextField;
    public TextField logTextField;

    private Character operation;
    private float result = 0;
    private float num1 = 0;
    private float num2 = 0;
    private boolean shift = false;
    @FXML
    private Button button1;
    @FXML
    private Button button2;

    @FXML
    public void clearButtonClicked(ActionEvent actionEvent) {
        resultTextField.setText("0");
        logTextField.clear();
        result = 0;
        num1 = 0;
        num2 = 0;
        operation = null;
        shift = false;
    }

    public void numberButtonCLicked(ActionEvent actionEvent) {
        /*
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
        /*
         * Erstellen Sie die Logik zum Addieren zweier Zahlen.
         * Ablauf: Es wird die erste Zahl eingegeben.
         * Dann wird eine der vier Operatoren gewählt.
         * Anschließend wird die zweite Zahl eingegeben.
         * Sobald mit der zweiten Zahl zu tippen begonnen wird, soll die erste Zahl in eine Variable gespeichert Werden.
         * Wenn die zweite Zahl eingegeben wurde un der "=" Button geklickt wurde, wird das Ergebnis im Textfeld ausgegeben.
         */

        resultTextField.setText(resultTextField.getText().replace(",", "."));

        if ((resultTextField.getText().equals("+") || resultTextField.getText().equals("-") || resultTextField.getText().equals("×") || resultTextField.getText().equals("÷") || resultTextField.getText().isEmpty()) && result == 0) {
            num1 = result;
        } else if (result == 0){
            result = Float.parseFloat(resultTextField.getText());
        } else {
            calculate();
        }

        Button button = (Button) actionEvent.getSource();
        operation = button.getText().charAt(0);
        logTextField.appendText(resultTextField.getText() + operation);
        resultTextField.setText(operation.toString());
    }

    private void calculate() {
        num1 = result;
        num2 = Integer.parseInt(resultTextField.getText());

        if (operation == '+') {
            result = result + Float.parseFloat(resultTextField.getText());
        } else if (operation == '-') {
            result = result - Float.parseFloat(resultTextField.getText());
        } else if (operation == '×') {
            result = result * Float.parseFloat(resultTextField.getText());
        } else if (operation == '÷') {
            result = result / Float.parseFloat(resultTextField.getText());
        }
    }

    public void resultButtonClicked(ActionEvent actionEvent) {
        calculate();
        if (result == (long) result) {
            resultTextField.setText(String.format("%d", (long) result));
        } else {
            resultTextField.setText(String.format("%s", result));
        }
        logTextField.appendText(num2 + "=" + result);
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
                result = 0;
                operation = null;
            }
        }
    }




    // @ToDO

    public void textFieldOnKeyUp(KeyEvent keyEvent) {
        if (Objects.equals(keyEvent.getText(), "+") || Objects.equals(keyEvent.getText(), "-") || Objects.equals(keyEvent.getText(), "/") || Objects.equals(keyEvent.getText(), "*") || (Objects.equals(keyEvent.getText(), "7") && shift)) {
            operation = keyEvent.getText().charAt(0);
            if (operation == '*') {
                operation = '×';
            } else if (operation == '7') {
                operation = '÷';
            }

            if (result == 0){
                result = Float.parseFloat(resultTextField.getText());
            } else {
                calculate();
            }
            logTextField.appendText(resultTextField.getText() + operation);
            resultTextField.setText(operation.toString());
            shift = false;
        } else if (keyEvent.getCode() == KeyCode.SHIFT) {
            shift = true;
        } else if (keyEvent.getCode() == KeyCode.ENTER) {
            calculate();
            if (result == (long) result) {
                resultTextField.setText(String.format("%d", (long) result));
            } else {
                resultTextField.setText(String.format("%s", result));
            }
            logTextField.appendText(num2 + "=" + result);
        } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            String text = resultTextField.getText();
            if (!text.equals("0")) {
                resultTextField.setText(text.substring(0, text.length() - 1));
                if (resultTextField.getText().isEmpty()) {
                    resultTextField.setText("0");
                    result = 0;
                    operation = null;
                }
            }
        } else if(Objects.equals(keyEvent.getText(), "0") && shift){
            calculate();
            if (result == (long) result) {
                resultTextField.setText(String.format("%d", (long) result));
            } else {
                resultTextField.setText(String.format("%s", result));
            }
            logTextField.appendText(num2 + "=" + result);
        } else if (keyEvent.getText().equals("0") || keyEvent.getText().equals("1") || keyEvent.getText().equals("2") ||
                keyEvent.getText().equals("3") || keyEvent.getText().equals("4") || keyEvent.getText().equals("5") ||
                keyEvent.getText().equals("6") || keyEvent.getText().equals("7") || keyEvent.getText().equals("8") || keyEvent.getText().equals("9")) {
            if (resultTextField.getText().equals("+") || resultTextField.getText().equals("-") || resultTextField.getText().equals("×") || resultTextField.getText().equals("÷") || resultTextField.getText().isEmpty()) {
                resultTextField.clear();
            }

            if (resultTextField.getText().equals("0")) {
                resultTextField.setText(keyEvent.getText());
            } else {
                resultTextField.appendText(keyEvent.getText());
            }
            shift = false;
        }





    }
}









