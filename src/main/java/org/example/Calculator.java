package org.example;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {
    Calculator(){}
    public static void main(String[] args) {
        createWindow();
    }
    private static void createWindow(){
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(frame);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(JFrame frame){
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(layout);

        JTextField inputField = new JTextField();
        inputField.setEditable(false);

        JButton a0Button = new JButton("0");
        JButton a1Button = new JButton("1");
        JButton a2Button = new JButton("2");
        JButton a3Button = new JButton("3");
        JButton a4Button = new JButton("4");
        JButton a5Button = new JButton("5");
        JButton a6Button = new JButton("6");
        JButton a7Button = new JButton("7");
        JButton a8Button = new JButton("8");
        JButton a9Button = new JButton("9");

        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        JButton multiButton = new JButton("*");
        JButton divButton = new JButton("/");
        JButton calcButton = new JButton("=");
        JButton deleteButton = new JButton("D");
        JButton resetButton = new JButton("C");

        ActionListener inputListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.charAt(0) == 'C'){
                    inputField.setText("");
                }
                else if(command.charAt(0) == '='){
                    inputField.setText(calculate(inputField));
                }
                else if(command.charAt(0)== 'D'){
                    char[] arr = inputField.getText().toCharArray();
                    char[] newArr = new char[arr.length-1];
                    System.arraycopy(arr, 0, newArr, 0, arr.length - 1);
                    inputField.setText(String.valueOf(newArr));

                }
                else{
                    inputField.setText(inputField.getText() + command);
                }
            }

           private static boolean isNumber(char input){
               return switch (input) {
                   case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> true;
                   default -> false;
               };
            }

            private static boolean isOperator(char input){
                return switch (input) {
                    case '+', '-', '*', '/' -> true;
                    default -> false;
                };
            }

            private static String calculate(JTextField input){

                char[] arr = input.getText().toCharArray();
                int opIndex = 0;
                if(isNumber(arr[0])){
                    // golden path: two operands
                    if(arr.length == 3){
                        switch(arr[1]){
                            case '+': return String.valueOf(arr[0]-'0'+arr[2]-'0');
                            case '-': return String.valueOf(arr[0]-'0'-arr[2]-'0');
                            case '*': return String.valueOf(arr[0]-'0'*arr[2]-'0');
                            case '/': return String.valueOf(arr[0]-'0'/arr[2]-'0');
                        }
                    }
                    // less than three chars
                    else if(arr.length < 3){
                        return "error! too few chars";
                    }
                    // larger numbers (still not more than one operator)
                    else{
                        for(int i = 1; i < arr.length; i++) {
                            if(isOperator(arr[i])){
                                opIndex = i;
                            }
                        }
                        String operand1 = "" + arr[0];
                        String operand2 = "" + arr[opIndex+1];
                        for(int i = 1; i < opIndex; i++){
                            operand1 += arr[i];
                        }
                        for(int i = opIndex+2; i < arr.length; i++){
                            operand2 += arr[i];
                        }
                        switch(arr[opIndex]){
                            case '+': return String.valueOf(Integer.valueOf(operand1) + Integer.valueOf(operand2));
                            case '-': return String.valueOf(Integer.valueOf(operand1) - Integer.valueOf(operand2));
                            case '*': return String.valueOf(Integer.valueOf(operand1) * Integer.valueOf(operand2));
                            case '/': return String.valueOf(Integer.valueOf(operand1) / Integer.valueOf(operand2));
                        }
                    }
                }
                else{
                    return "error! something went wront";
                }
            return null;
            }
        };


        calcButton.addActionListener(inputListener);
        plusButton.addActionListener(inputListener);
        minusButton.addActionListener(inputListener);
        multiButton.addActionListener(inputListener);
        divButton.addActionListener(inputListener);
        deleteButton.addActionListener(inputListener);
        resetButton.addActionListener(inputListener);

        a0Button.addActionListener(inputListener);
        a1Button.addActionListener(inputListener);
        a2Button.addActionListener(inputListener);
        a3Button.addActionListener(inputListener);
        a4Button.addActionListener(inputListener);
        a5Button.addActionListener(inputListener);
        a6Button.addActionListener(inputListener);
        a7Button.addActionListener(inputListener);
        a8Button.addActionListener(inputListener);
        a9Button.addActionListener(inputListener);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0; panel.add(a1Button, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(a2Button, gbc);
        gbc.gridx = 2; gbc.gridy = 0; panel.add(a3Button, gbc);
        gbc.gridx = 3; gbc.gridy = 0; panel.add(plusButton, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(a4Button, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(a5Button, gbc);
        gbc.gridx = 2; gbc.gridy = 1; panel.add(a6Button, gbc);
        gbc.gridx = 3; gbc.gridy = 1; panel.add(minusButton, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(a7Button, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(a8Button, gbc);
        gbc.gridx = 2; gbc.gridy = 2; panel.add(a9Button, gbc);
        gbc.gridx = 3; gbc.gridy = 2; panel.add(divButton, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(deleteButton, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(a0Button, gbc);
        gbc.gridx = 2; gbc.gridy = 3; panel.add(resetButton, gbc);
        gbc.gridx = 3; gbc.gridy = 3; panel.add(multiButton, gbc);
        gbc.gridwidth = 3;

        gbc.gridx = 0; gbc.gridy = 4; panel.add(inputField, gbc);
        gbc.gridx = 3; gbc.gridy = 4; panel.add(calcButton, gbc);
        frame.getContentPane().add(panel, BorderLayout.CENTER);


    }

}