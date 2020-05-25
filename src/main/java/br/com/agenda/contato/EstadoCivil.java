package br.com.agenda.contato;

public enum EstadoCivil {

    SOLTEIRO("Solteiro"), CASADO("Casado"), DIVORCIADO("Divorciado"), VIUVO("Viúvo");

    private final String label;

    public String getLabel() {
        return label;
    }

    EstadoCivil(String label) {
        this.label = label;
    }
}
