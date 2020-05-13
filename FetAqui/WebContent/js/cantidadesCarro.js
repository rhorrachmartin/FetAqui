$('.minus-boton').on('click', function(e) {
	e.preventDefault();
	var $this = $(this);
	var $input = $(".unidades");
	var value = parseInt($input.val());

	if (value > 1) {
		value = value - 1;
	} else {
		value = 0;
	}

	$input.val(value);

});

$('.plus-boton').on('click', function(e) {

	e.preventDefault();
	var $this = $(this);
	var $input = $(".unidades");
	var value = parseInt($input.val());
	console.log(value);
	if (value < 100) {
		value = value + 1;
	} else {
		value = 100;
	}

	$input.val(value);
});
