package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class CutAndStyleMen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public CutAndStyleMen() {
        this.cost = 100;
        this.minutes = 45;
        this.description = "All cut and blowout services include a relaxing shampoo and conditioner.\n" +
                "\n" +
                "Buzz Cut (single guard, no shampoo)\n" +
                "Signature Cut\n" +
                "Signature Cut with Classic Blowout\n" +
                "Tea Tree VIP (scalp massage & Signature Cut)\n" +
                "Edge Up (beard, hairline and neck shape-up)\n" +
                "Clipper Design\n" +
                "Deluxe Clipper Design";
        this.name = "Cut And Style For Men";
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
