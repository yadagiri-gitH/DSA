package com.cracking.coding.interview.stack;

import java.util.LinkedList;

class Animal {
    public String name;
    public int order;

    public Animal(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isOlderThan(Animal a) {
        return this.order < a.order;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}

public class AnimalShelter {
    private int orderId;
    private LinkedList<Animal> dogs = new LinkedList<>();
    private LinkedList<Animal> cats = new LinkedList<>();

    public void enqueue(Animal animal) {
        if (animal == null)
            throw new RuntimeException("Please bring the animal that you want Animal Shelter to adopt");

        animal.setOrder(orderId++);

        if (animal instanceof Dog) {
            dogs.addLast(animal);
        } else if (animal instanceof Cat) {
            cats.addLast(animal);
        }
    }

    public Animal dequeueDog() {
        if (dogs.isEmpty()) throw new RuntimeException("No dogs in shelter");
        return dogs.poll();
    }

    public Animal dequeueCat() {
        if (cats.isEmpty()) throw new RuntimeException("No cats in shelter");
        return cats.poll();
    }

    public Animal dequeueAnyAnimal() {
        if (dogs.size() == 0) {
            return dequeueCat();
        }
        if (cats.size() == 0) {
            return dequeueDog();
        }

        Dog dog = (Dog) dogs.peek();
        Cat cat = (Cat) cats.peek();

        if (dog.isOlderThan(cat)) {
            return dequeueDog();
        } else
            return dequeueCat();
    }

    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        shelter.enqueue(new Dog("Doggo1"));
        shelter.enqueue(new Cat("Kitty1"));
        shelter.enqueue(new Dog("Doggo2"));
        shelter.enqueue(new Cat("Kitty2"));
        shelter.enqueue(new Dog("Doggo3"));

        System.out.println("Dequeue Any: " + shelter.dequeueAnyAnimal().name); // Expected: Doggo1
        System.out.println("Dequeue Cat: " + shelter.dequeueCat().name);       // Expected: Kitty1
        System.out.println("Dequeue Dog: " + shelter.dequeueDog().name);       // Expected: Doggo2
        System.out.println("Dequeue Any: " + shelter.dequeueAnyAnimal().name); // Expected: Kitty2
        System.out.println("Dequeue Any: " + shelter.dequeueAnyAnimal().name); // Expected: Doggo3

        // Optional: Try dequeue from empty lists to test safety
        try {
            System.out.println("Dequeue Any: " + shelter.dequeueAnyAnimal().name);
        } catch (Exception e) {
            System.out.println("No more animals: " + e.getMessage());
        }
    }
}
