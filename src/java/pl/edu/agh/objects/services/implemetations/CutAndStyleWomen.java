package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class CutAndStyleWomen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public CutAndStyleWomen() {
        this.cost = 150;
        this.minutes = 90;
        this.description = "All cut and blowout services include a relaxing shampoo and conditioner.\n" +
                "\n" +
                "Buzz Cut (single guard, no shampoo)\n" +
                "Signature Cut\n" +
                "Classic Blowout\n" +
                "Signature Cut with Classic Blowout\n" +
                "Deluxe Blowout\n" +
                "Signature Cut with Deluxe Blowout\n" +
                "Classic Set or Wrap\n" +
                "Classic Up Do\n" +
                "Special Occasion Design";
        this.name = "Cut And Style For Women";
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public long getMinutes() {
        return minutes;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }
}
