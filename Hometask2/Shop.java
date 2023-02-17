import java.util.*;

// Для различных методов по подбору критериев и сортировке по ним сделаем класс Shop - магазин ноутбуков
// и будем обращаться к этим методам через него

// Не буду усложнять доступом к полям, пусть все будут public :)
public class Shop {
    public HashSet<Notebook> notebooks;

    public Shop(HashSet<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    public boolean tryParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean tryParseDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public HashMap<Integer, String> newRequest() {
        HashMap<Integer, String> requestedCriteria = new HashMap<>();

        Scanner scan = new Scanner(System.in);
        System.out.println("Select the criteria that you want to make the request by.");

        for (int i = 1; i < Notebook.criteriaMap.size() + 1; i++) {
            String printCrit = Notebook.criteriaMap.get(i);
            System.out.println("Do you to take " + printCrit + " into account in your request?\nY/N: ");
            String n = scan.nextLine();
            while (true) {
                if (Objects.equals(n, "q") || Objects.equals(n, "Q")) {
                    System.exit(0);
                } else if (Objects.equals(n, "y") || Objects.equals(n, "Y")) {
                    if (Objects.equals("Price", Notebook.criteriaMap.get(i))) {
                        requestedCriteria.put(0, "Price");
                    } else {
                        requestedCriteria.put(i, Notebook.criteriaMap.get(i));
                    }
                    break;
                } else if (Objects.equals(n, "n") || Objects.equals(n, "N")) {
                    break;
                } else {
                    System.out.println("Wrong action! Try again!");
                    System.out.println("Do you to take " + printCrit + " into account in your request?\nY/N: ");
                    n = scan.nextLine();
                }
            }
        }
        return requestedCriteria;
    }

    public HashMap<String, String> newFilter(HashMap<Integer, String> reqCrit) {
        HashMap<String, String> reqChars = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Select the characteristics that you want to filter your request by.");
        for (int critKey : reqCrit.keySet()) {
            if (Objects.equals("Price", reqCrit.get(critKey))) {
                System.out.println("What maximum price of a Notebook do you want?");
                String n = scan.nextLine();
                while (!tryParseInt(n)) {
                    System.out.println("You didn't input a number! Try again!");
                    n = scan.nextLine();
                }
                reqChars.put("Price", n);
            } else {
                System.out.printf("""
                    What %s-s do you want to be used as a filter?
                    Input one number at a time.
                    Type 'f' to stop input the characteristics, 'q' to exit.
                    """, reqCrit.get(critKey));
                for (int charKey = 1; charKey < Notebook.characteristicsMap.size(); charKey++) {
                    if (!Notebook.characteristicsMap.containsKey(critKey + Integer.toString(charKey))) {
                        break;
                    }
                    System.out.printf(Notebook.characteristicsMap.get(critKey + Integer.toString(charKey))
                            + " - %d\n", charKey);
                }
                while (true) {
                    String n = scan.nextLine();
                    if (Objects.equals(n, "q") || Objects.equals(n, "Q")) {
                        System.exit(0);
                    } else if (Notebook.characteristicsMap.containsKey(critKey + n)) {
                        reqChars.put(critKey + n, Notebook.characteristicsMap.get((critKey + n)));
                    } else if (Objects.equals(n, "f") || Objects.equals(n, "F")) {
                        break;
                    } else {
                        System.out.println("No such characteristic! Try again!");
                    }
                }
            }
        }
        return reqChars;
    }


    public HashSet<Notebook> getFilteredNotes (HashMap<String, String> filter) {
        int maxPrice = 100_000_000;
        ArrayList<Integer> fRam = new ArrayList<>();
        ArrayList<Integer> fRom = new ArrayList<>();
        ArrayList<Double> fScreen = new ArrayList<>();
        ArrayList<Boolean> fInStock = new ArrayList<>();
        ArrayList<String> fBrand = new ArrayList<>();
        ArrayList<String> fProc = new ArrayList<>();
        ArrayList<String> fOS = new ArrayList<>();
        ArrayList<String> fColour = new ArrayList<>();

        HashSet<Notebook> filteredNotes = new HashSet<>();
        for (Map.Entry<String, String> entry : filter.entrySet()) {
            if (tryParseInt(entry.getValue())) {
                int intCharacteristic = Integer.parseInt(entry.getValue());
                if (Objects.equals(entry.getKey(), "Price")) {
                    maxPrice = intCharacteristic;
                } else if (Objects.equals(entry.getKey().charAt(0), '2')) {
                    fRam.add(intCharacteristic);
                } else if (Objects.equals(entry.getKey().charAt(0), '3')) {
                    fRom.add(intCharacteristic);
                }
            } else if (tryParseDouble(entry.getValue())) {
                double doubleCharacteristic = Double.parseDouble(entry.getValue());
                fScreen.add(doubleCharacteristic);
            } else if (Objects.equals(entry.getKey().charAt(0), '9')) {
                boolean boolCharacteristic = Boolean.parseBoolean(entry.getValue());
                fInStock.add(boolCharacteristic);
            } else {
                if (Objects.equals(entry.getKey().charAt(0), '1')) {
                    fBrand.add(entry.getValue());
                } else if (Objects.equals(entry.getKey().charAt(0), '4')) {
                    fProc.add(entry.getValue());
                } else if (Objects.equals(entry.getKey().charAt(0), '6')) {
                    fOS.add(entry.getValue());
                } else if (Objects.equals(entry.getKey().charAt(0), '7')) {
                    fColour.add(entry.getValue());
                }else {
                    fColour.add(entry.getValue());
                }
            }
        }

        System.out.println("Your characteristics filter list:");

        System.out.println(fBrand);
        System.out.println(fRam);
        System.out.println(fRom);
        System.out.println(fProc);
        System.out.println(fScreen);
        System.out.println(fOS);
        System.out.println(fColour);
        System.out.println(fInStock);
        System.out.printf("Max. Price %d\n", maxPrice);

        if (fBrand.size() == 0) {
            Collections.addAll(fBrand, Notebook.brands);
        }
        if (fRom.size() == 0) {
            Collections.addAll(fRom, Notebook.roms);
        }
        if (fRam.size() == 0) {
            Collections.addAll(fRam, Notebook.rams);
        }
        if (fScreen.size() == 0) {
            Collections.addAll(fScreen, Notebook.screenDiags);
        }
        if (fProc.size() == 0) {
            Collections.addAll(fProc, Notebook.procs);
        }
        if (fOS.size() == 0) {
            Collections.addAll(fOS, Notebook.oss);
        }
        if (fColour.size() == 0) {
            Collections.addAll(fColour, Notebook.colours);
        }
        if (fInStock.size() == 0) {
            Collections.addAll(fInStock, Notebook.inStocks);
        }

        for (String brand : fBrand){
            for (int ram : fRam){
                for (int rom : fRom){
                    for (String proc : fProc){
                        for (double screen : fScreen){
                            for (String os : fOS){
                                for (String colour : fColour){
                                    for (boolean inStock : fInStock){
                                        for (Notebook note : notebooks) {
                                            if (note.brand.equals(brand) && note.ram == ram && note.rom == rom
                                                    && note.proc.equals(proc) && note.screenDiag == screen
                                                    && note.os.equals(os) && note.colour.equals(colour)
                                                    && note.inStock == inStock && note.price < maxPrice){
                                                filteredNotes.add(note);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return filteredNotes;
    }
}