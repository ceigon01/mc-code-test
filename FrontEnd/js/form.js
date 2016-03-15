//Create your form validation here.//
$("#main").submit(function() {
    var form  = $("#main");
    var isValid = false;
    var emailIcon = $("#emailAvailable");
    var msgContainerEle = $(".msg-container");
    var email = $("#email");
    var isValid = false;
    emailIcon.hide();

    form.find('input').each(function(){
        var field = $(this);
        var validate = (field.attr('validate'))?Boolean(field.attr('validate')):false;
        if(validate && field.val().length == 0){
            field.addClass('invalid');
            msgContainerEle.html("Oops! The following fields are required and left blank. ");
            msgContainerEle.show();
        }else{
            field.removeClass('invalid');
            msgContainerEle.hide();
            isValid = true;
        }
    });
    //run email check after validation has finished
    if(isValid == true){
    	email.addClass('invalid');
		$.ajax({
	        crossDomain: true,
	        type:"GET",
	        async:false,
	        dataType:"jsonp",
	        contentType:'application/x-www-form-urlencoded; charset=utf-8',
	        url: "http://codetest.socrative.com/v1/api/email-check/"+email.val(),
	        statusCode: {
	            200:function(){
	                emailIcon.show();
	                isValid = true;
	                email.removeClass('invalid');
	            }
	        }
	    });
    }
    return false;
});

$("#email").blur(function(event){
    event.preventDefault();
    var field = $(this);
});
