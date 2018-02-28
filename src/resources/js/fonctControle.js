function teste()
{
   var  ok=true;
    if(document.identification.nom.value == "")
        {
            alert("Veuillez entrer votre nom");
            ok=false;
        }
      else
          if(document.identification.prenom.value == "")
            {
            alert("Veuillez entrer votre prenom");
            ok=false;
        }
          else
              if(document.identification.ville.value == "")
                {
                alert("Veuillez entrer la ville");
                ok=false;
            }

return ok;
}

function testeOeuvre()
{
    var  ok=true;
    if(document.identification.titre.value == "")
    {
        alert("Veuillez entrer le titre de votre oeurve.");
        ok=false;
    }
    else
    if(document.identification.prix.value == "")
    {
        alert("Veuillez entrer le prix de votre oeuvre.");
        ok=false;
    }

    return ok;
}