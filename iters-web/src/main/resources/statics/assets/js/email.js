var emailTitles, isMsgs, currentID, sendTimes, flag = true;
var $dataTableHot;
/*删除收件箱的内容*/
function delEmail(id) {
    $.ajax({
        type: "GET",
        url: "email/deleteEmail/"+id,
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
/*邮箱导出功能*/
function exportEmails(){
    alert("导出成功");
    window.open("email/exportEmailsInformation");
}
/*收件箱查询的内容*/
function InboxInf() {
    $(function () {
        $dataTableHot = $('#table').bootstrapTable({
            method: 'get',
            url: "email/emailInboxPage",//请求路径
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
                    emailTitle: emailTitles = $("#emailTitles").val(),
                    isMsgs: isMsgs = $("#isMsgs").val(),
                    sendTime: sendTimes = $("#sendTimes").val(),
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
                        var e = '<button button="#" mce_href="#" onclick="delEmail(\'' + row.id + '\')">删除</button>' +
                            '<button button="#" mce_href="#" onclick="openlayerEquery(\'' + row.id + '\')">详情</button> ' +
                            '<button button="#" mce_href="#" onclick="openlayerRepeat(\'' + row.id + '\')">转发</button> ' +
                            '<button button="#" mce_href="#" onclick="openlayerReply(\'' + row.id + '\')">回复</button> '
                        return e;
                    }
                }
            ]
        });
    });
}
function getEmailTableData(){debugger;
    $dataTableHot.bootstrapTable('refresh');
}
function openlayerEquery(id){
    $('#id').val(id);
    layer.open({
        type: 2,
        title: '邮件详情',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:1,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 1,
        content: 'write_queryEmail'
    });
}
function openlayerReply(id){
    $('#id').val(id);
    layer.open({
        type: 2,
        title: '邮件详情',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:1,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 1,
        content: 'write_replyEmail'
    });
}
function openlayerRepeat(id){
    $('#id').val(id);
    layer.open({
        type: 2,
        title: '邮件详情',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:1,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 1,
        content: 'write_repeatEmail'
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


