package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class ExtrasWomen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public ExtrasWomen() {
        this.cost = 200;
        this.minutes = 120;
        this.description = "Relaxing Scalp Massage\n" +
                "Redken Chemistry Treatment\n" +
                "Redken pH-Bonder Treatment\n" +
                "Clarifying Treatment\n" +
                "Curling/Flat Iron Finish\n" +
                "Curling/Flat Iron Set\n" +
                "Clipper Design\n" +
                "Deluxe Clipper Design\n" +
                "Hair Extensions\n" +
                "Hair Removal";
        this.name = "Extras For Women";
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
