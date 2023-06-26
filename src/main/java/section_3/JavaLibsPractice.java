package section_3;

import javax.swing.JOptionPane;

public class JavaLibsPractice {
    public static void main(String[] args) {
        // Accept user inputs
        String name = JOptionPane.showInputDialog(null, "Digite seu nome:");
        int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite sua idade:"));
        double distanceKm = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite uma distância (em quilômetros):"));
        String favoriteColor = JOptionPane.showInputDialog(null, "Digite sua cor favorita:");
        int birthYear = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ano do seu nascimento:"));
        double temperature = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a temperatura (em graus Celsius):"));

        String[] options = {"Calor", "Frio"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Com essa temperatura você sente calor ou frio?",
                "Opções",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        String animal = JOptionPane.showInputDialog(null, "Digite seu animal favorito:");
        int numberOfSiblings = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número de irmãos que você tem:"));
        String city = JOptionPane.showInputDialog(null, "Digite o nome de uma cidade:");
        String adjective = JOptionPane.showInputDialog(null, "Digite um adjetivo:");

        int newAge = age + 5;
        double distanceMiles = distanceKm * 0.621371;
        String thermalSensation;
        if (choice == 0) {
            thermalSensation = "calor";
        } else {
            thermalSensation = "frio";
        }

        // Generate the story
        String story = "Era uma vez uma pessoa chamada " + name + ".\n";
        story += "Com a idade de " + age + " anos, " + name + " embarcou em uma jornada incrível.\n";
        story += "Eles percorreram uma distância de " + distanceMiles + " milhas, explorando novas terras e vivendo aventuras emocionantes.\n";
        story += "Durante sua jornada, " + name + " estava acompanhado de seu leal " + animal + " " + favoriteColor + ".\n";
        story += "Nascido no ano de " + birthYear + ", " + name + " possuía sabedoria além de seus anos.\n";
        story += "Eles enfrentaram diversos desafios ao longo do caminho, mas o espírito " + adjective + " de " + name + " nunca fraquejou.\n";
        story += "Na cidade de " + city + ", " + name + " descobriu um tesouro escondido que enriqueceu sua alma.\n";
        story += "Com " + numberOfSiblings + " irmãos, " + name + " aprendeu a importância da família e da amizade.\n";
        story += "Ao retornar para casa com seus " + newAge + " anos, a temperatura era de " + temperature + " graus Celsius, mas " + name + " não se incomodava com o " + thermalSensation + ".\n";
        story += "E assim, a incrível jornada de " + name + " chegou ao fim, deixando para trás memórias que durariam para sempre.";

        // Display the story to the user
        JOptionPane.showMessageDialog(null, story);
    }
}