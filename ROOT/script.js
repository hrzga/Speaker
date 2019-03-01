


$( document ).ready(function() {
	
    $("button").click(function(){
		$.ajax({
		  type: "POST",
		  url: "/abc",
		  data: 'audio=' + $(this).attr("name"),
		  success: function(data, status, xhr) {
			  alert(data);
		  },
		  error: function(xhr, status, errorThrown) {
			  alert("error: " + errorThrown);
		  }
		});
	});
});


 