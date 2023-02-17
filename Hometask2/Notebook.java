import java.util.HashMap;

public class Notebook {
    // ID нужен для "симуляции" БД, чтобы при дальнейше сортировке выводить только id определенного ноутбука
    public final Integer id;
    protected String brand;
    protected Integer ram;
    protected Integer rom;
    protected String proc;
    protected double screenDiag;
    protected String os;
    protected String colour;
    public Integer price;
    public Boolean inStock;

    public static HashMap<Integer, String> criteriaMap;
    public static HashMap<String, String> characteristicsMap;


    // Создадим массивы вариантов характеристик (цену пропустим) для удобства случайной генерации множества ноутбуков
    public static String[] brands = new String[]{"Lenovo", "HP", "Asus", "Acer", "Xiaomi", "Apple"};
    public static Integer[] rams = new Integer[]{4, 8, 16, 32};
    public static Integer[] roms = new Integer[]{256, 512, 1024};
    public static String[] procs = new String[]{"Intel", "AMD", "Apple"};
    public static Double[] screenDiags = new Double[]{14d, 15.6d, 17d};
    public static String[] oss = new String[]{"Windows", "MacOS", "Linux", "DOS"};
    public static String[] colours = new String[]{"Black", "Blue", "White", "Gold"};
    public static Boolean[] inStocks = new Boolean[]{true, false};

    public static String[] criteria = new String[] {"Brand", "RAM", "ROM", "Processor", "Screen Diagonal",
            "OS", "Colour", "Price", "In Stock"};



    // Конструктор
    public Notebook(Integer id, String brand, Integer ram, Integer rom,
                    String proc, double screenDiag, String os, String colour, Boolean inStock, Integer price) {
        this.id = id;
        this.brand = brand;
        this.ram = ram;
        this.rom = rom;
        this.proc = proc;
        this.screenDiag = screenDiag;
        this.os = os;
        this.colour = colour;
        this.price = price;
        this.inStock = inStock;

        HashMap<Integer, String> criteriaMap = new HashMap<>();
        for (int i = 0; i < Notebook.criteria.length; i++) {
            criteriaMap.put(i+1, Notebook.criteria[i]);
        }
        Notebook.criteriaMap = criteriaMap;

        HashMap<String, String> characteristicsMap = new HashMap<>();
        for (int critKey : criteriaMap.keySet()) {
            switch (critKey) {
                case 1:
                    for (int j = brands.length; j > 0; j--) {
                        characteristicsMap.put(critKey + Integer.toString(j), brands[j-1]);
                    }
                    break;
                case 2:
                    for (int j = rams.length; j > 0 ; j--) {
                        characteristicsMap.put(critKey + Integer.toString(j), String.valueOf(rams[j-1]));
                    }
                    break;
                case 3:
                    for (int j = roms.length; j > 0 ; j--) {
                        characteristicsMap.put(critKey + Integer.toString(j), String.valueOf(roms[j-1]));
                    }
                    break;
                case 4:
                    for (int j = procs.length; j > 0 ; j--) {
                        characteristicsMap.put(critKey + Integer.toString(j), procs[j-1]);
                    }
                    break;
                case 5:
                    for (int j = screenDiags.length; j > 0 ; j--) {
                        characteristicsMap.put(critKey + Integer.toString(j), Double.toString(screenDiags[j-1]));
                    }
                    break;
                case 6:
                    for (int j = oss.length; j > 0 ; j--) {
                        characteristicsMap.put(critKey + Integer.toString(j), oss[j-1]);
                    }
                    break;
                case 7:
                    for (int j = colours.length; j > 0 ; j--) {
                        characteristicsMap.put(critKey + Integer.toString(j), colours[j-1]);
                    }
                    break;
                case 9:
                    for (int j = inStocks.length; j > 0 ; j--) {
                        characteristicsMap.put(critKey + Integer.toString(j), String.valueOf(inStocks[j-1]));
                    }
                    break;
            }
        }
        Notebook.characteristicsMap = characteristicsMap;
    }

    // Красивый вывод характеристик
    public StringBuilder GetCharacteristics() {
        StringBuilder outStr = new StringBuilder();
        outStr.append("Notebook ").append(id).append(":\n");

        outStr.append("\t").append("Brand: ");
        outStr.append(brand).append("\n");

        outStr.append("\t").append("Ram: ");
        outStr.append(ram).append(" MB").append("\n");

        outStr.append("\t").append("ROM: ");
        outStr.append(rom).append(" GB").append("\n");

        outStr.append("\t").append("Processor Brand: ");
        outStr.append(proc).append("\n");

        outStr.append("\t").append("Screen Diagonal: ");
        outStr.append(screenDiag).append("\n");

        outStr.append("\t").append("Operating System: ");
        outStr.append(os).append("\n");

        outStr.append("\t").append("Colour: ");
        outStr.append(colour).append("\n");

        outStr.append("\t").append("Price: ");
        outStr.append(price).append("\n");

        outStr.append("\t").append("In Stock: ");
        outStr.append(inStock).append("\n");

        return outStr;
    }
}