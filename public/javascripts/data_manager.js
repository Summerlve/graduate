$(".update-area-hidden").hide();
$(".update-building-hidden").hide();
$(".update-house-hidden").hide();

$("#update-area-delete").hide();
$("#update-building-delete").hide();
$("#update-house-delete").hide();


window.currentOption = {}; // this is update area
window.currentUpdateBuildingOption = {};
window.currentUpdateHouseOption = {};

// update area
$("#update-area-select").on("change", event => {
    let select =  event.currentTarget;
    let index = select.selectedIndex;
    let currentOption = select.options[index];
    window.currentOption = currentOption;

    let data = currentOption.getAttribute("value");
    if (data === null) {
        $(".update-area-hidden").hide();
        $("#update-area-alert").show();
        return ;
    }
    console.log(data);

    $.ajax({
        url: "/dashboard/data_manager/get_area?id=" + data,
        method: "GET",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            $("#update-area-alert").hide();

            $("#update-area-name").val(data.name);
            $("#update-area-description").val(data.description);
            $("#update-area-management").val(data.management || "无");
            $("#update-area-building-num").val(data.buildingNum);
            document.querySelector("#update-area-overview-img").setAttribute("src", "/assets/images/" + data.img);

            $(".update-area-hidden").show();
        },
        error: function (jqXHR, textStatus, errorThrown) {
           alert("获取数据失败");
        }
    });
});

$("#update-area-form").submit(event => {
    let currentOption = window.currentOption
    let data = currentOption.getAttribute("value");

    if (data === null) {
        alert("请选择小区");
        return false;
    }

    event.currentTarget.setAttribute("action", "/dashboard/data_manager/update_area/" + data);
    return true;
});

$("#update-area-delete").click(event => {
    let currentOption = window.currentOption
    let data = currentOption.getAttribute("value");

    if (data === null) {
        alert("请选择小区");
        return false;
    }

    let wantDelete = confirm("删除此小区会连带删除此小区中的所有楼栋, 你确认要删除吗?");
    if (wantDelete) location.href = "/dashboard/data_manager/delete_area/" + data;
    else return true;
});


// update building
$("#update-building-select").on("change", event => {
    let select =  event.currentTarget;
    let index = select.selectedIndex;
    let currentUpdateBuildingOption = select.options[index];
    window.currentUpdateBuildingOption = currentUpdateBuildingOption;

    let data = currentUpdateBuildingOption.getAttribute("value");
    if (data === null) {
        $(".update-building-hidden").hide();
        $("#update-building-alert").show();
        return ;
    }
    console.log(data);

    $.ajax({
        url: "/dashboard/data_manager/get_building?id=" + data,
        method: "GET",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            $("#update-building-alert").hide();

            $("#update-building-default-of-area").html(`当前所属小区: ${data.area}`);
            $("#update-building-default-kind").html(`当前类型: ${data.kind}`);
            $("#update-building-description").val(data.description);
            $("#update-building-acreage").val(data.acreage);
            $("#update-building-completion-date").val(data.completion_date);
            $("#update-building-house-num").val(data.house_num);

            document.querySelector("#update-building-overview-img").setAttribute("src", "/assets/images/" + data.img);

            $(".update-building-hidden").show();
        },
        error: function (jqXHR, textStatus, errorThrown) {
           alert("获取数据失败");
        }
    });
});

$("#update-building-form").submit(event => {
    let currentOption = window.currentUpdateBuildingOption
    let data = currentOption.getAttribute("value");

    if (data === null) {
        alert("请选择小区");
        return false;
    }

    event.currentTarget.setAttribute("action", "/dashboard/data_manager/update_building/" + data);
    return true;
});

$("#update-building-delete").click(event => {
    let currentOption = window.currentUpdateBuildingOption
    let data = currentOption.getAttribute("value");

    if (data === null) {
        alert("请选择楼栋");
        return false;
    }

    let wantDelete = confirm("删除此楼栋会连带删除此楼栋中的所有房屋, 你确认要删除吗?");
    if (wantDelete) location.href = "/dashboard/data_manager/delete_building/" + data;
    else return true;
});

// update house
$("#update-house-select").on("change", event => {
    let select = event.currentTarget;
    let index = select.selectedIndex;
    let currentUpdateHouseOption = select.options[index];
    window.currentUpdateHouseOption = currentUpdateHouseOption;

    let data = currentUpdateHouseOption.getAttribute("value");
    if (data === null) {
        $(".update-house-hidden").hide();
        $("#update-house-alert").show();
        return ;
    }
    console.log(data);

    $.ajax({
        url: "/dashboard/data_manager/get_house?id=" + data,
        method: "GET",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            $("#update-house-alert").hide();

            $("#update-house-default-state").html(`当前状态: ${data.state}`);
            $("#update-house-floor").val(data.floor);
            $("#update-house-no").val(data.no);

            $("#update-house-space").val(data.space);
            $("#update-house-buy-date").val(data.buy_date);
            $("#update-house-in-date").val(data.in_date);
            $("#update-house-price").val(data.price);

            document.querySelector("#update-house-overview-img").setAttribute("src", "/assets/images/" + data.img);

            $(".update-house-hidden").show();
        },
        error: function (jqXHR, textStatus, errorThrown) {
           alert("获取数据失败");
        }
    });
});

$("#update-house-form").submit(event => {
    let currentOption = window.currentUpdateHouseOption
    let data = currentOption.getAttribute("value");

    if (data === null) {
        alert("请选择房屋");
        return false;
    }

    event.currentTarget.setAttribute("action", "/dashboard/data_manager/update_house/" + data);
    return true;
});

$("#update-house-delete").click(event => {
    let currentOption = window.currentUpdateHouseOption
    let data = currentOption.getAttribute("value");

    if (data === null) {
        alert("请选择房屋");
        return false;
    }

    let wantDelete = confirm("你确认要删除此房屋吗?");
    if (wantDelete) location.href = "/dashboard/data_manager/delete_house/" + data;
    else return true;
});