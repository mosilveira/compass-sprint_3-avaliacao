package uol.compass.avaliacao.config;

public class FormValidationErrorDTO {

    private String field;
    private String error;

    public FormValidationErrorDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
