var app = angular.module("indexApp", []);

app.controller("indexController", function ($http, $scope) {

    $scope.analysis=function (){

        if($scope.cardNo != undefined && $scope.cardNo != ""){
            $http.post("/analysis/analysisCardNo",{cardNo:$scope.cardNo}).then(function (result) {
                if(result.data.code==="200"){
                    $scope.showPopus=true;
                    $scope.birthday=result.data.data.birthday;
                    $scope.address=result.data.data.address;
                    $scope.sex=result.data.data.sex;
                    $scope.age=result.data.data.age;
                }else{
                    alert(result.data.code +"====>"+result.data.msg)
                }

            })
        }else{
            alert("请输入身份证号")
        }

    }
})