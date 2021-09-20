import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


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

        //TODO: тут конец задания №1 :)

        GameProgress progress1 = new GameProgress(1400000, 50, 12, 105.5); //создали 3 объекта класса
        GameProgress progress2 = new GameProgress(140, 50, 12, 105.5);
        GameProgress progress3 = new GameProgress(3700000, 120000, 700000, 367.0);

        List<File> listFileZip = new ArrayList<>();
        addFileListZip(listFileZip);

        saveGame(listFileZip.get(0).getAbsolutePath(), progress1);
        saveGame(listFileZip.get(1).getAbsolutePath(), progress2);
        saveGame(listFileZip.get(2).getAbsolutePath(), progress3);  //сериализировали все объекты в папку savegame, каждый объект в свой файл

        String zipName = "M://Games//savegames//save.zip";

        zipFiles(zipName, listFileZip);
        deleteFile(listFileZip);
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
        return dirs.stream()
                .filter(File::mkdir)
                .map(dir -> "Каталог " + dir.getName() + " был создан" + "\n")
                .collect(Collectors.joining("", "Логи" + "\n", ""));
    }

    private static String fileLog(List<File> files) {
        StringBuilder sb = new StringBuilder();
        files.forEach(file -> {
            try {
                if (file.createNewFile())
                    sb.append("Файл ").append(file.getName()).append(" был создан").append("\n");
            } catch (IOException ex) {
                sb.append(ex.getMessage()).append("\n");
            }
        });
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

    private static void addFileListZip(List<File> files) {
        files.add(new File("M://Games//savegames//save1.dat"));
        files.add(new File("M://Games//savegames//save2.dat"));
        files.add(new File("M://Games//savegames//save3.dat"));
    }

    private static void saveGame(String fileName, GameProgress progresses) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progresses);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String zipName, List<File> listFileZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(zipName))) {
            int count = 1;
            for (File fileName : listFileZip) {
                FileInputStream fis = new FileInputStream(fileName.getAbsolutePath());
                ZipEntry entry = new ZipEntry("save" + count++ + ".dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void deleteFile(List<File> files) {
//        new FileOutputStream("M://Games//savegames//save1.dat").close();
//        new FileOutputStream("M://Games//savegames//save2.dat").close();
//        new FileOutputStream("M://Games//savegames//save3.dat").close();
        for (File file : files) {
            file.delete();
        }
    }
}
