package Configurations;

public final class Configurations {
	public static final String filePath = String.join("/", (new java.io.File("").getAbsolutePath()).split("\\\\")); // build the path but with forward slashes
	public static final String path_ImagesAndIcons = Configurations.filePath + "/src/ItemsAndCharacters/IconsAndImages/";
	
	private Configurations() {};
	
}
