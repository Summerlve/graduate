"use strict";

(function (window, $, echarts) {
    // chart ele init
    var chart_1 = echarts.init(document.querySelector("#chart-1"));
    var chart_2 = echarts.init(document.querySelector("#chart-2"));
    var chart_3 = echarts.init(document.querySelector("#chart-3"));

    // chart references collection
    var coll = [];
    coll.push(chart_1);
    coll.push(chart_2);
    coll.push(chart_3);

    // all charts show loading view
    coll.forEach(_ => _.showLoading());

    $.ajax({
        url: "/dashboard/charts_data",
        method: "GET",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            // add data for all charts
            var chart_1_option = {
               tooltip: {
                   trigger: "item",
                   formatter: "{a} <br/>{b}: {c} ({d}%)"
               },
               legend: {
                   orient: "vertical",
                   x: "left",
                   data: ["已售出", "未售出", "未完成", "已经预订"]
               },
               series: [
                   {
                       name: "数量",
                       type: "pie",
                       radius: ["50%", "70%"],
                       avoidLabelOverlap: false,
                       label: {
                           normal: {
                               show: false,
                               position: "center"
                           },
                           emphasis: {
                               show: true,
                               textStyle: {
                                   fontSize: "30",
                                   fontWeight: "bold"
                               }
                           }
                       },
                       labelLine: {
                           normal: {
                               show: false
                           }
                       },
                       data:[
                           {value:data.sold, name:"已售出"},
                           {value:data.unsold, name:"未售出"},
                           {value:data.ordered, name:"已经预订"},
                           {value:data.unfinished, name:"未完成"},
                       ]
                   }
               ]
            };

            var {keys, values} = timeHandler(data.houses);
            var chart_2_option = {
                tooltip : {
                    trigger: "axis"
                },
                legend: {
                    data:["房屋销量"]
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                grid: {
                    left: "3%",
                    right: "4%",
                    bottom: "3%",
                    containLabel: true
                },
                xAxis : [
                    {
                        type : "category",
                        boundaryGap : false,
                        data : keys
                    }
                ],
                yAxis : [
                    {
                        type : "value"
                    }
                ],
                series : [
                    {
                        name: "房屋销量",
                        type: "line",
                        stack: "总量",
                        areaStyle: {normal: {}},
                        data: values
                    }
                ]
            };

            var defaultValue = [0, 0, 0, 0, 0];
            var {sold = defaultValue, unsold = defaultValue, ordered = defaultValue, unfinished = defaultValue} = chart_3_handler(data.houses);

            var chart_3_option  = {
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['已售出', '未售出','已预订','未完成']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis:  {
                    type: 'value'
                },
                yAxis: {
                    type: 'category',
                    data: ['小高层','高层','公寓','联排别墅','独栋别墅']
                },
                series: [
                    {
                        name: '已售出',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'insideRight'
                            }
                        },
                        data: sold
                    },
                    {
                        name: '未售出',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'insideRight'
                            }
                        },
                        data: unsold
                    },
                    {
                        name: '已预订',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'insideRight'
                            }
                        },
                        data: ordered
                    },
                    {
                        name: '未完成',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'insideRight'
                            }
                        },
                        data: unfinished
                    }
                ]
            };

            chart_1.hideLoading();
            chart_1.setOption(chart_1_option);

            chart_2.hideLoading();
            chart_2.setOption(chart_2_option);

            chart_3.hideLoading();
            chart_3.setOption(chart_3_option);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus);
        }
    });

    function timeHandler (value) {
        // value needs house array

        let temp = new Map();

        value
            .map(_ => _.buy_date)
            .filter(_ => _ ? true : false)
            .map (_ => new Date(_))
            .sort((pre, lat) => {
                // 按照时间的升序排序

                // compare func
                let compare = (pre, lat, func) => {
                    if (func.apply(pre) > func.apply(lat)) {
                        return 1;
                    }
                    else if (func.apply(pre) < func.apply(lat))
                    {
                        return -1;
                    }
                    else
                    {
                        return 0;
                    }
                };

                let compared = compare(pre, lat, Date.prototype.getFullYear);
                if (compared !== 0) {
                    return compared;
                }
                else
                {
                    compared = compare(pre, lat, Date.prototype.getMonth);
                    if (compared !== 0) {
                        return compared;
                    }
                    else
                    {
                        compared = compare(pre, lat, Date.prototype.getDate);
                        return compared;
                    }
                }
            })
            .forEach(_ => {
                let dateString = [_.getMonth() + 1, _.getDate()].join("/");

                if (temp.has(dateString)) {
                    let count = temp.get(dateString);
                    count++ ;
                    temp.set(dateString, count);
                }
                else
                {
                    temp.set(dateString, 1);
                }
            });

        var {keys, values} = {keys: [], values: []};

        for (var [key, value] of temp) {
            keys.push(key);
            values.push(value);
        }

        return {keys, values};
    }

    function chart_3_handler (value) {
        var ddbs = new Set();
        var lpbs = new Set();
        var gy = new Set();
        var gc = new Set();
        var xgc = new Set();

        value
            .forEach(_ => {
                switch (_.building_info.building_kind.name) {
                    case "独栋别墅": ddbs.add(_)
                    break;

                    case "联排别墅": lpbs.add(_)
                    break;

                    case "公寓": gy.add(_)
                    break;

                    case "高层": gc.add(_)
                    break;

                    case "小高层": xgc.add(_)
                    break;
                }
            });

        let sold = [0, 0, 0, 0, 0];
        let unsold = [0, 0, 0, 0, 0];
        let ordered = [0, 0, 0, 0, 0];
        let unfinished = [0, 0, 0, 0, 0];

        let counter = (_, index) => {
            _.forEach(_ => {
                if (_.state.name = "已售出") sold[index] = sold[index] + 1;
                else if (_.state.name = "未售出") unsold[index] = unsold[index] + 1;
                else if (_.state.name = "已预订") ordered[index] = ordered[index] + 1;
                else if (_.state.name = "未完成") unfinished[index] = unfinished[index] + 1;
            });
        };

        counter(ddbs, 4);
        counter(lpbs, 3);
        counter(gy, 2);
        counter(gc, 1);
        counter(xgc, 0);

        console.log(unfinished);

        return {
            sold,
            unfinished,
            unsold,
            ordered
        };
    }
}(window, $, echarts));