$(function() {
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
    $('#publishBy').val(name);
    $('#publishBy').attr("data-id",id);
    ani($("#l-modal-bg"), 0);
    ani($(this).closest(".l-modal"), 0);
  });


});
