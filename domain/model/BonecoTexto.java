package domain.model;

public class BonecoTexto implements Boneco {

    @Override
    public void exibir(int numErros) {
        String[] partesBoneco = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
        };

        if (numErros >= 0 && numErros < partesBoneco.length) {
            System.out.println(partesBoneco[numErros]);
        } else if (numErros >= partesBoneco.length) {
            System.out.println(partesBoneco[partesBoneco.length - 1]);
        }
    }
}

