package org.giufus;

import java.util.Optional;
import java.util.stream.Stream;

record NoName(int age) implements Runnable {
    @Override
    public void run() {
        var v = String.join(" - ", "John Doe", Optional.ofNullable(this.age()).orElse(0).toString());
        System.out.println("Can't run: " + v);
    }
}

record WithName(String name, Number age) implements Runnable {

    @Override
    public void run() {
        var v = String.join(" - ", this.name(), Optional.ofNullable(this.age()).orElse(0).toString());
        System.out.println("Can run: " + v);
    }

    public static void main(String[] args) {
        Runnable a = new WithName("Mike Bongiorno", 14);
        Runnable b = new NoName(15);
        WithName c = null;

        try {
            Stream.of(a, b, c).forEach(runnable -> {

                switch (runnable) {
                    case null -> throw new IllegalArgumentException("isn't valid, is null");
                    case WithName w -> System.out.println("Hey, I've found a named one");
                    case Runnable r -> r.run();
                }

            });
        } catch (Exception ex) {
            System.out.println(String.format("Recover from exception: error '%s'", ex.getMessage()));
        }



    }
}
