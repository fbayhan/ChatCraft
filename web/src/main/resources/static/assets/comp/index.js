var vue = new Vue({
    el: "#app",
    data: function () {
        return {
            isLogin: false,
            username: '',
            password: '',
            token: '',
            profile: {},
            searchText: '',
            searchUsers : [],
            showFriendReq : false
        }
    },
    methods: {
        login: function () {
            console.log(this.username);
            var vm = this;
            axios.post("api/auth/signin", {username: this.username, password: this.password}).then(function (value) {
                console.log(value.data);
                if (value.data.token != null) {
                    vm.setToken(value.data.token);
                    window.location = "index#" + vm.token;
                }
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        getMyProfile: function () {
            var vm = this;
            axios.post("api/user/me", {}).then(function (value) {
                console.log(value.data);
                vm.profile = value.data;
            }).catch(function (reason) {
                console.log(reason);
                window.location="index";
            });
        },
        setToken: function (token) {
            this.isLogin = true;
            this.token = token;
            axios.defaults.headers.common['Authorization'] = "Bearer " + token;
            this.getMyProfile();
        },
        search: function () {
            var vm = this;
            axios.post("api/user/search", {name:vm.searchText}).then(function (value) {
                console.log(value.data);
                vm.searchUsers = value.data;
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        sendFriendReq : function (user) {
            var vm = this;
            axios.post("api/user/sendFriendReq", user).then(function (value) {
                console.log(value.data);
                // vm.searchUsers = value.data;
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        acceptFriend : function (user) {
            var vm = this;
            axios.post("api/user/acceptFriendReq", user).then(function (value) {
                console.log(value.data);
                // vm.searchUsers = value.data;
            }).catch(function (reason) {
                console.log(reason);
            });
        }
    },
    created() {
        var link = window.location.href;
        if (link.indexOf("#") > 0) {
            this.token = link.split("#")[1];
            this.setToken(this.token);
        }

        console.log(window.location.href.indexOf("#"));

    }
});
