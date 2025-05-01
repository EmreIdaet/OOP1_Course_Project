package enums;

public enum Genre {
    /**
     * Enum representing different genres of books.
     * Each genre is represented as a constant.
     */
    FICTION, NON_FICTION, FANTASY, HORROR, SCIENCE_FICTION,
    ROMANCE, MYSTERY, HISTORY, BIOGRAPHY, OTHER;

    /**
     * Converts a string to a Genre enum.
     * If the string does not match any genre, it returns OTHER.
     *
     * @param str The string representation of the genre.
     * @return The corresponding Genre enum or OTHER if not found.
     */
    public static Genre fromString(String str) {
        try {
            return Genre.valueOf(str.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }
}
