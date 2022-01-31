package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WilliamJUnit {

    @Test
    public void testEasy() {
        Controller.setDifficulty(1);
        InitialGameScreen gameScreen = new InitialGameScreen(839, 586);
        assertEquals(1, gameScreen.getDiff());
    }

    @Test
    public void testMedium() {
        Controller.setDifficulty(2);
        InitialGameScreen gameScreen = new InitialGameScreen(839, 586);
        assertEquals(2, gameScreen.getDiff());
    }
    @Test
    public void testHard() {
        Controller.setDifficulty(3);
        InitialGameScreen gameScreen = new InitialGameScreen(839, 586);
        assertEquals(3, gameScreen.getDiff());
    }


}