import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        File games = new File("M://Games");
        File src = new File(games, "src");
        File res = new File(games, "res");
        File savegames = new File(games, "savegames");
        File temp = new File(games, "temp");
        File main = new File(src, "main");
        File test = new File(src, "test");
        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");

        File mainJava = new File("M://Games//src//main//Main.java");
        File utilJava = new File("M://Games//src//main//Utils.java");
        File tempTxt = new File("M://Games//temp//temp.txt");

        StringBuilder sb = new StringBuilder("Логи" + "\n");

        sb.append(creatingLogsForDirectories(src));
        sb.append(creatingLogsForDirectories(res));
        sb.append(creatingLogsForDirectories(savegames));
        sb.append(creatingLogsForDirectories(temp));
        sb.append(creatingLogsForDirectories(main));
        sb.append(creatingLogsForDirectories(test));
        sb.append(creatingLogsForDirectories(drawables));
        sb.append(creatingLogsForDirectories(vectors));
        sb.append(creatingLogsForDirectories(icons));

        sb.append(creatingLogsForFiles(mainJava));
        sb.append(creatingLogsForFiles(utilJava));
        sb.append(creatingLogsForFiles(tempTxt));

        String result = sb.toString();

        try (FileWriter writer = new FileWriter("M://Games//temp//temp.txt", false)) {
            writer.write(result);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        GameProgress progress1 = new GameProgress(140, 50, 12, 105.5);
        GameProgress progress2 = new GameProgress(200, 100, 50, 300.2);
        GameProgress progress3 = new GameProgress(370, 120, 70, 367.0);

        saveGame("M://Games//savegames//save1.dat", progress1);
        saveGame("M://Games//savegames/save2.dat", progress2);
        saveGame("M://Games//savegames/save3.dat", progress3);

        zipFiles("M://Games//savegames//save.zip","M://Games//savegames//save1.dat");
        zipFiles("M://Games//savegames//save.zip","M://Games//savegames//save2.dat");
        zipFiles("M://Games//savegames//save.zip","M://Games//savegames//save3.dat");

    }

    private static void saveGame(String fileName, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String zipName, String fileName) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(zipName));
             FileInputStream fis = new FileInputStream(fileName)) {
            ZipEntry entry = new ZipEntry(fileName);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static String creatingLogsForDirectories(File dir) {
        if (dir.mkdir()) {
            return "Каталог " + dir.getName() + " был создан" + "\n";
        }
        return "Ошибка создания каталога  " + dir.getName() + "\n";
    }

    private static String creatingLogsForFiles(File file) {
        try {
            if (file.createNewFile())
                return "Файл " + file.getName() + " был создан" + "\n";
        } catch (IOException ex) {
            return ex.getMessage() + "\n";
        }

        return "Ошибка создания файла  " + file.getName() + "\n";
    }
}