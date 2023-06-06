package Other;

import java.util.Random;

public class RandomTodoGenerator {
    private static final String[] todoVerbs = {"Buy", "Clean", "Fix", "Create", "Read", "Call"};
    private static final String[] todoNouns = {"groceries", "house", "car", "artwork", "book", "friend"};

    private Random random;

    public RandomTodoGenerator() {
        random = new Random();
    }

    public String generateRandomTodo() {
        String verb = getRandomElement(todoVerbs);
        String noun = getRandomElement(todoNouns);
        return verb + " " + noun;
    }

    private String getRandomElement(String[] array) {
        int index = random.nextInt(array.length);
        return array[index];
    }
}
