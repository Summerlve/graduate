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

            function randomData() {
                now = new Date(+now + oneDay);
                value = value + Math.random() * 21 - 10;
                return {
                    name: now.toString(),
                    value: [
                        [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('-'),
                        Math.round(value)
                    ]
                }
            }

            var data = [];
            var now = +new Date(1997, 9, 3);
            var oneDay = 24 * 3600 * 1000;
            var value = Math.random() * 1000;
            for (var i = 0; i < 1000; i++) {
                data.push(randomData());
            }

            var chart_3_option = {
                title: {
                    text: '动态数据 + 时间坐标轴'
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: function (params) {
                        params = params[0];
                        var date = new Date(params.name);
                        return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
                    },
                    axisPointer: {
                        animation: false
                    }
                },
                xAxis: {
                    type: 'time',
                    splitLine: {
                        show: false
                    }
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%'],
                    splitLine: {
                        show: false
                    }
                },
                series: [{
                    name: '模拟数据',
                    type: 'line',
                    showSymbol: false,
                    hoverAnimation: false,
                    data: data
                }]
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
}(window, $, echarts));