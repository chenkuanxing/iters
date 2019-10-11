/*删除培训列表的内容*/
function delTrain(id) {
    $.ajax({
        type: "GET",
        url: "train/deleteTrain/"+id,
        dataType: "json",
        success: function(result) {
            if (result.code == 100) {
                alert("删除成功！")
                $dataTableHot.bootstrapTable('refresh');
                window.location.reload();
            } else {bootstrapTable
                console.log(result.message);
            }
        }
    });
}
var trainingName, keynoteSpeaker, currentID, trainTime, flag = true;
var $dataTableHot;
function Trainload() {
    $dataTableHot = $('#table').bootstrapTable({
        method: 'get',
        url: "train/trainPage",//请求路径
        striped: true,
        singleSelect: false,
        dataType: "json",
        pagination: true, //分页
        pageSize: 10,
        pageNumber: 1,
        search: false, //显示搜索框
        sidePagination: "server",
        contentType: "application/x-www-form-urlencoded",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
        queryParams: function (params) {
            var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                limit: params.limit, // 每页显示数量
                offset: (params.offset / params.limit) + 1, // SQL语句起始索引
                //page : (params.offset / params.limit) + 1, //当前页码
                trainingName: trainingName = $("#trainingName").val(),
                keynoteSpeaker: keynoteSpeaker = $("#keynoteSpeaker").val(),
                trainTime: sendTimes = $("#trainTime").val(),
            };
            return temp;
        },
        responseHandler: function (res) {
            console.log(res.data.total);
            console.log(res.data.records);
            return {
                total: res.data.total,
                rows: res.data.records
            };
        },
        columns: [
            {
                title: '培训名称',
                field: 'trainingName',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '主讲人',
                field: 'keynoteSpeaker',
                align: 'center'
            },
            {
                title: '培训时间',
                field: 'trainTime',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '参加人',
                field: 'participator',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '培训地点',
                field: 'trainingPlace',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '培训内容',
                field: 'trainContent',
                align: 'center'
            },
            {
                title: '结束时间',
                field: 'endTime',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '成绩',
                field: 'performance',
                align: 'center'
            },
           
            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {
                    var e = '<button button="#" mce_href="#" onclick="delTrain(\'' + row.id + '\')">删除</button> '
                    var d = '<button button="#" mce_href="#" onclick="editTrain(\'' + row.id + '\')">编辑</button> ';
                    return e + d;
                }
            }
        ]
    });
}
function getTrainTableData(){debugger;
    $dataTableHot.bootstrapTable('refresh');
}
function getData() {
    if (flag) {
        name = "";
        person= "";
        time = "";
        flag = false;
    } else {
        person = $("#person").val();
        name = $("#name").val();
        time = $("#demo").val();
    }
    $.ajax({
        type: "GET",
        url: "../WorkRecord/SearchWork?dtStart=" + person + "&dtEnd=" + type + "&dtEnd=" +time,
        dataType: "json",
        success: function (result) {
            if (result.data) {
                var TableData = result.data;
                $('#table').bootstrapTable("load", TableData);
            }
        }
    })
}
//初始化状态下拉菜单
function getType() {
    $.ajax({
        url: '../Common/GetPhaseList',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var PHASEValue = data.data;
            if (PHASEValue.length > 0) {
                $("#part").html("");
                for (var i = 0; i < PHASEValue.length; i++) {
                    if (TASKPHASE == PHASEValue[i].ID) {
                        var html = "<Option value = '" + PHASEValue[i].ID + "'  selected = 'true'>" + PHASEValue[i].NAME + "</Option>";
                    } else {
                        var html = "<Option value = '" + PHASEValue[i].ID + "'>" + PHASEValue[i].NAME + "</Option>";
                    };
                    $("#part").append(html);
                }
            }


        },
        error: function (err) {
        }

    })
}
function add() {
    openlayer()
    currentID = "";
}
function edit(id) {
    openlayer()
    currentID = id;
}
function getCurrentID() {
    return currentID;
}
function editTrain(id){
    $('#id').val(id);
    alert(id);
    layer.open({
        type: 2,
        title: '添加信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:1,
        area: ['90%', '98%'],
        shadeClose: true,
        closeBtn: 1,
        content: 'train_tail'
        //iframe的url
    });
}





