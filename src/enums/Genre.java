package enums;

public enum Genre {
    FICTION, NON_FICTION, FANTASY, HORROR, SCIENCE_FICTION,
    ROMANCE, MYSTERY, HISTORY, BIOGRAPHY, OTHER;

    public static Genre fromString(String str) {
        try {
            return Genre.valueOf(str.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }
}
