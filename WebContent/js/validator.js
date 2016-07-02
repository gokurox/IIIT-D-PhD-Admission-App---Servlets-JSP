$(document).ready(function() {
    $('#filterform').bootstrapValidator({
    	container: '#errors',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            PI_eMail: {
                validators: {
                	notEmpty: {
                        message: 'The full name is required and cannot be empty'
                    }
                    emailAddress: {
                        message: 'The email address is not valid'
                    }
                }
            },
            EI_XBoardPerc: {
                validators: {
                    between: {
                        min:0,
                        max:100,
                        message: 'Invalid Percentage'
                   }
                }
            }
        }
    });
});