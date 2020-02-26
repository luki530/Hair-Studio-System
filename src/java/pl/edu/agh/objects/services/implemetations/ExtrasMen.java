package pl.edu.agh.objects.services.implemetations;

import pl.edu.agh.objects.services.Services;

public class ExtrasMen extends Services {
    private long minutes;
    private double cost;
    private String description;
    private String name;

    public ExtrasMen() {
        this.cost = 170;
        this.minutes = 90;
        this.description = "Relaxing Scalp Massage\n" +
                "Redken Chemistry Treatment\n" +
                "Redken pH-Bonder Treatment\n" +
                "Clarifying Treatment\n" +
                "Hair Removal";
        this.name = "Extras For Men";
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
