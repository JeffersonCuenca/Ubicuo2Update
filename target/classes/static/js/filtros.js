function SoloLetras(e)
{
    key = e.keycode || e.which;
    tecla = String.fromCharCode(key).toString();
    letras = "QWERTYUIOPASDFGHJKLÑZXCVBNMÁÉÍÓÚqwertyuiopasdfghjklñzxcvbnmáéíóú"

    especiales = [8, 13, 32];
    tecla_especial = false;
    
    for (var i in especiales)
    {
        if (key == especiales[i])
        {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) == -1 && !tecla_especial)
    {
        return false;

    }
}

function SoloNumeros(e)
{
    key = e.keycode || e.which;
    teclado = String.fromCharCode(key);
    numero = "0123456789";
    especiales = "8-37-38-46";
    teclado_especial = false;
    
    for (var i in especiales)
    {
        if (key == especiales[i])
        {
            teclado_especial = true;
        }
        if (numero.indexOf(teclado)==-1 && !teclado_especial)
        {
            return false;
        }
    }
}

