var boton = document.getElementById("customSwitch1");
var label = document.getElementById("labelCheckBox");

boton.addEventListener('change', cambiarValorLabel);

function cambiarValorLabel() {

	if (boton.checked == true) {
		label.innerHTML = "Ventas online activadas"
	} else {
		label.innerHTML = "Ventas online desactivadas"
	}

}
