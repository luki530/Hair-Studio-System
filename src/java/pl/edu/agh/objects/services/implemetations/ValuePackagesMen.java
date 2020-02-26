package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class ValuePackagesMen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public ValuePackagesMen() {
        this.cost = 50;
        this.minutes = 30;
        this.description = "All Value Packages include a Signature Cut, \n" +
                "Redken Chemistry Treatment and $3 off any regularly priced haircare product. \n" +
                "Additional Value Packages available.\n" +
                "\n" +
                "Signature Cut with Classic Blowout\n" +
                "Single Color\n" +
                "Gray Blending\n" +
                "Root Touch-Up with Gloss\n" +
                "Foil Highlights (Partial/Full)\n" +
                "Balayage Highlights (Partial/Full)";
        this.name = "Value Packages For Men";
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
