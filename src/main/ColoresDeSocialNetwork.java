public enum ColoresDeSocialNetwork {
    BLACK("\u001B[30m"),
    WHITE("\u001B[37m"),
    BLUE("\u001B[38;2;40;177;249m"),
    RED("\u001B[38;2;255;100;100m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33;2;40;177;249m"),
    RESET("\u001B[0m");

    private final String color;

    ColoresDeSocialNetwork(String code) {
        this.color = code;
    }
    public String getCode(){
        return this.color;
    }
}
