var userid;
$(function() {
  var setting1 = {
    view: {
      dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
      showLine: true,//是否显示节点之间的连线
      fontCss:{'color':'black','font-weight':'bold'},//字体样式函数
      selectedMulti: true //设置是否允许同时选中多个节点
    },
    data: {
      simpleData: {//简单数据模式
        enable:true,
        idKey: "id",
        pIdKey: "parentId",
        name: "name",
        rootPId: null
      }
    },
    callback:{
      onClick : zTreeOnClick
    }
  };

  var setting = {
    view: {
      dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
      showLine: true,//是否显示节点之间的连线
      fontCss:{'color':'black','font-weight':'bold'},//字体样式函数
      selectedMulti: true //设置是否允许同时选中多个节点
    },
    check:{
      enable: true,
      chkStyle: "radio",
      radioType: "all"
    },
    data: {
      simpleData: {//简单数据模式
        enable:true,
        idKey: "id",
        pIdKey: "parentId",
        name: "name",
        rootPId: null
      }
    },
    callback:{
      onCheck: onCheck
    }
  };

  $(document).ready(function() {
    $.ajax({
      type: "GET",
      url: "department/listTree",
      dataType: "json",
      success: function (result) {
        if (result.code == 100) {
          $.fn.zTree.init($("#regionZTree"), setting, result.data);
        } else {
          console.log(result.message);
        }
      }
    });

  });

  $(document).ready(function() {
    $.ajax({
      type: "GET",
      url: "department/listTree",
      dataType: "json",
      success: function (result) {
        if (result.code == 100) {
          $.fn.zTree.init($("#userZTree"), setting1, result.data);
        } else {
          console.log(result.message);
        }
      }
    });

  });

  function onCheck(e,treeId,treeNode){
    var treeObj=$.fn.zTree.getZTreeObj("regionZTree"),
        nodes=treeObj.getCheckedNodes(true),
        v="";
    for(var i=0;i<nodes.length;i++){
      $('#regionZTree').attr("data-id",nodes[i].id);
      $('#regionZTree').attr("data-name",nodes[i].name);
    }
  }

  // 单击事件，向后台发起请求
  function zTreeOnClick(event, treeId, treeNode) {
    $.ajax({
      type: "POST",
      url: "user/getBydepId/"+treeNode.id,
      dataType: "json",
      success: function (result) {
        if (result.code == 100) {
          var html='';
          $('#userList').find("label").remove();
          $.each(result.data, function (i, value) {
            html = html+"<label style='width: 50%;'><input style='width: 13px;height: 13px' name='radio' data-userName='"+value.userName+"' type='radio' value='"+value.id+"'><span style='margin-right: 70px'>"+value.userName+"</span></label>";
          })
          $('#userList').append(html);
        } else {
          console.log(result.message);
        }
      }
    });
  }

  function ani($el, show) {
    if (show) {
      $el.show();
      $el.each(function() {
        $(this).width();
      });
      $el.addClass("active");
    } else {
      $el.removeClass("active");
      setTimeout(function() {
        $el.hide();
      }, 300);
    }
  }
  $(".l-modal-close").on("click", function() {
    //关闭弹窗
    ani($("#l-modal-bg"), 0);
    ani($(this).closest(".l-modal"), 0);
  });

  $(".l-modal-cancel").on("click", function() {
    //关闭弹窗
    ani($("#l-modal-bg"), 0);
    ani($(this).closest(".l-modal"), 0);
  });

  $("#depId").focus(function(){
    ani($("#l-modal-bg,#stu-dep"), 1);

  });

  $("#publishBy").focus(function(){
    userid = 'publishBy';
    ani($("#l-modal-bg,#stu-user"), 1);
  });

  $("#publisher").focus(function(){
    userid = 'publisher';
    ani($("#l-modal-bg,#stu-user"), 1);
  });

  $("#performBy").focus(function(){
    userid = 'performBy';
    ani($("#l-modal-bg,#stu-user"), 1);
  });

   $("#sureCheck").on("click", function() {//弹窗确认
     var id = $('#regionZTree').attr("data-id");
     var name = $('#regionZTree').attr("data-name");
     $('#depId').val(name);
     $('#depId').attr("data-id",id);
     ani($("#l-modal-bg"), 0);
     ani($(this).closest(".l-modal"), 0);
   });

  $("#addUserCheck").on("click", function() {//弹窗确认
    var id = $('input:radio:checked').val();
    var name = $('input:radio:checked').attr("data-userName");
    console.log("id="+id+"name="+name);
    $('#'+userid).val(name);
    $('#'+userid).attr("data-id",id);
    ani($("#l-modal-bg"), 0);
    ani($(this).closest(".l-modal"), 0);
  });


});
