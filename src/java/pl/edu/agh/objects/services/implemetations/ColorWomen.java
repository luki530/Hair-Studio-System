package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class ColorWomen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public ColorWomen() {
        this.cost = 70;
        this.minutes = 120;
        this.description = "All color services include a relaxing shampoo, conditioner and Classic Blowout. \n" +
                "Ask your Salon Professional about our Fashion Colors! \n" +
                "Now available with any color service.\n" +
                "\n" +
                "Single Color\n" +
                "Root Touch Up with Gloss\n" +
                "Foil Highlights or Lowlights (Partial or Full)\n" +
                "Balayage Highlights or Lowlights (Partial or Full)\n" +
                "Single Color with Foils (Partial or Full)\n" +
                "Single Color with Balayage (Partial or Full)\n" +
                "Corrective Color";
        this.name = "Color For Women";
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
