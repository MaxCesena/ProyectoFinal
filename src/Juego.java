public class Juego {
    private CajaBolas ballCage;
    private Cartas jugadorCarta;
    private PatronInterfaz pattern; // Usar interfaz para flexibilidad


    public Juego(PatronInterfaz selectedPattern) {
        this.ballCage = new CajaBolas();
        this.pattern = selectedPattern;
        this.jugadorCarta = new Cartas();
    }

    public CajaBolas getCajaBolas() {
        return ballCage;
    }

    public Cartas getCartas() {
        return jugadorCarta;
    }

    public boolean hayGanador() {
        return pattern.patronCorrecto(jugadorCarta);
    }
}
