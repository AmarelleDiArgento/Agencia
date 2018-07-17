
<%@page import="Model.Tablas.agenciaTab"%>
<%@page import="java.util.List"%>
<%@page import="Servicios.Mensajes"%>
<!DOCTYPE html>
<%
// nombrar jsp de estancia
    String jsp = "agencia.jsp";
    HttpSession Ses = request.getSession(true);
    Ses.setAttribute("jsp", jsp);
    Mensajes msj = null;

//Confirmar sesion del usuario
    //if (Ses.getAttribute("log") != null) {
        if (Ses.getAttribute("lisAge") != null) {
%>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Lotus QA - Rol</title>
        <link rel="shortcut icon" href="img\favicon.png" type="image/x-icon"/>

        <!-- CSS  -->
        <link href="css/material-icons.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>



    <body
        <%        if (Ses.getAttribute("msj") != null) {
                msj = (Mensajes) Ses.getAttribute("msj");
                out.println(msj.getBody());

            } %> class="bodyfull"
        >

        <header>
            <% // @include file="Segmentos\menu.jspf" %>
        </header>


        <div class="container">
            <h5>Rol</h5>


            <%
                List<agenciaTab> LisAge = (List<agenciaTab>) Ses.getAttribute("lisAge");
            %>
            <table class="centered striped responsive-table" id="tabla" >
                <thead>
                    <tr>
                        <th>NIT</th>
                        <th>Nombre</th>
                        <th>Telefono</th>
                        <th>Dirección</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>

                <tbody>
                    <% 
                        out.print(LisAge.size());
                        
                        for (agenciaTab agt : LisAge) {%>
                    <tr>
                        <td><%=agt.getId_agencia()%></td>
                        <td><%=agt.getNombre()%></td>
                        <td><%=agt.getTelefono()%></td>
                        <td><%=agt.getDireccion()%></td>
                        <td>
                            <a href="#">
                                <i class="material-icons small purple-text" onclick="consultar(<%=agt.getId_agencia()%>)" > edit </i>

                        </td>
                        <td>
                            <a href="#">
                                <i class="material-icons small purple-text" onclick="msjConf(<%=agt.getId_agencia()%>)"> delete </i>
                            </a>
                        </td>
                    </tr>

                    <%}%>
                </tbody>
            </table>

          
        </div>

        <footer class="footer">
            <div>
                <div>
                    <p class="center-align">
                        LOTUS - ELITE FLOWER © 2017 Copyright Text
                    </p>
                </div>
            </div>
        </footer>


        <!-- Modal Insertar Nuevo registro -->
        <div id="modalNuevo" class="modal modal-fixed-footer">
            <form method="get" action="rols.do">
                <div class="modal-content">
                    <h4><i class="material-icons medium">assignment_ind</i> Nuevo Rol</h4>
                    <p>Registra la informacion del nuevo rol</p>
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="Nombre" type="text" name="Nombre" class="validate" required="">
                            <label for="Nombre">Nombre</label>
                        </div>
                        <div class="input-field col s12">
                            <textarea id="Descripcion" class="materialize-textarea" name="Descripcion" class="validate" required></textarea>
                            <label for="Descripcion">Descripción</label>
                        </div>
                        <div class="input-field col s4 center">
                            <div class="switch">
                                <label>
                                    Inactivo
                                    <input type="checkbox" name="Estado">
                                    <span class="lever"></span>
                                    Activo
                                </label>
                            </div>
                        </div>

                    </div>    
                </div>


                <div class="modal-footer">
                    <input name="accion" value="Registrar" type="submit" class="modal-action waves-effect waves-light btn-flat">
                </div>
            </form>
        </div>


        <!-- Modal Modificar Registro -->
        <%if (Ses.getAttribute("Rol") != null) {
                agenciaTab aS = (agenciaTab) Ses.getAttribute("age");
        %>
        <div id="modalModificar" class="modal modal-fixed-footer">
            <form method="get" action="rols.do">
                <div class="modal-content">
                    <h4><i class="material-icons medium">assignment_ind</i> Nuevo Rol</h4>
                    <p>Registra la informacion del nuevo rol</p>
                    <div class="row">
                        <div class="input-field col s6" hidden>
                            <input id="Nit" type="text" name="Id" class="validate" value="<%=aS.getId_agencia()%>"required="">
                            <label for="Nit">Id</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="Nombre" type="text" name="nombre" class="validate" value="<%=aS.getNombre()%>"required="">
                            <label for="Nombre">Nombre</label>
                        </div>
                        <div class="input-field col s12">
                            <textarea id="Telefono" class="materialize-textarea" name="telefono" class="validate" required><%=aS.getTelefono()%></textarea>
                            <label for="Telefono">Telefono</label>
                        </div>


                    </div>    
                </div>


                <div class="modal-footer">
                    <input name="accion" value="Modificar" type="submit" class="modal-action waves-effect waves-light btn-flat">
                </div>
            </form>
        </div>



        <%}%>
        <!--Scripts-->
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/init.js"></script>
        <script type="text/javascript" src="js/sweetalert.min.js"></script>

        <script type="text/javascript">
                                    document.querySelector("#buscar").onkeyup = function () {
                                        $TableFilter("#tabla", this.value);
                                    }
                                    $TableFilter = function (id, value) {
                                        var rows = document.querySelectorAll(id + ' tbody tr');
                                        for (var i = 0; i < rows.length; i++) {
                                            var showRow = false;
                                            var row = rows[i];
                                            row.style.display = 'none';
                                            for (var x = 0; x < row.childElementCount; x++) {
                                                if (row.children[x].textContent.toLowerCase().indexOf(value.toLowerCase().trim()) > -1) {
                                                    showRow = true;
                                                    break;
                                                }
                                            }
                                            if (showRow) {
                                                row.style.display = null;
                                            }
                                        }
                                    };
                                    function modalMod() {

                                        var elem = document.querySelector('#modalModificar');
                                        var instance = M.Modal.init(elem);
                                        instance.open();
                                    }
                                    ;
                                    function msjConf(id) {
                                        swal({
                                            title: "¿Estas seguro?",
                                            text: "Se eliminara el registro con el ID: " + id,
                                            icon: "warning",
                                            buttons: true,
                                            dangerMode: true
                                        })
                                                .then((willDelete) => {
                                                    if (willDelete) {
                                                        window.location = 'agencias.do?accion=Eliminar&id=' + id;
                                                    }
                                                });
                                    }
                                    ;
                                    function consultar(id) {
                                        var url = 'rols.do';
                                        var form = $('<form action="' + url + '" method="Post">' +
                                                '<input type="text" name="id" value="' + id + '" hidden/>' +
                                                '<input type="text" name="accion" value="Obtener" hidden/>' +
                                                '</form>');
                                        $('body').append(form);
                                        (form).submit();
                                    }
            <% if (msj != null) {%>

            <%if (msj.getTipo().equals("Error")) {%>
                                    function msjError() {
                                        swal({
                                            title: "<%=msj.getMsj()%>",
                                            text: "<%=msj.getDetalles()%>",
                                            icon: "error"
                                        });
                                    }
                                    ;


            <%} else if (msj.getTipo().equals("Msj")) {%>
                                    function msjMsj() {

                                        swal("<%=msj.getMsj()%>", {
                                            button: false
                                        });
                                    }
                                    ;

            <%} else if (msj.getTipo().equals("Ok")) {%>
                                    function msjOk()
                                    {
                                        swal({
                                            title: "<%=msj.getMsj()%>",
                                            text: "<%=msj.getDetalles()%>",
                                            icon: "success"
                                        });
                                    }
                                    ;
            <%}
                }%>

        </script>
    </body>
</html>
<%

    Ses.setAttribute("lisAge", null);
    Ses.setAttribute("age", null);
    Ses.setAttribute("msj", null);
} else {%>
<html>
    <body onload="document.getElementById('lista').submit()">
        <form id="lista" action="agencias.do" method="post" >
            <input name="accion" value="Listar" hidden/>
        </form>
    </body>
</html>
<%

        }

    /*} else {

        response.sendRedirect("index.html");
    }*/
%>
