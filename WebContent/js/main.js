$(document).ready(function() {
    $('#fform').bootstrapValidator({
    	container: '#messages',
    	 excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email address is required and cannot be empty'
                    },
                    emailAddress: {
                        message: 'The email address is not valid'
                    }
                }
            },         	
            name: {
                validators: {
                    notEmpty: {
                        message: 'The full name is required and cannot be empty'
                    }
                }
            },
            aoc: {
                validators: {
                    notEmpty: {
                        message: 'The Address Of Correspondence is required and cannot be empty'
                    }
                }
            },     
            contact: {
                validators: {
                    notEmpty: {
                        message: 'The Contact is required and cannot be empty'
                    },
                    between:{
                    	min:1000000000,
                    	max:9999999999,
                    	message: 'invalid phone number'
                    }
                }
            },
            phdstream:{
            	validators:{
                    notEmpty: {
                        message: 'Please select atleast one stream'
                    }            		
            	}
            },
            phdpref1: {
                validators: {
                    notEmpty: {
                        message: 'The Preference1 is required and cannot be empty'
                    }
                }
            },
            gender: {
                validators: {
                    notEmpty: {
                        message: 'The Gender is required and cannot be empty'
                    }
                }
            },
            category: {
                validators: {
                    notEmpty: {
                        message: 'The Category is required and cannot be empty'
                    }
                }
            } ,           
            pd: {
                validators: {
                    notEmpty: {
                        message: 'The Physically Disabled is required and cannot be empty'
                    }
                }
            },
            dob: {
                validators: {
                    notEmpty: {
                        message: 'DOB is required and cannot be empty'
                    }
                }
            },
            dp: {
                validators: {
                    notEmpty: {
                        message: 'Defence Personal Field is required and cannot be empty'
                    }
                }
            },   
            fname: {
                validators: {
                    notEmpty: {
                        message: 'Father\'s name is required and cannot be empty'
                    }
                }
            },             
            nat: {
                validators: {
                    notEmpty: {
                        message: 'Nationality is required and cannot be empty'
                    }
                }
            },             
            pmaddr: {
                validators: {
                    notEmpty: {
                        message: 'Permannt Address is required and cannot be empty'
                    }
                }
            },
            pincode: {
                validators: {
                    integer: {
                        message: 'Not a valid pincode'
                    }
                }
            },
            xboard: {
                validators: {
                    notEmpty: {
                        message: 'Xth Board is required and cannot be empty'
                    }
                }
            },
            xmarks: {
                validators: {
                    notEmpty: {
                        message: 'Xth Marks is required and cannot be empty'
                    }
                }
            },
            xyear: {
                validators: {
                    notEmpty: {
                        message: 'Year Of Passing Xth is required and cannot be empty'
                    }
                }
            },            
            xiiboard: {
                validators: {
                    notEmpty: {
                        message: 'XIIth Board is required and cannot be empty'
                    }
                }
            }, 
            xiimarks: {
                validators: {
                    notEmpty: {
                        message: 'XIIth Marks is required and cannot be empty'
                    }
                }
            },
            xiiyear: {
                validators: {
                    notEmpty: {
                        message: 'Year Of Passing Xth is required and cannot be empty'
                    }
                }
            },
            degree: {
                validators: {
                    notEmpty: {
                        message: 'Graduation Degree is required and cannot be empty'
                    }
                }
            },
            department: {
                validators: {
                    notEmpty: {
                        message: 'Graduation Department is required and cannot be empty'
                    }
                }
            },
            college: {
                validators: {
                    notEmpty: {
                        message: 'College name is required and cannot be empty'
                    }
                }
            },
            university: {
                validators: {
                    notEmpty: {
                        message: 'Name of University is required and cannot be empty'
                    }
                }
            },
            city: {
                validators: {
                    notEmpty: {
                        message: 'City is required and cannot be empty'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: 'State is required and cannot be empty'
                    }
                }
            },
            gradyear: {
                validators: {
                    notEmpty: {
                        message: 'GradYear is required and cannot be empty'
                    }
                }
            },
            gradcgpa1: {
                validators: {
                    between: {
                    	min:0,
                    	max:'gradcgpa2',
                        message: 'Invalid cgpa'
                    }
                }
            },
            ecepref1: {
                validators: {
                    notEmpty: {
                        message: 'ECE Pref1  is required and cannot be empty'
                    }
                }
            },
            ecepref2: {
                validators: {
                    notEmpty: {
                        message: 'ECE Pref2  is required and cannot be empty'
                    }
                }
            },
            ecepref3: {
                validators: {
                    notEmpty: {
                        message: 'ECE Pref3  is required and cannot be empty'
                    }
                }
            },            
            pgcgpa1: {
                validators: {
                    between: {
                    	min:0,
                    	max:'pgcgpa2',
                        message: 'Invalid cgpa'
                    }
                }
            }
        }       
    });
    
//    .on('status.field.bv', function(e, data) {
//        formIsValid = true;
//        $('.form-group',$(this)).each( function() {
//            formIsValid = formIsValid && $(this).hasClass('has-success');
//        });
//
//        if(formIsValid) {
//            $('.submit-button', $(this)).attr('disabled', false);
//        } else {
//            $('.submit-button', $(this)).attr('disabled', true);
//        }
//    });
//    .on('err.field.fv', function(e, data) {
//        if (data.fv.getSubmitButton()) {
//            data.fv.disableSubmitButtons(false);
//        }
//    })    
//    .on('success.field.bv', function(e, data) {
////    	e.preventDefault();
//        if (data.bv.getInvalidFields().length > 0) {
//            data.bv.disableSubmitButtons(true);
//        }
//        else
//        	data.bv.disableSubmitButtons(false);
//    });
//    $("#submitButton").click(function(event){
//    	event.preventDefault();
//    	var form = $("#fform");
//
//    	form.data('bootstrapValidator').validate();
//
//    	if(form.data('bootstrapValidator').isValid())
//    	{
//    		$.ajax({
//    			type: form.attr('method'),
//    			url: form.attr('action'),
//    			data: form.serialize(),
//    			success: function(result) {
//    				alert("Successfully Submitted");
//    			}
//    		});
//    	}
//    });    

});
 