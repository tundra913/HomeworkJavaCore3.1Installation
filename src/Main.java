import java.io.*;
import java.util.*;
import java.util.zip.*;


public class Main {
    public static void main(String[] args) {
        List<File> dirs = new ArrayList<>();
        List<File> files = new ArrayList<>(); //создаем два листа, один для директорий, второй для файлов, тк способ создания у них разный

        addDir(dirs);
        addFile(files);  //добавляем необходимые дирректории и файлы

        String dirLogText = dirLog(dirs);
        String fileLogText = fileLog(files); //собираем логи по директориям и файлам

        writeLog(dirLogText);
        writeLog(fileLogText); //записываем логи в текстовый файл
    }

    private static void addDir(List<File> dirs) {
        dirs.add(new File("M://Games"));
        dirs.add(new File("M://Games//src"));
        dirs.add(new File("M://Games//res"));
        dirs.add(new File("M://Games//savegames"));
        dirs.add(new File("M://Games//temp"));
        dirs.add(new File("M://Games//src//main"));
        dirs.add(new File("M://Games//src//test"));
        dirs.add(new File("M://Games//res//drawables"));
        dirs.add(new File("M://Games//res//vectors"));
        dirs.add(new File("M://Games//res//icons"));
    }

    private static void addFile(List<File> files) {
        files.add(new File("M://Games//src//main//Main.java"));
        files.add(new File("M://Games//src//main//Utils.java"));
        files.add(new File("M://Games//temp//temp.txt"));
    }

    private static String dirLog(List<File> dirs) {
        StringBuilder sb = new StringBuilder("Логи" + "\n");
        for (File dir : dirs) {
            if (dir.mkdir())
                sb.append("Каталог " + dir.getName() + " был создан" + "\n");
        }
        return sb.toString();
    }

    private static String fileLog(List<File> files) {
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            try {
                if (file.createNewFile())
                    sb.append("Файл " + file.getName() + " был создан" + "\n");
            } catch (IOException ex) {
                sb.append(ex.getMessage() + "\n");
            }
        }
        return sb.toString();
    }

    private static void writeLog(String text) {
        try (FileWriter writer = new FileWriter("M://Games//temp//temp.txt", true)) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //TODO: тут конец задания №1 :)

}