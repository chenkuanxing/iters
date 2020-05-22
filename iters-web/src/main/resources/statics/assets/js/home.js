$(document).ready(function(){debugger;
$.ajax({
        type: "GET",
        url: "publish/publishHomeInformations/",
        dataType: "json",
        success: function (result) {
            if (result.code == 100) {
                $.each(result.data.publishQueryDotList,function (index, value) {debugger;
                if(index<=4){
                    var publishEmail="";
                    publishEmail+='<ul class="notice-list">'+
                        '<li class="ue-clear">'+
                        '<a href="javascript:;" class="notice-title">'+value.title+'</a>'+
                        '<div class="notice-time">'+value.publishTimes+'</div>'+
                        '</li>'+
                        '</ul>'
                    $("#htmlPublish").append(publishEmail);
                    $("#htmlPublish").trigger("create");
                }
                });
            }
        }
    });
});