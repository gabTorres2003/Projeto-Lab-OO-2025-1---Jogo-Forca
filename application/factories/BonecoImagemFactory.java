package application.factories;

import domain.model.Boneco;

public class BonecoImagemFactory implements BonecoFactory {
    private static BonecoImagemFactory soleInstance;

    public static BonecoImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoImagemFactory();
        }
        return soleInstance;
    }

    private BonecoImagemFactory() {}

    @Override
    public Boneco getBoneco() {
        return null;
    }
}