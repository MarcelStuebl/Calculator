package htl.steyr.calculator;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CalculatorController {
    public TextField resultTextField;
    public TextField logTextField;

    private Character operation;
    private float result = 0;
    private float num1 = 0;
    private float num2 = 0;


    private void resetAll() {
        resultTextField.setText("0");
        logTextField.clear();
        result = 0;
        num1 = 0;
        num2 = 0;
        operation = null;
    }

    private void appendNumber(String number) {
        if (resultTextField.getText().matches("[+\\-×÷]?")) {
            resultTextField.clear();
        }
        if (resultTextField.getText().equals("0")) {
            resultTextField.setText(number);
        } else {
            resultTextField.appendText(number);
        }
    }

    private void applyOperation(Character op) {
        resultTextField.setText(resultTextField.getText().replace(",", "."));

        if (resultTextField.getText().matches("[+\\-×÷]?") && result == 0) {
            num1 = result;
        } else if (result == 0) {
            result = Float.parseFloat(resultTextField.getText());
        } else {
            calculate();
        }

        operation = op;
        logTextField.appendText(resultTextField.getText() + operation);
        resultTextField.setText(operation.toString());
    }

    private void formatAndDisplayResult() {
        if (result == (long) result) {
            resultTextField.setText(String.format("%d", (long) result));
        } else {
            resultTextField.setText(String.valueOf(result));
        }
    }

    private void handleBackspace() {
        String text = resultTextField.getText();
        if (!text.equals("0")) {
            resultTextField.setText(text.substring(0, text.length() - 1));
            if (resultTextField.getText().isEmpty()) {
                resetAll();
            }
        }
    }

    // ---------- Buttons ----------
    public void clearButtonClicked(ActionEvent actionEvent) {
        resetAll();
    }

    public void numberButtonCLicked(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        appendNumber(button.getText());
    }

    public void mathOperationClicked(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        applyOperation(button.getText().charAt(0));
    }

    private void calculate() {
        num1 = result;
        num2 = Float.parseFloat(resultTextField.getText());

        switch (operation) {
            case '+':
                result += num2;
                break;
            case '-':
                result -= num2;
                break;
            case '×':
                result *= num2;
                break;
            case '÷':
                result /= num2;
                break;
        }
    }

    public void resultButtonClicked(ActionEvent actionEvent) {
        calculate();
        formatAndDisplayResult();
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
        handleBackspace();
    }

    // ---------- Tastatureingaben ----------
    public void textFieldOnKeyUp(KeyEvent keyEvent) {
        String key = keyEvent.getText();

        if (key.matches("[+\\-/*]") || keyEvent.isShiftDown() && key.equals("7")) {
            char op = key.charAt(0);
            if (op == '*') {
                op = '×';
            } else if (keyEvent.isShiftDown() && op == '7') {
                op = '÷';
            }
            applyOperation(op);
        } else if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.isShiftDown() && (key.equals("0"))) {
            calculate();
            formatAndDisplayResult();
            logTextField.appendText(num2 + "=" + result);
        } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            handleBackspace();
        } else if (key.matches("[0-9]")) {
            appendNumber(key);
        }
    }
}
