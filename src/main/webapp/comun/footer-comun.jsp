<!--</div>-->
</div>
</div>

<!-- SNACKBAR:-->
<!--<div id="snackbar">Some text some message..</div>-->

<div class="footer" >
    Un producto de View Devs Company
    <!--{{anchoScreen}}-->
    <!--    XS:{{xs ||sm}}-->
    <!--LG:{{md || lg}}-->
</div>



<div class="alta-sticky " ng-click="alta()" ng-hide='btnStickyApagado'>
    <!--<img class="img-alta" src="../res/img/iconos/add-rounded.png">-->
    <h3 class="img-alta"><i class="fas fa-plus" ng-show="btnStickyAddMode"></i></h3>
    <h3 class="img-alta"><i class="fas fa-check" ng-hide="btnStickyAddMode"></i></h3>
</div>

<style>
    .footer
    {
        background-color: var(--secundario);
        color: white;
        text-align: center;
        padding: 25px;

    }
</style>