package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.avaje.ebean.Ebean;
import form.AddArea;
import form.AddBuilding;
import json.OperationResult;
import models.Admin;
import models.Area;
import models.Building;
import models.BuildingKind;
import play.Logger;
import play.Play;
import play.data.Form;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.data_manager;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

import play.mvc.Http.MultipartFormData.FilePart;
import javax.imageio.ImageIO;

/**
 * Created by Summer on 16/3/17.
 */

@Restrict(@Group("ADMIN"))
public class DataManager extends Controller {
    @SuppressWarnings("unchecked")
    public Result index () {
        // get necessary data from db
        List<Area> areas = Area.find.all();
        List<BuildingKind> buildingKinds = BuildingKind.find.all();

        Optional<Admin> user = (Optional) ctx().args.get("user");
        return ok(data_manager.render("数据管理", user.get(), areas, buildingKinds));
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

    @BodyParser.Of(BodyParser.MultipartFormData.class)
    public Result addArea () {
        Form<AddArea> areaForm = Form.form(AddArea.class).bindFromRequest();
        if (areaForm.hasErrors()) return badRequest(Json.toJson(new OperationResult(400, 1, "表单数据错误")));

        String areaName = areaForm.get().getArea_name();
        Integer buildingNum = areaForm.get().getBuilding_num();
        String description = areaForm.get().getDescription();

        // get upload path
        String uploadPath = Play.application().configuration().getString("uploadPath");

        // get img
        FilePart filepart = request().body().asMultipartFormData().getFile("img");
        File image = filepart.getFile();
        String extensionName = filepart.getFilename().split("\\.")[1];
        String uuid = UUID.randomUUID().toString();
        String imageName = uuid + "." + extensionName;
        String imageStorePath = uploadPath + imageName;

        Logger.info(imageStorePath);

        // store to db
        Area area = new Area();
        Ebean.beginTransaction();
        try {
            area.setBuildingNum(buildingNum);
            area.setDescription(description);
            area.setName(areaName);
            area.setImg(imageName);

            // 先存img在写入db
            ImageIO.write(ImageIO.read(image), extensionName, new File(imageStorePath));
            area.save();

            Ebean.commitTransaction();
        }
        catch (Exception e) {
            e.printStackTrace();
            Ebean.rollbackTransaction();
            return internalServerError(Json.toJson(new OperationResult(500, 1, "服务器端错误")));
        }
        finally {
            Ebean.endTransaction();
        }

        return this.index();
    }

    @BodyParser.Of(BodyParser.MultipartFormData.class)
    public Result addBuilding () {
        Form<AddBuilding> buildingForm = Form.form(AddBuilding.class).bindFromRequest();
        if (buildingForm.hasErrors()) return badRequest(Json.toJson(new OperationResult(400, 1, "表单数据错误")));

        // get info from form data
        Long areaId = buildingForm.get().getArea();
        Long buildingKindId = buildingForm.get().getKind();
        Integer houseNum = buildingForm.get().getHouse_num();
        String description = buildingForm.get().getDescription();
        Integer acreage = buildingForm.get().getAcreage();

        // parse String to Calendar
        String timeString = buildingForm.get().getCompletion_date();
        Calendar completionDate = Calendar.getInstance();

        try {
            completionDate.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(timeString));
        }
        catch (Exception e) {
            return internalServerError(Json.toJson(new OperationResult(400, 1, "时间格式不对")));
        }

        // get upload path
        String uploadPath = Play.application().configuration().getString("uploadPath");

        // get image
        FilePart filepart = request().body().asMultipartFormData().getFile("img");
        File image = filepart.getFile();
        String extensionName = filepart.getFilename().split("\\.")[1];
        String uuid = UUID.randomUUID().toString();
        String imageName = uuid + "." + extensionName;
        String imageStorePath = uploadPath + imageName;

        Logger.info(imageStorePath);

        // get area ref
        Area area = Area.find.byId(areaId);

        // get building kind ref
        BuildingKind buildingKind = BuildingKind.find.byId(buildingKindId);

        // store to db
        Building building = new Building();
        Ebean.beginTransaction();
        try {
            building.setArea(area);
            building.setBuildingKind(buildingKind);
            building.setAcreage(acreage);
            building.setDescription(description);
            building.setCompletionDate(completionDate);
            building.setHouseNum(houseNum);
            building.setImg(imageName);
            building.setImg(imageName);

            // 先存img在写入db
            ImageIO.write(ImageIO.read(image), extensionName, new File(imageStorePath));
            building.save();

            Ebean.commitTransaction();
        }
        catch (Exception e) {
            e.printStackTrace();
            Ebean.rollbackTransaction();
            return internalServerError(Json.toJson(new OperationResult(500, 1, "服务器端错误")));
        }
        finally {
            Ebean.endTransaction();
        }

        return this.index();
    }

    @BodyParser.Of(BodyParser.MultipartFormData.class)
    public Result addHouse () {
        return ok();
    }

    @BodyParser.Of(BodyParser.FormUrlEncoded.class)
    public Result change () {
        return ok();
    }
}
