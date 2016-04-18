package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import json.OperationResult;
import models.Admin;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.data_manager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Created by Summer on 16/3/17.
 */

@Restrict(@Group("ADMIN"))
public class DataManager extends Controller {
    public Result index () {
        Optional<Admin> user = (Optional) ctx().args.get("user");
        return ok(data_manager.render("数据管理", user.get()));
    }

    public Result backup () {
        boolean isSucceed = false; // record cmd exec result

        try {
            Process process = Runtime.getRuntime().exec(new String[] {"node", "./data/backup.script.js", "./data"});
            process.waitFor();

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));

            String line = null;

            while ((line = input.readLine()) != null) {
                Logger.info(line);
            }

            if (process.exitValue() == 0) isSucceed = true;

            process.destroy();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (isSucceed) return ok(Json.toJson(new OperationResult(200, 0, "数据备份成功")));
        else return internalServerError(Json.toJson(new OperationResult(500, 1, "数据备份失败")));
    }

    public Result restore () {
        boolean isSucceed = false; // record cmd exec result

        try {
            Process process = Runtime.getRuntime().exec("node ./data/restore.script.js ./data/");
            process.waitFor();

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));

            String line = null;

            while ((line = input.readLine()) != null) {
                Logger.info(line);
            }

            if (process.exitValue() == 0) isSucceed = true;

            process.destroy();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (isSucceed) return ok(Json.toJson(new OperationResult(200, 0, "数据恢复成功")));
        else return internalServerError(Json.toJson(new OperationResult(500, 1, "数据恢复失败")));
    }
}
