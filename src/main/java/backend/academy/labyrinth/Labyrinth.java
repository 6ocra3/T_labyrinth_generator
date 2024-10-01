package backend.academy.labyrinth;

import backend.academy.labyrinth.generators.KraskalGenerator;

public class Labyrinth {
    public Labyrinth(){
        System.out.println("Labyrinth запущен");
        KraskalGenerator generator = new KraskalGenerator();
        generator.generate();
    }
}
