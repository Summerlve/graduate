"use strict";
// restore data
const exec = require("child_process").exec;

let coll = [];

let zh_user = new Promise((resolve, reject) => {
    exec("mysql -u root -p 123456 graduate < zh_user.sql", (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_area = new Promise((resolve, reject) => {
    exec("mysql -u root -p 123456 graduate < zh_area.sql", (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_house_state = new Promise((resolve, reject) => {
    exec("mysql -u root -p 123456 graduate < zh_house_state.sql", (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_building_kind = new Promise((resolve, reject) => {
    exec("mysql -u root -p 123456 graduate < zh_building_kind.sql", (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_house = new Promise((resolve, reject) => {
    exec("mysql -u root -p 123456 graduate < zh_house.sql", (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_admin = new Promise((resolve, reject) => {
    exec("mysql -u root -p 123456 graduate < zh_admin.sql", (error, stdout, stderr) => {
        if (!error) resolve(true);
        else reject(error);

        if (stderr) reject(stderr);
    });
});

let zh_building = new Promise((resolve, reject) => {
    exec("mysql -u root -p 123456 graduate < zh_building.sql", (error, stdout, stderr) => {
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
        .then(_ => console.log("ok"), _ => console.error(_));








