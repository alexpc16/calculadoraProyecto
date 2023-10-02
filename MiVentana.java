import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MiVentana extends JFrame {

    Container contentPane;

    String resultado = "";
    public MiVentana() {
        super("Titulo de ventana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obtenemos el panel principal (contentPane) del frame para agregar elementos sobre el
        this.contentPane = getContentPane();

        // Definimos el modelo de layout para posicionar los elementos en el panel principal
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        JTextField cuadroTexto = new JTextField(10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        contentPane.add(cuadroTexto,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        JButton botonIgual = new JButton("=");
        botonIgual.addActionListener(new EventoBotonPulsado(cuadroTexto, this));
        contentPane.add(botonIgual, constraints);

        

    }
}
class EventoBotonPulsado implements ActionListener {
    private JTextField textField;
    private MiVentana ventana; // Agregamos una referencia a la ventana

    public EventoBotonPulsado(JTextField textField, MiVentana ventana) {
        this.textField = textField;
        this.ventana = ventana; // Inicializamos la referencia a la ventana
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        System.out.println(buttonText.equals(" 1 "));
        
        if(buttonText.equals("=")){
            Resolver resolver = new Resolver();
            double r = Resolver.resolver(textField.getText());
            System.out.println(r);
            ventana.resultado = Double.toString(r);
            textField.setText(ventana.resultado);
        }
    }

}