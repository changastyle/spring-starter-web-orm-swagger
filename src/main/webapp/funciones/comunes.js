// UTILIDADES GENERALES:
$scope.urlHome = "../home/home.jsp";
$scope.urlMails = "../emails/emails.jsp";
$scope.urlPersonas = "../personas/personas.jsp";
$scope.urlLogin = "../login/login.jsp";
$scope.fullScreen = false;
$scope.urlMaestra = "http://viewdevs.com.ar:8081";


$scope.redireccionamiento = function(direccion)
{
    window.location.href = direccion;
}

$scope.urlTerminaCon = function(conQueTermina)
{
    terminacionContiene = false;

    //1.TRAIGO LA URL:
    url =  window.location.href;

    if(url.endsWith(conQueTermina))
    {
        terminacionContiene = true;
    }

    return terminacionContiene;
}
$scope.scrollToo = function(idDIV)
{
    $('html, body').animate(
        {
            scrollTop: ($("#" + idDIV).offset().top - 130)
        }, 800);
}
$scope.urlContiene = function(conQueTermina)
{
    terminacionContiene = false;

    //1.TRAIGO LA URL:
    url =  window.location.href;

    if(url.indexOf(conQueTermina) !== -1)
    {
        terminacionContiene = true;
    }

    return terminacionContiene;
}
$scope.openNewTab = function(url)
{
    var win = window.open(url, '_blank');
    win.focus();
}

$scope.dameValorDelParametroFromURL = function(nombreParametro)
{
    var url_string = window.location.href;
    var url = new URL(url_string);
    var parametro = url.searchParams.get(nombreParametro);

    if(parametro == null || parametro.length == 0)
    {
        parametro = -1;
    }
    return parametro;
}
$scope.pasaraMayusPrimerLetra = function (str)
{
    primeraLetra = str.substring(0,1);
    resto = str.substring(1, str.length);

    primeraLetra = primeraLetra.toUpperCase();

    str = primeraLetra + "" + resto;

    return str;
}
$scope.snack = function(msg)
{
    // Get the snackbar DIV
    var x = document.getElementById("snackbar");

    $("#snackbar").html(msg)
    //x.html(msg);

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

$scope.volverVentanaAtras = function()
{
    history.back();
}
$scope.cambiarModoBuscar = function(pos)
{
    if($scope.modoBuscar  == pos )
    {
        $scope.modoBuscar = -1;
    }
    else
    {
        $scope.modoBuscar = pos
    }

    $scope.$evalAsync();
    setTimeout(function()
    {
        $("#barra-busqueda-"+pos).focus();
    },100)

}


$scope.wpp = function(numero)
{
    url = "https://api.whatsapp.com/send?phone=549" + numero;
    $scope.openNewTab(url);
}

$scope.arrOrden = ["nombre", "apellido", "email", "alias", "email", "tel"];
$scope.ordenElegido = "apellido";





//CLIPBOARD:
$(document).ready(function()
{
    new Clipboard('.btn');

    // TOOLTIPS:
    $(document).ready(function()
    {
        $('[data-toggle="tooltip"]').tooltip();
    });

    $scope.anchoScreen = (window.innerWidth > 0) ? window.innerWidth : screen.width;
});











///ACCIONES BARRA LATERAL:
$scope.ventanaActual = null;
$scope.arrAcciones = [];
$scope.findMenus = function()
{
    $scope.cargando = false;
    console.log("BUSCANDO ACCIONES:");
    $.ajax(
        {
            url:"../api/menus/",
            beforeSend: function (xhr)
            {
                $scope.cargando = true;
            },
            success: function (resultado, textStatus, jqXHR)
            {
                $scope.arrMenus = resultado;
                console.log("arrAcciones: " + $scope.arrAcciones.length);

                // DECIME CUAL ES LA VENTANA ACTUAL:
                for(i = 0 ; i < $scope.arrAcciones.length; i++)
                {
                    url = window.location.href;
                    posParametros = url.indexOf("?");
                    if(posParametros != -1)
                    {
                        url = url.substring(0,posParametros);
                        console.log("POS: " + posParametros + " | " + url);
                    }
                    actual = $scope.arrAcciones[i];
                    console.log("loop[" + i + "]: " + JSON.stringify(actual) + " - " + url)
                    if(url.endsWith(actual.terminacion))
                    {
                        $scope.ventanaActual = actual;
                    }
                }

                $scope.cargando = false;
                $scope.$evalAsync();
            }
        });
}
$scope.nombreVentana = "MENU";
$scope.menuCerrado = true;
$scope.mostrarMenu = function()
{
    console.log("mostrar menu : " + $scope.menuCerrado);
    if($scope.menuCerrado)
    {
        $scope.menuCerrado = false;
    }
    else
    {
        $scope.menuCerrado = true;
    }
    $scope.$evalAsync();
}










//FUNCIONES DE LOGEO:
$scope.logearse = function()
{
    $.ajax(
        {
            url:"../../logearse",
            type: 'POST',
            data:
                {
                    "usr":$scope.usr,
                    "pass":$scope.pass
                },
            beforeSend: function (xhr)
            {
                $scope.cargando = true;
            },
            success: function (resultado, textStatus, jqXHR)
            {
                if(resultado)
                {
                    // GUARDO LOS DATOS DE LOGIN:
                    $scope.setCookie("usr",$scope.usr);
                    $scope.setCookie("pass",$scope.pass);

                    // TERMINACION ES LOGIN.JSP:
                    $scope.redireccionamiento($scope.urlMails);
                }
            }

        });
}
$scope.comprobarOperadorLogeado = function()
{
    console.log("comprobando operador logeado");

    if( $scope.usrLogeado == null)
    {
        $scope.refrescarCookies();
    }

    $.ajax(
        {
            url: "../api/operador/logeado",
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
                    $scope.findMenus();
                    $scope.$evalAsync();
                }
            }

        });
}
$scope.readCookie = function(nombreCookie)
{
    var name = nombreCookie + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++)
    {
        var c = ca[i];
        while (c.charAt(0) == ' ')
        {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0)
        {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
$scope.setCookie = function(nombreCookie, valor)
{
    diasExpiracion = 90;
    var d = new Date();
    d.setTime(d.getTime() + (diasExpiracion*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = nombreCookie + "=" + valor + ";" + expires + ";path=/";
}
$scope.refrescarCookies = function()
{
    usrCookie = $scope.readCookie("usr");
    passCookie = $scope.readCookie("pass");

    console.log("USR GUARDADO EN COOKIE: " + usrCookie + " | " + passCookie);

    if(usrCookie != null  && passCookie != null)
    {
        if(usrCookie.trim().length > 0 && passCookie.trim().length > 0)
        {
            if(usrCookie != "undefined" &&  passCookie != "undefined")
            {
                $scope.usr = usrCookie;
                $scope.pass = passCookie;
                $scope.$evalAsync();

                if($scope.usrLogeado == null)
                {
                    console.log("VOY A LOGEARME");
//                    $scope.logearse();
                }
            }
        }
    }

}
$scope.exit = function()
{
    $.ajax(
        {
            url:"../../exit",
            beforeSend: function (xhr)
            {
                $scope.cargando = true;
            },
            success: function (resultado, textStatus, jqXHR)
            {
                if(resultado)
                {
                    $scope.usrLogeado = null;
                    $scope.$evalAsync();
                    $scope.redireccionamiento($scope.urlLogin);
//                window.location.reload();
                }
            }
        });
}
$scope.ayuda = function()
{
    console.log("abriendo ayuda");
    urlWPPNico = "https://api.whatsapp.com/send?phone=5492944530851";
    $scope.openNewTab(urlWPPNico);
}
$scope.abrirModalCambiarContrasena = function()
{
    console.log("abriendo modal");
    $("#modal-cambiar-contrasena").modal();
}

$scope.cambiarPass = function()
{
    if($scope.pass1 != null && $scope.pass2 != null)
    {
        if($scope.pass1 == $scope.pass2)
        {
            $.ajax(
                {
                    url:"../../cambiarPass",
                    data:
                        {
                            "pass1":$scope.pass1,
                            "pass2":$scope.pass2
                        },
                    beforeSend: function (xhr)
                    {
                        $scope.cargando = true;
                    },
                    success: function (resultado, textStatus, jqXHR)
                    {
                        if(resultado)
                        {
                            $scope.exit();
                        }
                    }

                });
        }
    }
}
$scope.olvideMisDatos = function()
{
    if($scope.nombreUsuario != null)
    {
        // ENVIAR AJAX A UN WS QUE ENVIE UN MAIL AL ADMIN PARA RESTABLECER LA PASS:
    }
    else
    {
        alert("Debes ingresar al menos el nombre de usuario");
        $("#input-nombre-usuario").focus();

    }
}


