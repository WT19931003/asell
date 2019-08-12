
function  formatStatu(v) {
    if(v==0){
        return "<span style='color: #800509;'>待审</span>"
    }else if(v==1){
        return "<span style='color: green;'>已审</span>"
    }else if(v==-1){
        return "<span style='color: rgba(115,102,106,0.29);'><s>作废</s></span>";
    }
}

$(function () {

    var itemsGrid = $("#itemsGrid");
    var searchForm = $("#searchForm");
    var chartDialog = $("#chartDialog");

    itemsGrid.datagrid({
        //title:'DataGrid - GroupView',
        //width:500,
        //height:250,
        fit:true,
        rownumbers:true,
        remoteSort:false,
        nowrap:false,
        fitColumns:true,
        toolbar:"#searchForm",
        url:'/purchasebillitem/findItems', //数据
        columns:[[
            {field:'id',title:'编号',width:80},
            {field:'supplierName',title:'供应商',width:80},
            {field:'buyerName',title:'采购员',width:80},
            {field:'productName',title:'产品名称',width:80},
            {field:'productTypeName',title:'产品类型',width:80},
            {field:'vdate',title:'交易日期',width:80},
            {field:'num',title:'数量',width:80},
            {field:'price',title:'单价',width:80},
            {field:'amount',title:'小计',width:80},
            {field:'status',title:'状态',width:80,formatter:formatStatu}
        ]],
        groupField:'groupField', //根据哪一个字段分组
        view: groupview,
        groupFormatter:function(value, rows){ //分组部分展示的信息
            //准备数据与金额
            var totalNum = 0;
            var totalAmount = 0;
            //遍历后进行累加
            for(var i=0;i<rows.length;i++){
                var row = rows[i];
                totalNum = totalNum + row.num;
                totalAmount = totalAmount + row.amount;
            }
            return value + ' - ' + rows.length + '条数据 &nbsp;'
                + '<span style="color: #0000FF">共'+totalNum+"个商品</span>&nbsp;"+
                '<span style="color: red">总金额:'+totalAmount+'￥</span>';
        }
});



    //为所有有data-method的组件添加事件
    $("*[data-method]").on("click",function(){
        // var method = $(this).data("method");
        // itsource[method]();
        itsource[$(this).data("method")]()
    })

    //防止污染
    window.itsource = {
        search:function(){
            //获取过滤的参数值
            var params = searchForm.serializeObject();
            //刷新grid
            itemsGrid.datagrid('load',params);
        },
        chart3D:function () {
            //获取过滤的参数值
            var params = searchForm.serializeObject();
            //通过Ajax到后台拿到相应的数据
            $.post("/purchasebillitem/findCharts",params,function (result) {
                //展示图表
                Highcharts.chart('chartDialog', {
                    chart: {
                        type: 'pie', //代表是饼图
                        options3d: { //3D的一些属性
                            enabled: true,
                            alpha: 45,
                            beta: 0
                        }
                    },
                    title: {
                        text: '我的图就是你！'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            depth: 35, //深度
                            dataLabels: {
                                enabled: true,
                                format: '{point.name}'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: '浏览器份额',
                        data: result
                    }]
                });
                //弹出dialog
                chartDialog.dialog("center").dialog("open");
            })
        },
        chart2D:function () {
            //获取过滤的参数值
            var params = searchForm.serializeObject();
            //通过Ajax到后台拿到相应的数据
            $.post("/purchasebillitem/findCharts",params,function (result) {
                Highcharts.chart('chartDialog', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: '2018年1月浏览器市场份额'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                }
                            }
                        }
                    },
                    series: [{
                        name: 'Brands',
                        colorByPoint: true,
                        data: result
                    }]
                });
                //弹出dialog
                chartDialog.dialog("center").dialog("open");
            })
        }
    };
})