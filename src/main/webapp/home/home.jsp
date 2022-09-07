<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <%@include file="../comun/comun.jsp" %>
</head>
<body ng-app="app" ng-controller="home" ng-init="init()" ng-cloak>

    LOGEADO JS: {{usrLogeado}}

    <br>
    <label>Usuario</label>
    <input type="text" placeholder="EMAIL.." ng-model="email">
    <br>
    <label>Pass</label>
    <input type="text" placeholder="PASS.." ng-model="pass">
    <button class="btn btn-success" ng-click="logearse()">LOGIN</button>
    <button class="btn btn-danger" ng-click="exit()">Cerrar Sesion</button>


    <br>
    MENUS:
    <div class="cont-menus">
        <div class="item-menu" ng-repeat="menuLoop in arrMenus">
            {{menuLoop}}
        </div>
    </div>


<%--    LOGEADO:<%= request.getSession().getAttribute("USER_emkt")%>--%>
    <!-- GRID:-->
<%--    <div class="grilla-marcadores flex col-xs-6 col-xs-offset-6 col-sm-8 col-sm-offset-4 col-md-10 col-md-offset-2"--%>
<%--         style="margin-top:50px; height:calc(100% - 50px)">--%>

<%--        <div class=" col-xs-12 col-sm-6 col-md-4 " ng-repeat="e in arrMarcadors | filter : busqueda" >--%>
<%--            <div class="cuadraditos col-xs-12">--%>

<%--                <a ng-click="openNewTab(e.enlace)">--%>
<%--                    <div  class="imgs-circulos img-responsive center-block img-circle" style="background-image:url('{{e.urlFinal}}')">--%>
<%--                    </div>--%>
<%--                </a>--%>
<%--                <h4 class="hx col-xs-12">--%>
<%--                    <a ng-click="openNewTab(e.enlace)">{{e.titulo}}</a>--%>
<%--                </h4>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<!-- FIN FORMATO COMUN: -->
</body>

<script>
    //ANGULAR
    app = angular.module('app', []);

    app.controller('home', function($scope)
    {
        <%@include file="../funciones/comunes.js" %>
<%--        <%@include file="../funciones/Accion-fn.js" %>--%>

        $scope.init = function()
        {
            console.log("INIT");
            $scope.comprobarOperadorLogeado();
        }
        $scope.logearse = function()
        {
            $.ajax(
                {
                    url: "../api/operador/logearse",
                    type:"POST",
                    data:{
                        "email" : $scope.email,
                        "pass" : $scope.pass
                    },
                    beforeSend: function (xhr)
                    {
                        $scope.cargando = true;
                    },
                    success: function (resultado, textStatus, jqXHR)
                    {
                        if(resultado != null && resultado != "undefined")
                        {
                            $scope.usrLogeado = resultado;
                            console.log("LOGEADOX:" +  JSON.stringify($scope.usrLogeado));
                            $scope.findAcciones();
                            $scope.$evalAsync();
                        }
                    }

                });
        }
        $scope.exit = function()
        {
            $.ajax(
                {
                    url: "../api/operador/exit",
                    beforeSend: function (xhr)
                    {
                        $scope.cargando = true;
                    },
                    success: function (resultado, textStatus, jqXHR)
                    {
                        if(resultado != null && resultado != "undefined")
                        {
                            $scope.usrLogeado = resultado;
                            console.log("LOGEADOX:" +  JSON.stringify($scope.usrLogeado));
                            $scope.findAcciones();
                            $scope.$evalAsync();
                        }
                    }

                });
        }
    });
</script>
</html>
<style>
    .item-menu
    {
        border: solid 1px red;
    }
</style>