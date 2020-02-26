package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class TextureAndCurlWomen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public TextureAndCurlWomen() {
        this.cost = 100;
        this.minutes = 90;
        this.description = "All texture and curl services include a relaxing shampoo,\n" +
                " conditioner and Classic Blowout.\n" +
                "\n" +
                "Relaxer/Texturizer\n" +
                "Perm\n" +
                "Specialty Perm\n" +
                "Keratin";
        this.name = "Texture And Color For Women";

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
