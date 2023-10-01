import javax.swing.*;

public class Calculadora {
    public static void main(String[] args) {
        //Creamos una instancia de la clase Jframe para crear la ventana
        JFrame f = new JFrame("Titulo de ventana");
        //definimos su tamaño en px
        f.setSize(400, 300);
        //definimos el comportamiento que tendra por default al dar click en cerrar
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}