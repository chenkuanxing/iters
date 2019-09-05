var recodeTitle, Publisher, currentID, recodeTime, flag = true;
var $dataTableHot;
function Recodeload() {debugger;
    $(function () {
        $dataTableHot = $('#table').bootstrapTable({
            method : 'get',
            url : "recode/listRecodePage",//请求路径
            striped: true,
            singleSelect: false,
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: false, //显示搜索框
            sidePagination:"server",
            contentType: "application/x-www-form-urlencoded",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
            queryParams : function(params) {
                var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                    limit : params.limit, // 每页显示数量
                    offset : (params.offset / params.limit) + 1, // SQL语句起始索引
                    //page : (params.offset / params.limit) + 1, //当前页码
                    title : recodeTitle = $("#name").val(),
                    publisher : Publisher = $("#person").val(),
                    createdTimes : recodeTime = $("#demo").val(),
                    type : false
                };

                return temp;
            },
            responseHandler: function (res) {
                console.log(res.data.total);
                console.log(res.data.records);
                return {
                    total : res.data.total,
                    rows : res.data.records
                };
            },
            columns : [
                {
                    checkbox: "true",
                },
                {
                    title: '任务名称',
                    field: 'title',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '发布人',
                    field: 'publisherName',
                    align: 'center'
                },
                {
                    title: '发布时间',
                    field: 'createdTime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '执行时间',
                    field: 'performTime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '任务内容',
                    field: 'content',
                    align: 'center'
                }
                ,
                {
                    title: '操作',
                    align: 'center',

                    formatter: function (value, row) {
                        console.log("row="+row.id);
                        var e = '<button button="#" mce_href="#" onclick="delRecode(\'' + row.id + '\')">删除</button>' +
                            '<button button="#" mce_href="#" onclick="openlayer(\'' + row.id + '\')">编辑</button> '
                        return e;
                    }
                }
            ]
        });
    });
}
function getRecodeTableData() {
    $dataTableHot.bootstrapTable('refresh');
}
function addRecode() {debugger;
    openlayer()
    currentID = "";
}
function editRecode(id) {
    openlayer()
    currentID = id;
}
function delRecode(id) {
    $.ajax({
        type: "GET",
        url: "recode/delete/"+id,
        dataType: "json",
        success: function(result) {
            if (result.code == 100) {
                alert("删除成功！")
                $dataTableHot.bootstrapTable('refresh');
            } else {bootstrapTable
                console.log(result.message);
            }
        }
    });
}
function getCurrentID() {
    return currentID;
}
function outRecodeWork() {
    alert("导出成功！");
    window.open("recode/recodeExport");
}
function openlayer(id) {
    $('#id').val(id);
    layer.open({
        type: 2,
        title: '任务信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:" recode_tail"
    });
    
}





