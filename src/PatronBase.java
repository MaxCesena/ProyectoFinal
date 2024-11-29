public abstract class PatronBase implements PatronInterfaz {
    protected boolean[][] patternMatrix;

    public PatronBase(boolean[][] patternMatrix) {
        this.patternMatrix = patternMatrix;
    }

    @Override
    public boolean[][] getMatrizP() {
        return patternMatrix;
    }

    @Override
    public boolean patronCorrecto(Cartas card) {
        boolean[][] markedNumbers = card.getMarkedNumbers();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (patternMatrix[i][j] && !markedNumbers[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
