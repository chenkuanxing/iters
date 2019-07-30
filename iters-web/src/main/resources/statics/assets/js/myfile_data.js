// 加载数据
var name, type,currentID;
var $dataTableHot;
function Myfileload() {
    $(function () {
        $dataTableHot = $('#table').bootstrapTable({
            method: 'get',
            url: "fileManage/listPage",//请求路径
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
                    type: $('#typess').html(),
                    fileType: $('#flietype').html(),
                    name : $("#name").val(),
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
                    title: '文件名称',
                    field: 'name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '文件类型',
                    field: 'type',
                    align: 'center',
                    formatter: function (value, row) {
                        if (value == 1){
                            return "私人文件";
                        } else if (value == 2){
                            return "工作文件";
                        }else if (value == 3){
                            return "项目文件";
                        }
                        return ;
                    }
                },
                {
                    title: '上传时间',
                    field: 'createdTime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '文件大小',
                    field: 'size',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '文件格式',
                    field: 'format',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: '',
                    align: 'center',
                    formatter: function (value, row) {
                        var e = '<button button="#" mce_href="#" onclick="del(\'' + row.id + '\')">删除</button> '
                        var d = '<button button="#" mce_href="#" onclick="down(\'' + row.id + '\')">下载</button> ';

                        return e + d;
                    }
                }
            ]
        });
    });
}

// 下载操作
function down(id) {
    window.open("fileManage/download/"+id);
}
// 删除操作
function del(id) {
    $.ajax({
        url: 'fileManage/delete/' + id,
        type: 'GET',
        dataType: 'json',
        success: function (result) {
            if (result.code == 100) {
                alert("删除成功！");
                $dataTableHot.bootstrapTable('refresh');
            } else {
                alert(result.message);
            }
        }
    });
}






