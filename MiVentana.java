import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
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



    }
}