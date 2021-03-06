function loginOut(){
    $.ajax({
        type: "POST",
        url: "signOut",
        dataType: "json",
        success: function (result) {
            if (result.code == 100) {
                location.href = parent.location.href = 'login';
            } else {
                console.log(result.message);
            }
        }
    });
}
var vm = new Vue({
    el:'#container',
    data:{
        user:{}
    },
    methods: {
        userinfo: function () {
            $.getJSON("user/userInfo", function(r){
                console.log(r.data);
                vm.user = r.data;
                console.log(vm.user);
            });
        },
    },
    created:function () {
        this.userinfo();
    }
});