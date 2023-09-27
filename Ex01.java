import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  * Lucas Gomes dos Santos e Rodrigo Braga
 */
public class Ex01 extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] buttons;

    private double num1 = 0;
    private String operator = "";

    public Ex01() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));

        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        //checando se é um dígito entre 0 e 9 ou um ponto
        if (command.matches("\\d") || command.equals(".")) {
            if (display.getText().equals("Erro")) {
                display.setText("");
            }
            display.setText(display.getText() + command);
        }
        //checando se é um operador +, -, * ou / 
        else if (command.matches("[+\\-*/]")) {
            try {
            num1 = Double.parseDouble(display.getText());
            operator = command;
            display.setText("");
            } catch (NumberFormatException ex) {
                display.setText("Erro");
            }
        } else if (command.equals("=")) {
            try {
            double num2 = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Não é possível dividir por 0");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                display.setText("Erro");
            }
        } else if (command.equals("C")) {
            display.setText("");
            num1 = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
            new Ex01();
    }
}
