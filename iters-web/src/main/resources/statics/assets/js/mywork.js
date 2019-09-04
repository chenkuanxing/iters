var workTitle, Publisher, currentID, workTime, flag = true;
var $dataTableHot;
function Workload() {
    $(function () {debugger;
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
            contentType: "application/x-www-form-urlencoded",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
            queryParams : function(params) {
                var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                    limit : params.limit, // 每页显示数量
                    offset : (params.offset / params.limit) + 1, // SQL语句起始索引
                    //page : (params.offset / params.limit) + 1, //当前页码
                    title : workTitle = $("#tit").val(),
                    publisher : Publisher = $("#person").val(),
                    createdTimes : workTime = $("#demo").val(),
                    type : true
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
                // {
                //     title: '发布部门',
                //     field: 'publishDep',
                //     align: 'center',
                //     valign: 'middle'
                // },
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
                        var e = '<button button="#" mce_href="#" onclick="delWork(\'' + row.id + '\')">删除</button>' +
                            '<button button="#" mce_href="#" onclick="openlayer(\'' + row.id + '\')">编辑</button> '
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
function getWorkTableData(){debugger;
    $dataTableHot.bootstrapTable('refresh');
}
function addWork() {
    openlayer()
    currentID = "";
}
function outWork() {
    alert("导出成功！");
    window.open("journal/export");
}
/*function openlayer(id) {debugger;
    $('#id').val(id);
    $("input[name='btSelectItem']:checked").each(function() {debugger;
        //var len = $("input:checkbox:checked").length;
        //alert("你一共选中了"+len+"个复选框");
        if($("input:checkbox:checked").length==1){
            //var id = window.parent.document.getElementById("id").value;
            openlayer(id)
            currentID ="";
        } else{
            $dataTableHot.bootstrapTable('refresh');
        }
        //alert("成功选中！！！")
    });

}
*/
function openlayer(id) {
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
        content: 'work_tail'
    });
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
            } else {bootstrapTable
                console.log(result.message);
            }
        }
    });
}
// function getCurrentID() {
//     return currentID;
// }





