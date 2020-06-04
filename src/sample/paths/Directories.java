package sample.paths;

public enum Directories {

    INND("indd"),
    DOCUMENT_FONTS("Document fonts"),
    IDML("idml"),
    LINKS("links"),
    PDF("pdf");

    private String name;

    Directories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
