import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Visual extends JFrame {
    private Juego juego;
    private JLabel ganador;
    private JButton botonBola;
    private JButton verCarta;
    private Map<Integer, JLabel> almacenaJ;
    private JFrame cartaVisual;
    private JPanel cartaPanel;

    public Visual(PatronInterfaz selectedPattern) {
        juego = new Juego(selectedPattern);
        almacenaJ = new HashMap<>();
        setupFrame();
    }

    private void setupFrame() {
        setTitle("¡Bienvenido al Juego de Bingo! ");
        setSize(1200, 700); // Tamaño más amplio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear panel superior (Botones y mensajes)
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(20, 100, 120)); // Fondo oscuro

        botonBola = boton(" Sacar Bola");
        botonBola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bolaDibujada();
            }
        });

        mostrarCarta();

        ganador = new JLabel("", SwingConstants.CENTER);
        ganador.setFont(new Font("Verdana", Font.BOLD, 20));
        ganador.setForeground(Color.YELLOW);

        topPanel.add(botonBola);

        topPanel.add(ganador);

        // Crear el panel central (Tómbola)
        JPanel tombolaPanel = tombola();

        add(topPanel, BorderLayout.NORTH);
        add(tombolaPanel, BorderLayout.CENTER);
    }

    private JPanel tombola() {
        JPanel tombolaPanel = new JPanel();
        tombolaPanel.setLayout(new BorderLayout());
        tombolaPanel.setBackground(new Color(50, 150, 200)); // Fondo del panel

        // Letras BINGO
        JPanel lettersPanel = new JPanel(new GridLayout(5, 1, 0, 10));
        String[] letters = {"B", "I", "N", "G", "O"};
        Color[] colors = {new Color(212, 102, 255), new Color(248, 86, 208), new Color(248, 15, 125), new Color(112, 253, 217), new Color(213, 255, 175)};

        for (int i = 0; i < letters.length; i++) {
            JLabel letterLabel = new JLabel(letters[i], SwingConstants.CENTER);
            letterLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
            letterLabel.setOpaque(true);
            letterLabel.setBackground(colors[i]);
            letterLabel.setForeground(Color.WHITE);
            letterLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            lettersPanel.add(letterLabel);
        }

        JPanel numbersPanel = new JPanel(new GridLayout(15, 5, 5, 5));
        numbersPanel.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < 75; i++) {
            JLabel numberLabel = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            numberLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
            numberLabel.setOpaque(true);
            numberLabel.setBackground(new Color(160, 219, 205));
            numberLabel.setForeground(Color.BLACK);
            numberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            almacenaJ.put(i + 1, numberLabel);
            numbersPanel.add(numberLabel);
        }

        tombolaPanel.add(lettersPanel, BorderLayout.WEST);
        tombolaPanel.add(numbersPanel, BorderLayout.CENTER);
        tombolaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return tombolaPanel;
    }

    private JButton boton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        button.setBackground(new Color(255, 140, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    private void bolaDibujada() {
        int newBall = juego.getCajaBolas().Bola();
        if (almacenaJ.containsKey(newBall)) {
            JLabel label = almacenaJ.get(newBall);
            label.setBackground(Color.YELLOW);
            label.setFont(label.getFont().deriveFont(Font.BOLD, 20f)); // Resaltar
        }

        juego.getCartas().markNumber(newBall);

        if (juego.hayGanador()) {
            showWinnerMessage();
        }

        if (cartaVisual != null && cartaVisual.isVisible()) {
            mejoraCarta();
        }
    }

    private void showWinnerMessage() {
        ganador.setText("||BINGOOOOOOOOOOOOOO!!!||");
        botonBola.setEnabled(false);
    }

    private void mostrarCarta() {

            cartaVisual = new JFrame("Juego Bingo!!!");
            cartaVisual.setSize(500, 500);
            cartaVisual.setLayout(new BorderLayout());
            cartaVisual.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel headerPanel = new JPanel(new GridLayout(1, 5));
            String[] bingoHeader = {"B", "I", "N", "G", "O"};
            for (String letter : bingoHeader) {
                JLabel headerLabel = new JLabel(letter, SwingConstants.CENTER);
                headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
                headerLabel.setOpaque(true);
                headerLabel.setBackground(new Color(223, 220, 56));
                headerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                headerPanel.add(headerLabel);
            }

            cartaPanel = new JPanel(new GridLayout(5, 5, 5, 5));
            mejoraCarta();

            cartaVisual.add(headerPanel, BorderLayout.NORTH);
            cartaVisual.add(cartaPanel, BorderLayout.CENTER);
            cartaVisual.setVisible(true);

    }

    private void mejoraCarta() {
        cartaPanel.removeAll();

        int[][] cardNumbers = juego.getCartas().getCardNumbers();
        boolean[][] markedNumbers = juego.getCartas().getMarkedNumbers();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String text = (i == 2 && j == 2) ? "Free" : String.valueOf(cardNumbers[i][j]);
                JLabel numberLabel = new JLabel(text, SwingConstants.CENTER);
                numberLabel.setOpaque(true);
                numberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                numberLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
                numberLabel.setPreferredSize(new Dimension(60, 60));

                if (markedNumbers[i][j]) {
                    numberLabel.setBackground(new Color(255, 0, 106));
                } else {
                    numberLabel.setBackground(Color.WHITE);
                }
                cartaPanel.add(numberLabel);
            }
        }

        cartaPanel.revalidate();
        cartaPanel.repaint();
    }
}
