package github.dzc.greendao;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by dzc on 15/11/21.
 */
public class GreenDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1,"github.dzc.apptemplate.download");
        Entity entity = schema.addEntity("DownloadDBEntity");
        entity.setClassNameDao("DownloadDao");
        entity.setTableName("download");
        entity.addStringProperty("downloadId").primaryKey();
        entity.addLongProperty("toolSize");
        entity.addLongProperty("completedSize");
        entity.addStringProperty("url");
        entity.addStringProperty("saveDirPath");
        entity.addStringProperty("fileName");
        entity.addIntProperty("downloadStatus");
        new DaoGenerator().generateAll(schema,"/Users/dzc/Desktop/AppTemplate/app/src/main/java/");
    }
}
