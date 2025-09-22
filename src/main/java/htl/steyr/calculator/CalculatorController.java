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
    private float num = 0;
    private boolean isresult = false;


    private void resetAll() {
        resultTextField.setText("0");
        logTextField.clear();
        result = 0;
        num = 0;
        isresult = false;
        operation = null;
    }

    private void addNumber(String number) {
        if (resultTextField.getText().matches("[+\\-×÷]?")) {
            resultTextField.clear();
        }
        if (resultTextField.getText().equals("0")) {
            resultTextField.setText(number);
        } else {
            resultTextField.appendText(number);
        }
    }

    private void setOperation(Character op) {
        resultTextField.setText(resultTextField.getText().replace(",", "."));

        if (result == 0) {
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

    private void remove() {
        String text = resultTextField.getText();
        if (!text.equals("0")) {
            resultTextField.setText(text.substring(0, text.length() - 1));
            if (resultTextField.getText().isEmpty()) {
                resetAll();
            }
        }
    }

    private void result() {
        calculate();
        formatAndDisplayResult();
        logTextField.appendText(num + "=" + result);
        num = 0;
        isresult = true;
    }


    public void clearButtonClicked() {
        resetAll();
    }

    public void numberButtonCLicked(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        addNumber(button.getText());
    }

    public void mathOperationClicked(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        setOperation(button.getText().charAt(0));
    }

    private void calculate() {
        if (!isresult) {
            num = Float.parseFloat(resultTextField.getText());

            switch (operation) {
                case '+' -> result += num;
                case '-' -> result -= num;
                case '×' -> result *= num;
                case '÷' -> result /= num;
            }
        } else {
            isresult = false;
        }
    }

    public void resultButtonClicked() {
        result();
    }

    public void invertButtonCLicked() {
        resultTextField.setText(String.valueOf(-Float.parseFloat(resultTextField.getText())));
    }

    public void commaButtonClicked() {
        if (!resultTextField.getText().contains(".")) {
            resultTextField.appendText(".");
        }
    }

    public void removeButtonClicked() {
        remove();
    }


    public void textFieldOnKeyDown(KeyEvent keyEvent) {
        String key = keyEvent.getText();

        if (key.matches("[+\\-/*]") || keyEvent.isShiftDown() && key.equals("7")) {
            char op = key.charAt(0);
            if (op == '*') {
                op = '×';
            } else if (keyEvent.isShiftDown() && op == '7') {
                op = '÷';
            }
            setOperation(op);
        } else if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.isShiftDown() && (key.equals("0"))) {
            result();
        } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            remove();
        } else if (key.matches("[0-9]")) {
            addNumber(key);
        } else if (!resultTextField.getText().contains(".") && ((key.matches(",") || key.matches(".")))) {
            resultTextField.appendText(".");
        }
    }
}










