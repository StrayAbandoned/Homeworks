package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessTheNumber extends JFrame{
    private JTextField textField;
    private int randomInt;
    private int tries = 0;

    public GuessTheNumber() {
        randomInt = (int) (Math.random() * 10) + 1;

        setTitle("Поиграем");
        setBounds(200, 200, 700, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);

        Font font = new Font("Arial", Font.PLAIN, 16);


        textField = new JTextField();
        add(textField, BorderLayout.NORTH);

        textField.setText("Попробуйте угадать число. У Вас 3 попытки");
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(font);

        JPanel panel = new JPanel(new GridLayout(1, 10));
        add(panel, BorderLayout.CENTER);
        JButton button, button1;
        button1 = new JButton("Начать с начала");
        button1.setFont(font);
        add(button1, BorderLayout.SOUTH);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();

            }
        });

        for (int i = 1; i < 11; i++) {
            int number = i;
            button = new JButton(String.valueOf(i));
            button.setFont(font);
            panel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tries==3) {
                        dispose();
                    }
                    guessTheNumber(number);

                }
            });
        }


        setVisible(true);
    }

    private void guessTheNumber(int answer) {
        tries++;
        if (tries == 3 && answer != randomInt) {
            textField.setText("Вы не угадали! Нажмите \"Начать сначала\" чтобы начать новую игру");
            return;

        }
        if (answer == randomInt) {
            textField.setText("Вы угадали! Нажмите \"Начать сначала\" чтобы начать новую игру");
            tries =3;
        } else if (answer < randomInt) {
            textField.setText("Загаданное число больше");
        } else {
            textField.setText("Загаданное число меньше");
        }


    }

    private void restart() {
        tries = 0;
        randomInt = (int) (Math.random() * 10) + 1;
        textField.setText("Попробуйте угадать число. У Вас 3 попытки");
    }

}
