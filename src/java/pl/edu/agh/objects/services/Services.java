package pl.edu.agh.objects.services;

public abstract class Services {
    private long minutes;
    private double cost;
    private String description;
    public String name;

    public void Services() {
    }

    public double getCost() {
        return cost;
    }

    public long getMinutes() {
        return minutes;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
