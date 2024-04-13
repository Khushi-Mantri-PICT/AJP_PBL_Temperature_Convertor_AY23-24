import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame {
    private JTextField textField;
    private JLabel resultLabel;
    private JComboBox<String> temperatureFromComboBox;
    private JComboBox<String> temperatureToComboBox;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel temperatureLabel = new JLabel("Temperature:");
        textField = new JTextField(10);

        JLabel fromLabel = new JLabel("From:");
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        temperatureFromComboBox = new JComboBox<>(units);

        JLabel toLabel = new JLabel("To:");
        temperatureToComboBox = new JComboBox<>(units);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());

        resultLabel = new JLabel();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(temperatureLabel);
        panel.add(textField);
        panel.add(fromLabel);
        panel.add(temperatureFromComboBox);
        panel.add(toLabel);
        panel.add(temperatureToComboBox);
        panel.add(convertButton);
        panel.add(resultLabel);

        add(panel);
    }

    private class ConvertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event)throws NumberFormatException
        {
                double temperature = Double.parseDouble(textField.getText());
                String fromUnit = (String) temperatureFromComboBox.getSelectedItem();
                String toUnit = (String) temperatureToComboBox.getSelectedItem();
                double result = convertTemperature(temperature, fromUnit, toUnit);
                resultLabel.setText(String.format("%.2f %s", result, toUnit));
            
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
            
        }
    }

    private double convertTemperature(double temperature, String fromUnit, String toUnit) {
        double result = 0.0;
        switch (fromUnit) {
            case "Celsius":
                switch (toUnit) {
                    case "Celsius":
                        result = temperature;
                        break;
                    case "Fahrenheit":
                        result = (temperature * 9 / 5) + 32;
                        break;
                    case "Kelvin":
                        result = temperature + 273.15;
                        break;
                }
                break;
            case "Fahrenheit":
                switch (toUnit) {
                    case "Celsius":
                        result = (temperature - 32) * 5 / 9;
                        break;
                    case "Fahrenheit":
                        result = temperature;
                        break;
                    case "Kelvin":
                        result = (temperature + 459.67) * 5 / 9;
                        break;
                }
                break;
            case "Kelvin":
                switch (toUnit) {
                    case "Celsius":
                        result = temperature - 273.15;
                        break;
                    case "Fahrenheit":
                        result = temperature * 9 / 5 - 459.67;
                        break;
                    case "Kelvin":
                        result = temperature;
                        break;
                }
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverter converter = new TemperatureConverter();
            converter.setVisible(true);
        });
    }
}
