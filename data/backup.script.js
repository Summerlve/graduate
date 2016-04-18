"use strict";
// backup data
const exec = require("child_process").exec;
const path = require("path");
const mainPath = process.argv[2];

if (!mainPath) {
    process.stderr.write(`Invalid Argument\nthis script need a main path to store sql file\n`);
    process.exit(9) // Invalid Argument
}

console.log("mainPath:", mainPath);

let coll = [];

let files = [
    "zh_user.sql",
    "zh_area.sql",
    "zh_house_state.sql",
    "zh_building_kind.sql",
    "zh_house.sql",
    "zh_admin.sql",
    "zh_building.sql"
];

let zh_user = new Promise((resolve, reject) => {
    exec(`mysqldump -u root -p123456 graduate zh_user > ${path.join(mainPath, "zh_user.sql")}`, (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_area = new Promise((resolve, reject) => {
    exec(`mysqldump -u root -p123456 graduate zh_area > ${path.join(mainPath, "zh_area.sql")}`, (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_house_state = new Promise((resolve, reject) => {
    exec(`mysqldump -u root -p123456 graduate zh_house_state > ${path.join(mainPath, "zh_house_state.sql")}`, (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_building_kind = new Promise((resolve, reject) => {
    exec(`mysqldump -u root -p123456 graduate zh_building_kind > ${path.join(mainPath, "zh_building_kind.sql")}`, (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_house = new Promise((resolve, reject) => {
    exec(`mysqldump -u root -p123456 graduate zh_house > ${path.join(mainPath, "zh_house.sql")}`, (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_admin = new Promise((resolve, reject) => {
    exec(`mysqldump -u root -p123456 graduate zh_admin > ${path.join(mainPath, "zh_admin.sql")}`, (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_building = new Promise((resolve, reject) => {
    exec(`mysqldump -u root -p123456 graduate zh_building > ${path.join(mainPath, "zh_building.sql")}`, (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

coll.push(zh_admin);
coll.push(zh_area);
coll.push(zh_building);
coll.push(zh_building_kind);
coll.push(zh_house);
coll.push(zh_house_state);
coll.push(zh_user);

Promise.all(coll)
        .then(_ => console.log("ok"),
              _ => console.error(_));








