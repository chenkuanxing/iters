/*邮箱导出功能*/
function exportRecycleEmails(){
    alert("导出成功");
    window.open("email/exportRecycleEmails");
}
var emailTitles, isMsgs, currentID, sendTimes, flag = true;
var $dataTableHot;
/*回收站查询的内容*/
function RecycleInf(){debugger;
    $dataTableHot = $('#table03').bootstrapTable({
        method: 'get',
        url: "email/emailRecyclePage",//请求路径
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
                emailTitle: emailTitles = $("#emailsTitles").val(),
                isMsgs: isMsgs = $("#issMsgs").val(),
                sendTime: sendTimes = $("#sendsTimes").val(),
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
                title: '邮件标示',
                field: 'imageId',
                align: 'center',
                formatter: function () {
                    var e = '<a  href="write_email"><img onclick="onclickImgFun()"  src="statics/assets/images/topmail.png"/> </a> ';
                    return e;
                }
            },
            {
                title: "发件人邮箱",
                field: 'sender',
                align: 'center',
                valign: 'middle'
            },
            {
                title: "发件人姓名",
                field: 'senderName',
                align: 'center',
                valign: 'middle'
            },
            {
                title: "收件人邮箱",
                field: 'recipients',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '邮件标题',
                field: 'emailTitle',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '是否已查看',
                field: 'isMsgs',
                align: 'center',
                valign: 'middle'
            },

            {
                title: '发送时间',
                field: 'sendTime',
                align: 'center'
            },
            {
                title: '操作',
                align: 'center',
                formatter: function (value, row) {
                    console.log("row=" + row.id);
                    var e = '<button button="#" mce_href="#" onclick="openEqueryRecycleEmail(\'' + row.id + '\')">详情</button>' +
                        '<button button="#" mce_href="#" onclick="openlayerRestore(\'' + row.id + '\')">还原</button> '
                    return e;
                }
            }
        ]
    });

}
function getEmailRecycleTableData(){debugger;
    $dataTableHot.bootstrapTable('refresh');
}
!function(){
    laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
    laydate({elem: '#sendsTimes',istime:true,format: 'YYYY-MM-DD hh:mm:ss'});//绑定元素
}();
function openEqueryRecycleEmail(id) {
    $('#id').val(id);
    layer.open({
        type: 2,
        title: '邮件详情',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn: 1,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 1,
        content: 'write_queryRecycleEmail'
    });
}
function openlayer(id) {
    $('#id').val(id);
    layer.open({
        type: 2,
        title: '邮件详情',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn: 1,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 1,
        content: 'write_email'
    });
}
    function openlayerRestore(id){
        alert("还原成功")
        $dataTableHot.bootstrapTable('refresh');
        window.location.reload();
        $.ajax({
            type: "GET",
            url: "email/emailRestoreRecycle/" + id,
            dataType: "json",
            success: function (result) {
                if (result.code == 100) {
                }else {
                    console.log(result.message);
                }
            }
        });
}
