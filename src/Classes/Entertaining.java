package Classes;

import java.util.ArrayList;

public class Entertaining extends Guidance {
    private ArrayList<Feature> features;

    public Entertaining(){

    }

    public Entertaining(ArrayList<Feature> features) {
        this.features = features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }
}
