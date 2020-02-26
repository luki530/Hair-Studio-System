package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class ValuePackagesWomen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public ValuePackagesWomen() {
        this.cost = 85;
        this.minutes = 90;
        this.description = "All Value Packages include a Signature Cut, \n" +
                "Redken Chemistry Treatment and $3 off any regularly priced haircare product.\n" +
                " Additional Value Packages available.\n" +
                "\n" +
                "Signature Cut with Classic Blowout\n" +
                "Signature Cut with Deluxe Blowout\n" +
                "Single Color\n" +
                "Gray Blending\n" +
                "Root Touch-Up with Gloss\n" +
                "Foil Highlights (Partial/Full)\n" +
                "Balayage Highlights (Partial/Full)\n" +
                "Single Color with Foils (Partial or Full)\n" +
                "Single Color with Balayage (Partial or Full)\n" +
                "Relaxer/Texturizer";
        this.name = "Value Packages For Women";
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
