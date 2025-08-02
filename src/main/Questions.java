package main;

public class Questions {
	private final String[] questions = {
		    "What is the unit of force called?",
		    "What is the speed of light in a vacuum (approximately)?",
		    "Who discovered alternating current?",
		    "Which quantity is measured in ohms?",
		    "What is the unit of frequency?",
		    "What is the name of the device that measures electric current?",
		    "What is the symbol for speed in physics?",
		    "Which of these phenomena is an example of an electromagnetic wave?",
		    "Which physical quantity describes the amount of motion of a body?",
		    "What happens to the temperature of a body that is being heated?"
		};

		private final String[][] answers = {
		    {"Joule", "Newton", "Watt", "Ampere"},
		    {"150,000 km/s", "300,000 km/s", "450,000 km/s", "1,000,000 km/s"},
		    {"Albert Einstein", "Isaac Newton", "Nikola Tesla", "Michael Faraday"},
		    {"Voltage", "Current", "Resistance", "Energy"},
		    {"Ampere", "Volt", "Hertz", "Ohm"},
		    {"Voltmeter", "Ammeter", "Ohmmeter", "Barometer"},
		    {"F", "v", "t", "a"},
		    {"Sound", "Light", "Heat", "Mechanical wave"},
		    {"Energy", "Power", "Momentum", "Mass"},
		    {"Decreases", "Stays the same", "Increases", "Disappears"},
		};

    private final int[] correctAnswers = {1, 1, 2, 2, 2, 1, 1, 1, 2, 2};

    public String[] getQuestions() {
        return questions;
    }

    public String[][] getAnswers() {
        return answers;
    }

    public int[] getCorrectAnswers() {
        return correctAnswers; 
    }
}
