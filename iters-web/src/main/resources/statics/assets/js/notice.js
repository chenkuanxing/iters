var $dataTableHot;

function Workload() {
    $(function () {
        $dataTableHot = $('#table').bootstrapTable({
            method: 'get',
            url: "publish/listPage",//请求路径
            striped: true,
            singleSelect: false,
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: false, //显示搜索框
            sidePagination: "server",
            contentType: "application/x-www-form-urlencoded",
            queryParams: function (params) {//x
                var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                    limit: params.limit, // 每页显示数量
                    offset: params.offset, // SQL语句起始索引
                    //page : (params.offset / params.limit) + 1, //当前页码
                    title: $("#title").val(),
                    publishBy: $("#publishBy").val(),
                    publishTimes: $("#publishTime").val()
                };

                return temp;
            },
            responseHandler: function (res) {
                return {
                    total: res.data.total,
                    rows: res.data.records
                };
            },
            columns: [
                {
                    checkbox: "true",
                },
                {
                    title: '标题',
                    field: 'title',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '发布人',
                    field: 'publishName',
                    align: 'center'
                },
                {
                    title: '发布时间',
                    field: 'publishTime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '发布内容',
                    field: 'content',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row) {
                        console.log("row=" + row.id);
                        var e = '<button button="#" mce_href="#" onclick="delWork(\'' + row.id + '\')">删除</button>' +
                            '<button button="#" mce_href="#" onclick="openlayer(\'' + row.id + '\')">编辑</button> '
                        return e;
                    }
                }
            ]
        });
    });
}

function delWork(id) {
    $.ajax({
        type: "GET",
        url: "publish/delete/" + id,
        dataType: "json",
        success: function (result) {
            if (result.code == 100) {
                alert("删除成功！")
                $dataTableHot.bootstrapTable('refresh');
            } else {
                console.log(result.message);
            }
        }
    });

}

function openlayer(id) {debugger;
    $('#id').val(id);
    layer.open({
        type: 2,
        title: '通知信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content: 'notic_tail'
    });
}

function getNoticeTableData() {
    $dataTableHot.bootstrapTable('refresh');
}







