package roulette.adnancalgin.pc.ceprulet;


public class Wheel {

    private int number;
    private ColorState colorState;
    private NumberState numberState;
    private BetweenState betweenState;

    enum ColorState {
        BLACK,RED,GREEN
    }

    enum NumberState {
        ODD,EVEN
    }

    enum BetweenState {
        FIRSTHALF,SECONDHALF
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;

            if (number %2 == 0){
            setNumberState(NumberState.EVEN);
        }
           else if (number %2 == 1){
            setNumberState(NumberState.ODD);
        }
    }

    public int getHalf() { return number; }

    public void setBetween(int number){
        this.number = number;

        if (number <= 18 ) {
            setBetweenState(BetweenState.FIRSTHALF);
        }

        else if (number >= 19) {
            setBetweenState(BetweenState.SECONDHALF);
        }
    }

    public ColorState getColorState() {
        return colorState;
    }

    public void setColorState(ColorState colorState) {
        this.colorState = colorState;
    }

    public NumberState getNumberState() {
        return numberState;
    }

    public void setNumberState(NumberState numberState) {
        this.numberState = numberState;
    }

    public BetweenState getBetweenState(){
        return betweenState;
    }

    public void setBetweenState (BetweenState betweenState) {
        this.betweenState = betweenState;
    }
}

