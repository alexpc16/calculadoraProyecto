import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;


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
       JTextField cuadroTexto = new JTextField(15);
        

        cuadroTexto.setBackground(new Color(51, 227, 255));
        
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        //cuadroTexto.setBorder(BorderFactory.createCompoundBorder(cuadroTexto.getBorder(), paddingBorder));
        cuadroTexto.setBorder(paddingBorder);
     
        // Establecer una nueva dimensión para cambiar la altura
        Dimension nuevaDimension = new Dimension(cuadroTexto.getPreferredSize().width, 70); // Altura de 50 píxeles
        cuadroTexto.setPreferredSize(nuevaDimension);

        Insets margen = new Insets(5, 5, 5, 5);

        constraints.insets = margen;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(cuadroTexto, constraints);
        JButton btn = new JButton("ejemplo");

            constraints.gridx = 5;
            constraints.gridy = 2;
            constraints.gridwidth = 1;
            Color colorAleatorio = new Color(
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256));

            // Establece el color de fondo del botón al color aleatorio
            btn.setBackground(colorAleatorio);


        constraints.insets = new Insets(0, 0, 0, 0);;
        String[] botones = { "C", "CA", "M", "M+", "ANS", "(",")","1/x","sin()", "cos()", "tan()", "ln()", "exp()", "arcsin()","arccos()","arctan()","e","π ","√ ","^", "7","8","9","*", "4","5","6","+","3","2","1","-",".","0","=","e"};
        int i = 0;
        for (String botonString : botones) {
            JButton boton = new JButton(botonString);
           
            // Cambiar el tamaño del botón
            boton.setPreferredSize(new Dimension(120, 50));
            constraints.gridx = i % 4;
            constraints.gridy = i / 4 + 1;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.NONE;
            colorAleatorio = new Color(
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256));

            // Establece el color de fondo del botón al color aleatorio
            boton.setBackground(colorAleatorio);

            boton.addActionListener(new EventoBotonPulsado(cuadroTexto, this));
            contentPane.add(boton, constraints);
            i++;

        }

        

    }
}
class EventoBotonPulsado implements ActionListener{
    private JTextField cuadroTexto;
    private MiVentana ventana; // Agregamos una referencia a la ventana

    public EventoBotonPulsado(JTextField cuadroTexto, MiVentana ventana) {
        this.cuadroTexto = cuadroTexto;
        this.ventana = ventana; // Inicializamos la referencia a la ventana
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // getSource obtiene la fuente del evento, es decir, que boton se
        // presiono, retorna un objeto de la clase Object que transformamos en JButton
        JButton boton = (JButton) e.getSource();
        String botonString = boton.getText();
        String cuadroTextoString = cuadroTexto.getText();

        
        if(botonString.equals("CA")){
            cuadroTexto.setText("");
            
        }
        else if(botonString.equals("C")){
            cuadroTexto.setText(cuadroTextoString.substring(0, cuadroTextoString.length() - 1));
        }
        else if(botonString.equals("ANS")){
            cuadroTexto.setText(cuadroTextoString + ventana.resultado);
            
        }

       
       

        else if (botonString.equals("=")) {
            double r;
            if(cuadroTextoString.charAt(0) == 's'
               ||cuadroTextoString.charAt(0) == 'c'
               ||cuadroTextoString.charAt(0) == 't'
               ||cuadroTextoString.substring(0, 3).equals("exp")){
                cuadroTexto.setText(ventana.resultado);
            }
            
            else{

               
                    r = Resolver.resolver(cuadroTexto.getText());
                    ventana.resultado = Double.toString(r);
                    cuadroTexto.setText(ventana.resultado);
               
            }
        } else {
            cuadroTexto.setText(cuadroTextoString + botonString);

        }
    }

}


class ExpresionInvalida extends Exception {
    public ExpresionInvalida(String mensaje) {
        super(mensaje);
    }
}
