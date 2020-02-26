package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class ColorMen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public ColorMen() {
        this.cost = 50;
        this.minutes = 60;
        this.description = "All color services include a relaxing shampoo, \n" +
                "conditioner and Classic Blowout Ask your Salon Professional about our Fashion Colors! \n" +
                "Now available with any color service.\n" +
                "\n" +
                "Single Color\n" +
                "Root Touch Up with Gloss\n" +
                "Gray Blending\n" +
                "Foil Highlights or Lowlights (Partial or Full)\n" +
                "Balayage Highlights or Lowlights (Partial or Full)";
        this.name = "Color For Men";
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
