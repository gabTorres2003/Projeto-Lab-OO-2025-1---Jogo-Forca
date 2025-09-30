package application.factories;

import domain.model.Boneco;
import domain.model.BonecoTexto;

public class BonecoTextoFactory implements BonecoFactory {
    private static BonecoTextoFactory soleInstance;

    public static BonecoTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoTextoFactory();
        }
        return soleInstance;
    }

    private BonecoTextoFactory() {}

    @Override
    public Boneco getBoneco() {
        return new BonecoTexto();
    }
}