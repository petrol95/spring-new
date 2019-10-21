$(document).ready(function() {
    // удаление по кнопке
    $('.removeBtn').on('click', function(event) {
		var studentId = $(this).attr('entryIndex');
		$.get("/students/remove/" + studentId);
		location.reload();
	});

	$('#consoleTestBtn').on('click', function(event) {
		console.log($('#myInput').val());
	});
	
	$('.table .editBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');		
		$.get(href, function(book, status) {
			$('.myForm #id').val(book.id);
			$('.myForm #title').val(book.title);
			$('.myForm #author').val(book.author);
		});		
		$('.myForm #editModal').modal();
	});
	
	$('.addNewBookBtn').on('click', function(event) {
		event.preventDefault();		
		var href = $(this).attr('href');		
		$.get(href, function(book, status) {
			$('.myForm #id').val(book.id);
			$('.myForm #title').val(book.title);
			$('.myForm #author').val(book.author);
		});	
		$('.myForm #editModal').modal();
	});


});