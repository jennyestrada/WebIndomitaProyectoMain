console.log("dale loco")

 function submiteLogin(){
    	// 1 - creo el objeto xhr
	     let xhr = new XMLHttpRequest();
	     

	      

       // 2 - ponemos Post para traer el id del Servlet doPost
		
	   xhr.open("POST", "ServletLogin", false);
	     
	     
	  xhr.onreadystatechange = function () {
                   if (xhr.readyState === 4){
                       if (xhr.status === 200) {
                         try{
		// 3 - mediante la clase javascript JSON me parsee el texto que me envia el Servlet	
		// y los metera en la variable results		

                    	 console.log(xhr.responseText);            
              
                    	//document.getElementById('usuario').innerHTML = usuario.nombre + " <a href='CerrarSesion'>CerrarSesión</a>";
                         }catch (e) {
							// TODO: handle exception
							//location.href = "login.html";
						}
                       }                    
                   }
               };
	    
	     console.log(new FormData(document.getElementById('form1')))
		
	   let formData = new FormData(document.getElementById("form1"));
	   console.log(formData)
	   xhr.send(formData);
	 
 }

