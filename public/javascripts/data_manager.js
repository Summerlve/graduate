$(".update-area-hidden").hide();
$(".update-building-hidden").hide();

window.currentOption = {}; // this is update area
window.currentUpdateBuildingOption = {};

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