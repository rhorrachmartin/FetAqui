$(document).on("click", "#baja", function(e){
	
	bootbox.confirm({
	    message: "¿Está seguro de que desea darse de baja y eliminar todos sus datos de nuestra aplicación?",
	    buttons: {
	        confirm: {
	            label: 'Sí',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: function (result) {
	        console.log('Ha sido dado de baja');
	    }
	});

});
