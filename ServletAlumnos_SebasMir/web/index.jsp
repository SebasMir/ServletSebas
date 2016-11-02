<%-- 
    Document   : index
    Created on : 19-oct-2016, 20:20:13
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script>
            
            //to prefix variables that contain a jQuery object with a $ so that they are easily identified
            $(document).ready(function () { //Permite el uso de una funcion después que este cargado
  
                $('#ListaAlumn').change(function (event) { //El evento cambia cuando el valor del elemnto ha sido cambiado.
                    // Aquí la carga de la información de cada alumno al cambiar la selección
                    
                    var dataString = "code=" + $('#ListaAlumn').val() + "&name=" + $('#ListaAlumn option:selected').text();
                    $.ajax({
                        type: "POST",
                        url: "Servlet",
                        dataType: "json",
                        data: dataString,
                        success: function (responseJson) { //jsonElmnt = gson.toJson(alumn); response.getWriter().print(jsonElmnt);
                            $('#Alumn').html(responseJson.nom);
                            $('#asignaturas').show();
                            $('#listAssignatures').html(responseJson.assignatures + "<br/>");
                            $('#tutoria').show();
                            $('#listTutories').html(responseJson.tutories + "<br/>");

                        }
                    });

                });
                $.ajax({
                    type: "GET",
                    url: "Servlet",
                    dataType: "json",
                    success: function (responseJson) {
                        if (responseJson !== null) {
                            var alumnSelect = $('#ListaAlumn'); //var email_field = $("#email").get(0); // refers to the dom object itself
                            alumnSelect.find('option').remove();
                            $.each(responseJson, function (key, value) {
                                $('<option>').val(value['codi']).text(value['nom']).appendTo(alumnSelect);
                            });
                        }
                    }
                });
            }); 
        </script>
    </head>
    <body>
        <h1 style="padding-left: 80px;">Alumno</h1>
        <br/>
        <select id="ListaAlumn" style="font-size: 25px; margin-left: 80px;">
        </select>
         <br/><br/>
        <div>
            <div id="Alumn" style="padding-left: 80px; margin-top: 15px; font-weight: bold;"></div>
            <span id="asignaturas" style="padding-left: 100px; display: none; font-size: 25px;">Assignatures:</span><br/>
            
            <div id="listAssignatures" style=" padding-left: 130px; font-size: 20px;"></div><br/>
            <span id="tutoria" style="padding-left: 100px; display: none; font-size: 25px;">Tutoríes:</span><br/>
            
            <div id="listTutories" style="padding-left: 130px; font-size: 20px;"></div><br/>
            
        </div>

    </body>
</html>
