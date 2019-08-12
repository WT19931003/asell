
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="/js/highcharts/code/highcharts.js"></script>
    <script src="/js/highcharts/code/highcharts-3d.js"></script>
    <script src="/js/highcharts/code/modules/exporting.js"></script>
    <script src="/js/highcharts/code/modules/export-data.js"></script>

</head>
<body>
    <div id="container" style="height: 400px"></div>


    <script type="text/javascript">
        Highcharts.chart('container', {
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
                data: [
                    {
                        "name": "东莞供应商",
                        "y": 1486.00
                    },
                    {
                        "name": "成都供应商",
                        "y": 407.00
                    }
                ]
            }]
        });
    </script>
</body>
</html>
