
    // Función para mostrar enlaces de inicio de sesión, registro o cerrar sesión según el estado de autenticación
   
    function LinksbyAuthentication(isAdmin) {
		
        if (isAdmin) {
            document.getElementById("loginLink").style.display = "none";
            document.getElementById("registerLink").style.display = "none";
            document.getElementById("logoutLink").style.display = "block";
            document.getElementById("adminLink").style.display = "block";

        } else {
            document.getElementById("loginLink").style.display = "none";
            document.getElementById("registerLink").style.display = "none";
            document.getElementById("logoutLink").style.display = "block";
            document.getElementById("favoritesLink").style.display = "block";
        }
    }
    
    function launchAuthCheck(){
		let xhr = new XMLHttpRequest();
	      let results;
	      
	      	 xhr.onreadystatechange = function () {
                   if (xhr.readyState === 4){
                       if (xhr.status === 200) {
                         try{

                    	 results = JSON.parse(xhr.responseText);            
              
                         }catch (e) {
							
						}
                      }                    
                }
            };
            
             xhr.open("GET", "ServletLogin", false);
             xhr.setRequestHeader("Content-Type", "application/json");// esto es opcional
             xhr.send();
             console.log(results)  
             
             // comprueba si la var result tiene los datos de la sesion, si los tiene ejecuta la funcion
             // LinksbyAuthentication. 
             // si la result esta vacia, no hace nada se muestta el menu que se hizo en html por defecto. 
             // no cumple la condicion para ejeuctar la fucnion LinksbyAuthentication.
             if (results != null){
           
		     	LinksbyAuthentication(results.isAdmin);
		     }
		    
	}
		
	      
		

    