import java.util.*;

@SuppressWarnings("ALL")
public class LaptopFilterApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, String> filters = new HashMap<>();
        Set<Laptop> laptops = getLaptopData();

        while (true) {
            displayFilters(filters);
            int choice = displayCriteria();

            if (choice == 1) {
                String ram = input.next();
                filters.put("ram", ram);
            } else if (choice == 2) {
                String hdd = input.next();
                filters.put("hdd", hdd);
            } else if (choice == 3) {
                String os = input.next();
                filters.put("os", os);
            } else if (choice == 4) {
                String color = input.next();
                filters.put("color", color);
            } else {
                System.out.println("Неверный выбор. Повторите ввод.");
                continue;
            }

            System.out.println("Продолжить выбор? (да/нет)");
            String continueInput = input.next();
            if (continueInput.equalsIgnoreCase("нет")) {
                break;
            }
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, filters);
        displayFilteredLaptops(filteredLaptops);

        input.close();
    }

    public static Set<Laptop> getLaptopData() {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("ModelA", 8, 512, "Windows", "Black"));
        laptops.add(new Laptop("ModelB", 16, 256, "Windows", "Silver"));
        laptops.add(new Laptop("ModelC", 8, 1024, "Linux", "Black"));
        // ... добавьте остальные данные ноутбуков ...
        return laptops;
    }

    public static int displayCriteria() {
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        return new Scanner(System.in).nextInt();
    }

    public static void displayFilters(Map<String, String> filters) {
        if (!filters.isEmpty()) {
            System.out.println("Выбранные критерии:");
            for (Map.Entry<String, String> entry : filters.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, String> filters) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        for (Map.Entry<String, String> filter : filters.entrySet()) {
            String key = filter.getKey();
            String value = filter.getValue();

            switch (key) {
                case "ram":
                    int ram = Integer.parseInt(value);
                    filteredLaptops.removeIf(laptop -> laptop.getRam() != ram);
                    break;
                case "hdd":
                    int hdd = Integer.parseInt(value);
                    filteredLaptops.removeIf(laptop -> laptop.getHdd() != hdd);
                    break;
                case "os":
                    filteredLaptops.removeIf(laptop -> !laptop.getOs().equalsIgnoreCase(value));
                    break;
                case "color":
                    filteredLaptops.removeIf(laptop -> !laptop.getColor().equalsIgnoreCase(value));
                    break;
            }
        }
        return filteredLaptops;
    }

    public static void displayFilteredLaptops(Set<Laptop> filteredLaptops) {
        if (filteredLaptops.isEmpty()) {
            System.out.println("Нет ноутбуков, соответствующих указанным критериям.");
        } else {
            System.out.println("Ноутбуки, соответствующие критериям:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }
}

class Laptop {
    private final String model;
    private final int ram;
    private final int hdd;
    private final String os;
    private final String color;

    public Laptop(String model, int ram, int hdd, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Модель: " + model + ", ОЗУ: " + ram + " ГБ, ЖД: " + hdd + " ГБ, ОС: " + os + ", Цвет: " + color;
    }
}