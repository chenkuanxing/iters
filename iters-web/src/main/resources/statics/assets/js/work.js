var workTitle, Publisher, currentID, workTime, flag = true;
var $dataTableHot;
    function Workload() {
    $(function () {
        $dataTableHot = $('#table').bootstrapTable({
            method : 'get',
            url : "journal/listPage",//请求路径
            striped: true,
            singleSelect: false,
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: false, //显示搜索框
            sidePagination:"server",
            contentType: "application/x-www-form-urlencoded",
            queryParams : function(params) {//x
                var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                    limit : params.limit, // 每页显示数量
                    offset : params.offset, // SQL语句起始索引
                    //page : (params.offset / params.limit) + 1, //当前页码
                    title : workTitle = $("#tit").val(),
                    publisher : Publisher = $("#person").val(),
                    publisherTime : workTime = $("#demo").val()
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
                    title: '日志标题',
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
                    title: '发布内容',
                    field: 'content',
                    align: 'center'
                }
                ,
                {
                    title: '操作',
                    align: 'center',

                    formatter: function (value, row) {
                        console.log("row="+row.id);
                        var e = '<button button="#" mce_href="#" onclick="delWork(\'' + row.id + '\')">删除</button> '
                        return e;
                    }
                }
            ]
        });
    });
}

// function getWorkTableData() {
//     if (flag) {
//         workTitle = "";
//         Publisher = "";
//         workTime = "";
//         flag = false;
//     } else {
//         workTitle = $("#tit").val();
//         Publisher = $("#person").val();
//         workTime = $("#demo").val();
//     }
//     $.ajax({
//         type: "GET",
//         url: "journal/listPage?title=" +workTitle + "&publisher=" + Publisher + "&publisherTime=" + workTime,
//         dataType: "json",
//         success: function (result) {
//             if (result.data) {
//                 var NoticeTableData = result.data;
//                 $('#table').bootstrapTable("load", NoticeTableData);
//             }
//         }
//     })
// }
function getWorkTableData(){
    $dataTableHot.bootstrapTable('refresh');
}
function addWork() {
    openlayer()
    currentID = "";
}
function editWork(id) {
    openlayer()
    currentID = id;
}
function delWork(id) {
        $.ajax({
            type: "GET",
            url: "journal/delete/"+id,
            dataType: "json",
            success: function(result) {
                if (result.code == 100) {
                    alert("删除成功！")
                    $dataTableHot.bootstrapTable('refresh');
                } else {
                    console.log(result.message);
                }
            }
        });

 }
// function getCurrentID() {
//     return currentID;
// }





