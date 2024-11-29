import javax.swing.*;
import java.awt.*;

public class PatronVentana extends JFrame {
    private PatronB patronEle;

    public PatronVentana() {
        setTitle("Seleccione un Patrón de Bingo");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3, 10, 10)); // Ajustamos para incluir más patrones

        // Botones para patrones básicos y avanzados
        JButton botonH = new JButton("Horizontalmente");
        botonH.addActionListener(e -> patronHorizontal());

        JButton botonV = new JButton("Verticalmente");
        botonV.addActionListener(e -> patronVertical());

        JButton botonCruz = new JButton("Cruzado");
        botonCruz.addActionListener(e -> patronDiagonal());

        JButton cartaCompleta = new JButton("Toda la carta");
        cartaCompleta.addActionListener(e -> escojePatron(PatronB.createFullCardPattern()));

        JButton packDe6V = new JButton("Pack de 6 vertical");
        packDe6V.addActionListener(e -> pack6V());

        JButton pachDe6H = new JButton("Pack de 6 Horizontal");
        pachDe6H.addActionListener(e -> pack6H());

        JButton botonDiamante = new JButton("Diamante");
        botonDiamante.addActionListener(e -> escojePatron(PatronB.patronDiamante()));

        JButton botonCentrado = new JButton("Cuadrado centro");
        botonCentrado.addActionListener(e -> escojePatron(PatronB.cuadradoCentral()));


        // Añadir botones al layout
        add(botonH);
        add(botonV);
        add(botonCruz);
        add(cartaCompleta);
        add(packDe6V);
        add(pachDe6H);
        add(botonDiamante);
        add(botonCentrado);


        setLocationRelativeTo(null); // Centrar la ventana
    }

    private void escojePatron(PatronB pattern) {
        patronEle = pattern;
        setVisible(false);
        new Visual(patronEle).setVisible(true);
        dispose();
    }

    private void patronHorizontal() {
        String rowInput = JOptionPane.showInputDialog(this, "Escoja de donde quiere tomar su fila (0-4):");
        try {
            int row = Integer.parseInt(rowInput);
            if (row >= 0 && row < 5) {
                escojePatron(PatronB.createHorizontalPattern(row));
            } else {
                JOptionPane.showMessageDialog(this, "ERROR. Solo entre 0 a 4.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, " ERROR. Numero de 0 a 4.");
        }
    }

    private void patronVertical() {
        String colInput = JOptionPane.showInputDialog(this, "Escoja de donde quiere tomar su columna (0-4):");
        try {
            int col = Integer.parseInt(colInput);
            if (col >= 0 && col < 5) {
                escojePatron(PatronB.createVerticalPattern(col));
            } else {
                JOptionPane.showMessageDialog(this, "ERROR. Tome de 0 a 4.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ERROR. Solo de 0 a 4.");
        }
    }

    private void patronDiagonal() {
        String[] options = {"De la Izquierda hasta la Derecha", "De Derecha hasta la Izquierda"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Que orientacion tiene su diagonal?:",
                "Escoja su orientacion",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            escojePatron(PatronB.createDiagonalPattern("leftToRight"));
        } else if (choice == 1) {
            escojePatron(PatronB.createDiagonalPattern("rightToLeft"));
        }
    }

    private void pack6V() {
        String rowInput = JOptionPane.showInputDialog(this, "Escoja el numero de fila que quiere (0-3):");
        String colInput = JOptionPane.showInputDialog(this, "Escoja el numero de columna que quiere (0-2):");
        try {
            int row = Integer.parseInt(rowInput);
            int col = Integer.parseInt(colInput);
            if (row >= 0 && row < 4 && col >= 0 && col < 3) {
                escojePatron(PatronB.create6PackVertical(row, col));
            } else {
                JOptionPane.showMessageDialog(this, "ERROR.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ERROR.");
        }
    }

    private void pack6H() {
        String rowInput = JOptionPane.showInputDialog(this, "Escoja el numero de fila que quiere (0-2):");
        String colInput = JOptionPane.showInputDialog(this, "Escoja el numero de columna que quiere (0-3):");
        try {
            int row = Integer.parseInt(rowInput);
            int col = Integer.parseInt(colInput);
            if (row >= 0 && row < 3 && col >= 0 && col < 4) {
                escojePatron(PatronB.create6PackHorizontal(row, col));
            } else {
                JOptionPane.showMessageDialog(this, "ERROR.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ERROR.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatronVentana().setVisible(true));
    }
}