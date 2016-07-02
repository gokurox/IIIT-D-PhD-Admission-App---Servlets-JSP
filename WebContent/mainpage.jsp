<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IIIT-Delhi PhD Admissions</title>
<link rel="shortcut icon" href="http://phdadmissions.iiitd.edu.in/static/images/favicon.ico" type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>  
  <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>
  <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"> </script>
<script src="main.js"></script>


</head>
<style>
/* tab color */
.nav-tabs>li>a {
  background-color: #333; 
  color:#fff;
}

/* active tab color */
.nav-tabs>li.active>a, .nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus {
  color: #fff;
  background-color: #3276b1;
  
}
/* hover tab color */
.nav-tabs>li>a:hover {
  background-color: #666;
}
.tab-content {
 background-color: #FFF;
}

.radio, .checkbox{
    margin-left: 10px;
}

.control-label{
	color: #666;
}
#div_id_u_scale, #div_id_p_scale {
    margin-left: 10px;
}
</style>

<body>
<nav class="navbar navbar-inverse">
<div class="container">
  <div class="container-fluid">
    <div class="navbar-header">
      <img src="http://www.iiitd.ac.in/sites/default/files/images/graphicidentity/logo/style3colorsmall.png" alt="IIITD Admissions" width="250" height="50">
    </div>
    <div style="float: right;">
      <ul class="nav navbar-nav">
        <li><a href="#">FAQ</a></li>
        <li><a href="#">Contact</a></li>
        <li><a href="#">Logout</a></li>   
      </ul>
    </div>
  </div>
</div>
</nav>

<div id="wrap" class="container content">
<div class="container">

  <div class="alert alert-info">
    Submit button appears on successful completion of all forms fields.
  </div>
  <ul class="nav nav-tabs" id="form-tabs">
  <li class="active"><a href="#tab1" data-toggle="tab">Personal Information</a></li>
  <li ><a href="#tab2" data-toggle="tab">Education Information</a></li>
  <li><a href="#tab3" data-toggle="tab">Payment Information</a></li>
  <li><a href="#tab4" data-toggle="tab">Feedback</a></li>
  <li><a href="#tab5" data-toggle="tab">Submit</a></li>
  <li><a href="#tab6" data-toggle="tab">Upload</a></li>	
</ul>
 <form class="form-horizontal" method ="post" action = "http://localhost:8080/PhD_Registration_Form_Servlet/MainPage2" id="fform2" enctype="multipart/form-data"></form>
 
 <form class="form-horizontal" method ="post" action = "http://localhost:8080/PhD_Registration_Form_Servlet/MainPage" id="fform" >
  <div class="tab-content" class="tab-content well">
    <div id="tab1" class="tab-pane fade in active">
    	<hr></hr>
		<div class="form-group">
    		<label class="control-label col-sm-2" for="email">Email*:</label>
    		<div class="col-sm-10">
      			<input type="email" class="form-control" name="email">
    		</div>
  		</div>
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="name">Name*:</label>
  			<div class="col-sm-10">
      			<input class="form-control" name="name" type="text">
    		</div>
    	</div>
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="enrollmentno">Enrollment No.:*:</label>
  			<div class="col-sm-10">
      			<input class="form-control" name="enrollmentno" type="text" disabled>
    		</div>
    	</div>
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="aoc">Address Of Correspondence:*</label>
  			<div class="col-sm-10">
      			<textarea class="form-control" name="aoc" cols='40' rows='5'></textarea>
    		</div>
    	</div>
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="contact">Contact:*</label>
  			<div class="col-sm-10">
      			<input type="text" class="form-control" name="contact">
    		</div>
    	</div>
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="phdstream">PHD Stream:*</label>
  			<div class="col-sm-10">
  			<div class="radio-group">
      			<div class="radio">
  					<label><input type="radio" name="phdstream" id="phdstream" value="Computer Science">Computer Science</label>
				</div>
				<div class="radio">
  					<label><input type="radio" name="phdstream" id="phdstream" value = "Electronics and Communication">Electronics and Communication</label>
				</div>
				<div class="radio">
  					<label><input type="radio" name="phdstream" id="phdstream" value = "Computational Biology">Computational Biology</label>
				</div>
			</div>
    		</div>
    	</div>
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="phdpref1">PhD Area Preference 1*</label>
  			<div class="col-sm-10">
      			<select class="form-control" name="phdpref1">
      			<option value="NULL"></option>
				<option value="1">Artificial Intelligence and Robotics - CSE</option>
				<option value="23" >Biophysics - CB</option>
				<option value="31" >Compilers - CSE</option>
				<option value="14" >Computer Architecture and Systems Design - ECE</option>
				<option value="4" >Computer Architecture and Systems Design - CSE</option>
				<option value="3" >Computer Graphics - CSE</option>
				<option value="2" >Computer Vision - CSE</option>
				<option value="13" >Controls and Robotics - ECE</option>
				<option value="25" >Digital and Analog VLSI Systems Design - ECE</option>
				<option value="15" >Electromagnetics - ECE</option>
				<option value="16" >Embedded and VLSI systems design - ECE</option>
				<option value="26" >Embedded Systems - ECE</option>
				<option value="27" >Fiber-Wireless Architectures - ECE</option>
				<option value="5" >Image Analysis and Biometrics - CSE</option>
				<option value="6" >Information Management and Data Engineering - CSE</option>
				<option value="32" >Machine Learning - CSE</option>
				<option value="17">Machine Learning - ECE</option>
				<option value="7" >Massively Parallel Systems - CSE</option>
				<option value="8" >Mobile Computing and Networking Applications - CSE</option>
				<option value="29" >OFDM based Optical Access Networks - ECE</option>
				<option value="30" >Optical Wireless Communication Systems - ECE</option>
				<option value="9" >Program Analysis - CSE</option>
				<option value="18" >RF and mixed signal electronics - ECE</option>
				<option value="10" >Security and Privacy - CSE</option>
				<option value="19" >Signal and Image Processing - ECE</option>
				<option value="33" >Signal and Image Processing - CSE</option>
				<option value="11" >Software Engineering - CSE</option>
				<option value="24" >Structural Biology - CB</option>
				<option value="22" >Systems Biology - CB</option>
				<option value="12" >Theoretical Computer Science - CSE</option>
				<option value="20" >Wireless Communication - ECE</option>
				<option value="21" >Wireless Networks - ECE</option>
				<option value="34" >Wireless Networks - CSE</option>
  				</select>
    		</div>
    	</div>
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="phdpref2">PhD Area Preference 2</label>
  			<div class="col-sm-10">
      			<select class="form-control" name="phdpref2">
      			<option value="NULL"></option>
				<option value="1">Artificial Intelligence and Robotics - CSE</option>
				<option value="23" >Biophysics - CB</option>
				<option value="31" >Compilers - CSE</option>
				<option value="14" >Computer Architecture and Systems Design - ECE</option>
				<option value="4" >Computer Architecture and Systems Design - CSE</option>
				<option value="3" >Computer Graphics - CSE</option>
				<option value="2" >Computer Vision - CSE</option>
				<option value="13" >Controls and Robotics - ECE</option>
				<option value="25" >Digital and Analog VLSI Systems Design - ECE</option>
				<option value="15" >Electromagnetics - ECE</option>
				<option value="16" >Embedded and VLSI systems design - ECE</option>
				<option value="26" >Embedded Systems - ECE</option>
				<option value="27" >Fiber-Wireless Architectures - ECE</option>
				<option value="5" >Image Analysis and Biometrics - CSE</option>
				<option value="6" >Information Management and Data Engineering - CSE</option>
				<option value="32" >Machine Learning - CSE</option>
				<option value="17">Machine Learning - ECE</option>
				<option value="7" >Massively Parallel Systems - CSE</option>
				<option value="8" >Mobile Computing and Networking Applications - CSE</option>
				<option value="29" >OFDM based Optical Access Networks - ECE</option>
				<option value="30" >Optical Wireless Communication Systems - ECE</option>
				<option value="9" >Program Analysis - CSE</option>
				<option value="18" >RF and mixed signal electronics - ECE</option>
				<option value="10" >Security and Privacy - CSE</option>
				<option value="19" >Signal and Image Processing - ECE</option>
				<option value="33" >Signal and Image Processing - CSE</option>
				<option value="11" >Software Engineering - CSE</option>
				<option value="24" >Structural Biology - CB</option>
				<option value="22" >Systems Biology - CB</option>
				<option value="12" >Theoretical Computer Science - CSE</option>
				<option value="20" >Wireless Communication - ECE</option>
				<option value="21" >Wireless Networks - ECE</option>
				<option value="34" >Wireless Networks - CSE</option>      			
  				</select>
    		</div>
    	</div>
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="phdpref3">PhD Area Preference 3</label>
  			<div class="col-sm-10">
      			<select class="form-control" name="phdpref3">
      			<option value="NULL"></option>
				<option value="1">Artificial Intelligence and Robotics - CSE</option>
				<option value="23" >Biophysics - CB</option>
				<option value="31" >Compilers - CSE</option>
				<option value="14" >Computer Architecture and Systems Design - ECE</option>
				<option value="4" >Computer Architecture and Systems Design - CSE</option>
				<option value="3" >Computer Graphics - CSE</option>
				<option value="2" >Computer Vision - CSE</option>
				<option value="13" >Controls and Robotics - ECE</option>
				<option value="25" >Digital and Analog VLSI Systems Design - ECE</option>
				<option value="15" >Electromagnetics - ECE</option>
				<option value="16" >Embedded and VLSI systems design - ECE</option>
				<option value="26" >Embedded Systems - ECE</option>
				<option value="27" >Fiber-Wireless Architectures - ECE</option>
				<option value="5" >Image Analysis and Biometrics - CSE</option>
				<option value="6" >Information Management and Data Engineering - CSE</option>
				<option value="32" >Machine Learning - CSE</option>
				<option value="17">Machine Learning - ECE</option>
				<option value="7" >Massively Parallel Systems - CSE</option>
				<option value="8" >Mobile Computing and Networking Applications - CSE</option>
				<option value="29" >OFDM based Optical Access Networks - ECE</option>
				<option value="30" >Optical Wireless Communication Systems - ECE</option>
				<option value="9" >Program Analysis - CSE</option>
				<option value="18" >RF and mixed signal electronics - ECE</option>
				<option value="10" >Security and Privacy - CSE</option>
				<option value="19" >Signal and Image Processing - ECE</option>
				<option value="33" >Signal and Image Processing - CSE</option>
				<option value="11" >Software Engineering - CSE</option>
				<option value="24" >Structural Biology - CB</option>
				<option value="22" >Systems Biology - CB</option>
				<option value="12" >Theoretical Computer Science - CSE</option>
				<option value="20" >Wireless Communication - ECE</option>
				<option value="21" >Wireless Networks - ECE</option>
				<option value="34" >Wireless Networks - CSE</option>      			
  				</select>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="gender">Gender:*</label>
  			<div class="col-sm-10">
      			<div class="radio">
  					<label><input type="radio" name="gender" value="Male">Male</label>
				</div>
				<div class="radio">
  					<label><input type="radio" name="gender" value="Female">Female</label>
				</div>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="category">Category:*</label>
  			<div class="col-sm-10">
      			<div class="radio">
  					<label><input type="radio" name="category" value="General">General</label>
				</div>
				<div class="radio">
  				<label class="radio-inline"><input type="radio" name="category" value = "SC">SC</label>
				<label class="radio-inline"><input type="radio" name="category" value="ST">ST</label>
				<label class="radio-inline"><input type="radio" name="category" value="OBC">OBC</label>
				</div>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="pd">Physically Disabled:*</label>
  			<div class="col-sm-10">
      			<div class="radio">
  					<label><input type="radio" name="pd" value="Yes">Yes</label>
				</div>
				<div class="radio">
  					<label><input type="radio" name="pd" value="No">No</label>
				</div>
    		</div>
    	</div>
    	<div class="form-group">
			<label class="control-label col-sm-2" for="date">Date of Birth*:</label>
			<div class="col-sm-10">
				<input type="date" name="dob">
			</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="dp">Children/War Widows of Defence Personnel killed/Disabled in Action*</label>
  			<div class="col-sm-10">
      			<div class="radio">
  					<label><input type="radio" name="dp" value="Yes" >Yes</label>
				</div>
				<div class="radio">
  					<label><input type="radio" name="dp" value="No">No</label>
				</div>
    		</div>
    	</div> 
  		<div class="form-group">
  			<label class="control-label col-sm-2" for="fname">Father's Name*:</label>
  			<div class="col-sm-10">
      			<input class="form-control" name="fname" type="text">
    		</div>
    	</div>
    	  		<div class="form-group">
  			<label class="control-label col-sm-2" for="nat">Nationality</label>
  			<div class="col-sm-10">
      			<select class="form-control" name="nat">
				<option value="NULL"></option><option value="afghan">Afghan</option><option value="albanian">Albanian</option><option value="algerian">Algerian</option><option value="american">American</option><option value="andorran">Andorran</option><option value="angolan">Angolan</option><option value="antiguans">Antiguans</option><option value="argentinean">Argentinean</option><option value="armenian">Armenian</option><option value="australian">Australian</option><option value="austrian">Austrian</option><option value="azerbaijani">Azerbaijani</option><option value="bahamian">Bahamian</option><option value="bahraini">Bahraini</option><option value="bangladeshi">Bangladeshi</option><option value="barbadian">Barbadian</option><option value="barbudans">Barbudans</option><option value="batswana">Batswana</option><option value="belarusian">Belarusian</option><option value="belgian">Belgian</option><option value="belizean">Belizean</option><option value="beninese">Beninese</option><option value="bhutanese">Bhutanese</option><option value="bolivian">Bolivian</option><option value="bosnian">Bosnian</option><option value="brazilian">Brazilian</option><option value="british">British</option><option value="bruneian">Bruneian</option><option value="bulgarian">Bulgarian</option><option value="burkinabe">Burkinabe</option><option value="burmese">Burmese</option><option value="burundian">Burundian</option><option value="cambodian">Cambodian</option><option value="cameroonian">Cameroonian</option><option value="canadian">Canadian</option><option value="cape verdean">Cape Verdean</option><option value="central african">Central African</option><option value="chadian">Chadian</option><option value="chilean">Chilean</option><option value="chinese">Chinese</option><option value="colombian">Colombian</option><option value="comoran">Comoran</option><option value="congolese">Congolese</option><option value="costa rican">Costa Rican</option><option value="croatian">Croatian</option><option value="cuban">Cuban</option><option value="cypriot">Cypriot</option><option value="czech">Czech</option><option value="danish">Danish</option><option value="djibouti">Djibouti</option><option value="dominican">Dominican</option><option value="dutch">Dutch</option><option value="east timorese">East Timorese</option><option value="ecuadorean">Ecuadorean</option><option value="egyptian">Egyptian</option><option value="emirian">Emirian</option><option value="equatorial guinean">Equatorial Guinean</option><option value="eritrean">Eritrean</option><option value="estonian">Estonian</option><option value="ethiopian">Ethiopian</option><option value="fijian">Fijian</option><option value="filipino">Filipino</option><option value="finnish">Finnish</option><option value="french">French</option><option value="gabonese">Gabonese</option><option value="gambian">Gambian</option><option value="georgian">Georgian</option><option value="german">German</option><option value="ghanaian">Ghanaian</option><option value="greek">Greek</option><option value="grenadian">Grenadian</option><option value="guatemalan">Guatemalan</option><option value="guinea-bissauan">Guinea-Bissauan</option><option value="guinean">Guinean</option><option value="guyanese">Guyanese</option><option value="haitian">Haitian</option><option value="herzegovinian">Herzegovinian</option><option value="honduran">Honduran</option><option value="hungarian">Hungarian</option><option value="icelander">Icelander</option><option value="indian">Indian</option><option value="indonesian">Indonesian</option><option value="iranian">Iranian</option><option value="iraqi">Iraqi</option><option value="irish">Irish</option><option value="israeli">Israeli</option><option value="italian">Italian</option><option value="ivorian">Ivorian</option><option value="jamaican">Jamaican</option><option value="japanese">Japanese</option><option value="jordanian">Jordanian</option><option value="kazakhstani">Kazakhstani</option><option value="kenyan">Kenyan</option><option value="kittian and nevisian">Kittian and Nevisian</option><option value="kuwaiti">Kuwaiti</option><option value="kyrgyz">Kyrgyz</option><option value="laotian">Laotian</option><option value="latvian">Latvian</option><option value="lebanese">Lebanese</option><option value="liberian">Liberian</option><option value="libyan">Libyan</option><option value="liechtensteiner">Liechtensteiner</option><option value="lithuanian">Lithuanian</option><option value="luxembourger">Luxembourger</option><option value="macedonian">Macedonian</option><option value="malagasy">Malagasy</option><option value="malawian">Malawian</option><option value="malaysian">Malaysian</option><option value="maldivan">Maldivan</option><option value="malian">Malian</option><option value="maltese">Maltese</option><option value="marshallese">Marshallese</option><option value="mauritanian">Mauritanian</option><option value="mauritian">Mauritian</option><option value="mexican">Mexican</option><option value="micronesian">Micronesian</option><option value="moldovan">Moldovan</option><option value="monacan">Monacan</option><option value="mongolian">Mongolian</option><option value="moroccan">Moroccan</option><option value="mosotho">Mosotho</option><option value="motswana">Motswana</option><option value="mozambican">Mozambican</option><option value="namibian">Namibian</option><option value="nauruan">Nauruan</option><option value="nepalese">Nepalese</option><option value="new zealander">New Zealander</option><option value="ni-vanuatu">Ni-Vanuatu</option><option value="nicaraguan">Nicaraguan</option><option value="nigerien">Nigerien</option><option value="north korean">North Korean</option><option value="northern irish">Northern Irish</option><option value="norwegian">Norwegian</option><option value="omani">Omani</option><option value="pakistani">Pakistani</option><option value="palauan">Palauan</option><option value="panamanian">Panamanian</option><option value="papua new guinean">Papua New Guinean</option><option value="paraguayan">Paraguayan</option><option value="peruvian">Peruvian</option><option value="polish">Polish</option><option value="portuguese">Portuguese</option><option value="qatari">Qatari</option><option value="romanian">Romanian</option><option value="russian">Russian</option><option value="rwandan">Rwandan</option><option value="saint lucian">Saint Lucian</option><option value="salvadoran">Salvadoran</option><option value="samoan">Samoan</option><option value="san marinese">San Marinese</option><option value="sao tomean">Sao Tomean</option><option value="saudi">Saudi</option><option value="scottish">Scottish</option><option value="senegalese">Senegalese</option><option value="serbian">Serbian</option><option value="seychellois">Seychellois</option><option value="sierra leonean">Sierra Leonean</option><option value="singaporean">Singaporean</option><option value="slovakian">Slovakian</option><option value="slovenian">Slovenian</option><option value="solomon islander">Solomon Islander</option><option value="somali">Somali</option><option value="south african">South African</option><option value="south korean">South Korean</option><option value="spanish">Spanish</option><option value="sri lankan">Sri Lankan</option><option value="sudanese">Sudanese</option><option value="surinamer">Surinamer</option><option value="swazi">Swazi</option><option value="swedish">Swedish</option><option value="swiss">Swiss</option><option value="syrian">Syrian</option><option value="taiwanese">Taiwanese</option><option value="tajik">Tajik</option><option value="tanzanian">Tanzanian</option><option value="thai">Thai</option><option value="togolese">Togolese</option><option value="tongan">Tongan</option><option value="trinidadian or tobagonian">Trinidadian or Tobagonian</option><option value="tunisian">Tunisian</option><option value="turkish">Turkish</option><option value="tuvaluan">Tuvaluan</option><option value="ugandan">Ugandan</option><option value="ukrainian">Ukrainian</option><option value="uruguayan">Uruguayan</option><option value="uzbekistani">Uzbekistani</option><option value="venezuelan">Venezuelan</option><option value="vietnamese">Vietnamese</option><option value="welsh">Welsh</option><option value="yemenite">Yemenite</option><option value="zambian">Zambian</option><option value="zimbabwean">Zimbabwean</option>
  				</select>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="pmaddr">Permanent Address:*</label>
  			<div class="col-sm-10">
      			<textarea class="form-control" name="pmaddr" cols='40' rows='5'></textarea>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="pincode">PinCode:</label>
  			<div class="col-sm-10">
      			<input class="form-control" name="pincode" type="text">
    		</div>
    	</div>		
	</div>
	
	
    <div id="tab2" class="tab-pane fade">
    	<h4>Schooling Information</h4>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="xboard">Xth Board*:</label>
  			<div class="col-sm-5">
  				<select class="form-control" name="xboard">
  									<option value="NULL"></option>
  									<option value="CBSE">
										CBSE
									</option>
									<option value="ICSE">
										ICSE
									</option>
									<option value="State Board">
										State Board
									</option>
									<option value="Andhra Pradesh Board of Intermediate Education">
										Andhra Pradesh Board of Intermediate Education
									</option>
									<option value="Andhra Pradesh Board of Secondary Education">
										Andhra Pradesh Board of Secondary Education
									</option>
									<option value="Board of Higher Secondary Education, Delhi">
										Board of Higher Secondary Education, Delhi
									</option>
									<option value="Assam Board of Secondary Education">
										Assam Board of Secondary Education
									</option>
									<option value="Bihar School Examination Board">
										Bihar School Examination Board
									</option>
									<option value="Board of Youth Education India">
										Board of Youth Education India
									</option>
									<option value="Board of School Education, Haryana">
										Board of School Education, Haryana
									</option>
									<option value="Board of Secondary Education, Madhya Pradesh">
										Board of Secondary Education, Madhya Pradesh
									</option>
									<option value="Board of Secondary Education Madhya Bharat Gwalior">
										Board of Secondary Education Madhya Bharat Gwalior
									</option>
									<option value="Board of Secondary Education, Rajasthan">
										Board of Secondary Education, Rajasthan
									</option>
									<option value="Chhattisgarh Board of Secondary Education">
										Chhattisgarh Board of Secondary Education
									</option>
									<option value="Central Board of Secondary Education">
										Central Board of Secondary Education
									</option>
									<option value="Central Board Of Patna, Bihar">
										Central Board Of Patna, Bihar
									</option>
									<option value="Central Board Of Education Ajmer New Delhi">
										Central Board Of Education Ajmer New Delhi
									</option>
									<option value="Goa Board of Secondary & Higher Secondary Education">
										Goa Board of Secondary & Higher Secondary Education
									</option>
									<option value="Gujarat Secondary Education Board">
										Gujarat Secondary Education Board
									</option>
									<option value="Himachal Pradesh Board of School Education">
										Himachal Pradesh Board of School Education
									</option>
									<option value="Indian Board of Science Education(not recognized)">
										Indian Board of Science Education(not recognized)
									</option>
									<option value="Indian Board of School Education">
										Indian Board of School Education
									</option>
									<option value="Indian Board of Computer Education">
										Indian Board of Computer Education
									</option>
									<option value="J&K State Board of School Education">
										J&K State Board of School Education
									</option>
									<option value="Jharkhand Academic Council">
										Jharkhand Academic Council
									</option>
									<option value="Karnataka Board of the Pre-University Education">
										Karnataka Board of the Pre-University Education
									</option>
									<option value="Karnataka Secondary Education Examination Board">
										Karnataka Secondary Education Examination Board
									</option>
									<option value="Kerala Board of Public Examinations">
										Kerala Board of Public Examinations
									</option>
									<option value="Maharashtra State Board of Secondary and Higher Secondary Education">
										Maharashtra State Board of Secondary and Higher Secondary Education
									</option>
									<option value="Manipur Board of Secondary Education">
										Manipur Board of Secondary Education
									</option>
									<option value="Manipur Council of Higher Secondary Education">
										Manipur Council of Higher Secondary Education
									</option>
									<option value="Meghalaya Board of School Education">
										Meghalaya Board of School Education
									</option>
									<option value="Mizoram Board of School Education">
										Mizoram Board of School Education
									</option>
									<option value="North East National Board Of School Education (NENBSE)">
										North East National Board Of School Education (NENBSE)
									</option>
									<option value="Northwest Accreditation Commission">
										Northwest Accreditation Commission
									</option>
									<option value="Nagaland Board of School Education">
										Nagaland Board of School Education
									</option>
									<option value="National Institute of Open Schooling">
										National Institute of Open Schooling
									</option>
									<option value="Orissa Board of Secondary Education">
										Orissa Board of Secondary Education
									</option>
									<option value="Orissa Council of Higher Secondary Education">
										Orissa Council of Higher Secondary Education
									</option>
									<option value="Punjab School Education Board">
										Punjab School Education Board
									</option>
									<option value="Rajasthan Board of Secondary Education">
										Rajasthan Board of Secondary Education
									</option>
									<option value="Tamil Nadu Board of Higher Secondary Education">
										Tamil Nadu Board of Higher Secondary Education
									</option>
									<option value="Tamil Nadu Board of Secondary Education">
										Tamil Nadu Board of Secondary Education
									</option>
									<option value="Tamilnadu Council for Open and Distance Learning">
										Tamilnadu Council for Open and Distance Learning
									</option>
									<option value="Tripura Board of Secondary Education">
										Tripura Board of Secondary Education
									</option>
									<option value="Telangana Board of Intermediate Education">
										Telangana Board of Intermediate Education
									</option>
									<option value="Telangana Board of Secondary Education">
										Telangana Board of Secondary Education
									</option>
									<option value="Uttar Pradesh Board of High School and Intermediate Education">
										Uttar Pradesh Board of High School and Intermediate Education
									</option>
									<option value="Sampurnanand Sanskrit Vishwavidyalaya Varanasi Uttar Pradesh">
										Sampurnanand Sanskrit Vishwavidyalaya Varanasi Uttar Pradesh
									</option>
									<option value="Uttarakhand Board of School Education">
										Uttarakhand Board of School Education
									</option>
									<option value="West Bengal Board of Secondary Education">
										West Bengal Board of Secondary Education
									</option>
									<option value="West Bengal Council of Higher Secondary Education">
										West Bengal Council of Higher Secondary Education
									</option>
									<option value="West Bengal State Council of Vocational Education and Training">
										West Bengal State Council of Vocational Education and Training
									</option>
									<option value="Board of Secondary Education Kant Shahjahanpur Uttar Pradesh">
										Board of Secondary Education Kant Shahjahanpur Uttar Pradesh
									</option>
									<option value="The West Bengal Council of Rabindra Open Schooling">
										The West Bengal Council of Rabindra Open Schooling
									</option>
  				</select>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="xmarks">Xth Marks(%)*:</label>
  			<div class="col-sm-5">
      			<input class="numberinput form-control"  max="100.0" min="0.0" name="xmarks" step="any" type="number">
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="xyear">Year Of Passing Xth:*</label>
  			<div class="col-sm-5">
      			<select class="form-control" name="xyear">
				<option value="2013">2013</option><option value="2012">2012</option><option value="2011">2011</option><option value="2010">2010</option><option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option><option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option><option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option><option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option><option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option><option value="1990">1990</option><option value="1989">1989</option><option value="1988">1988</option><option value="1987">1987</option><option value="1986">1986</option><option value="1985">1985</option><option value="1984">1984</option><option value="1983">1983</option><option value="1982">1982</option><option value="1981">1981</option><option value="1980">1980</option><option value="1979">1979</option><option value="1978">1978</option><option value="1977">1977</option><option value="1976">1976</option><option value="1975">1975</option><option value="1974">1974</option><option value="1973">1973</option><option value="1972">1972</option><option value="1971">1971</option><option value="1970">1970</option><option value="1969">1969</option><option value="1968">1968</option><option value="1967">1967</option><option value="1966">1966</option><option value="1965">1965</option><option value="1964">1964</option><option value="1963">1963</option><option value="1962">1962</option><option value="1961">1961</option><option value="1960">1960</option><option value="1959">1959</option><option value="1958">1958</option><option value="1957">1957</option><option value="1956">1956</option><option value="1955">1955</option><option value="1954">1954</option><option value="1953">1953</option><option value="1952">1952</option><option value="1951">1951</option>      			
  				</select>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="xiiboard">XIIth Board*:</label>
  			<div class="col-sm-5">
  				<select class="form-control" name="xiiboard">
  									<option value=""></option> 
  									<option value="CBSE">
										CBSE
									</option>
									<option value="ICSE">
										ICSE
									</option>
									<option value="State Board">
										State Board
									</option>
									<option value="Andhra Pradesh Board of Intermediate Education">
										Andhra Pradesh Board of Intermediate Education
									</option>
									<option value="Andhra Pradesh Board of Secondary Education">
										Andhra Pradesh Board of Secondary Education
									</option>
									<option value="Board of Higher Secondary Education, Delhi">
										Board of Higher Secondary Education, Delhi
									</option>
									<option value="Assam Board of Secondary Education">
										Assam Board of Secondary Education
									</option>
									<option value="Bihar School Examination Board">
										Bihar School Examination Board
									</option>
									<option value="Board of Youth Education India">
										Board of Youth Education India
									</option>
									<option value="Board of School Education, Haryana">
										Board of School Education, Haryana
									</option>
									<option value="Board of Secondary Education, Madhya Pradesh">
										Board of Secondary Education, Madhya Pradesh
									</option>
									<option value="Board of Secondary Education Madhya Bharat Gwalior">
										Board of Secondary Education Madhya Bharat Gwalior
									</option>
									<option value="Board of Secondary Education, Rajasthan">
										Board of Secondary Education, Rajasthan
									</option>
									<option value="Chhattisgarh Board of Secondary Education">
										Chhattisgarh Board of Secondary Education
									</option>
									<option value="Central Board of Secondary Education">
										Central Board of Secondary Education
									</option>
									<option value="Central Board Of Patna, Bihar">
										Central Board Of Patna, Bihar
									</option>
									<option value="Central Board Of Education Ajmer New Delhi">
										Central Board Of Education Ajmer New Delhi
									</option>
									<option value="Goa Board of Secondary & Higher Secondary Education">
										Goa Board of Secondary & Higher Secondary Education
									</option>
									<option value="Gujarat Secondary Education Board">
										Gujarat Secondary Education Board
									</option>
									<option value="Himachal Pradesh Board of School Education">
										Himachal Pradesh Board of School Education
									</option>
									<option value="Indian Board of Science Education(not recognized)">
										Indian Board of Science Education(not recognized)
									</option>
									<option value="Indian Board of School Education">
										Indian Board of School Education
									</option>
									<option value="Indian Board of Computer Education">
										Indian Board of Computer Education
									</option>
									<option value="J&K State Board of School Education">
										J&K State Board of School Education
									</option>
									<option value="Jharkhand Academic Council">
										Jharkhand Academic Council
									</option>
									<option value="Karnataka Board of the Pre-University Education">
										Karnataka Board of the Pre-University Education
									</option>
									<option value="Karnataka Secondary Education Examination Board">
										Karnataka Secondary Education Examination Board
									</option>
									<option value="Kerala Board of Public Examinations">
										Kerala Board of Public Examinations
									</option>
									<option value="Maharashtra State Board of Secondary and Higher Secondary Education">
										Maharashtra State Board of Secondary and Higher Secondary Education
									</option>
									<option value="Manipur Board of Secondary Education">
										Manipur Board of Secondary Education
									</option>
									<option value="Manipur Council of Higher Secondary Education">
										Manipur Council of Higher Secondary Education
									</option>
									<option value="Meghalaya Board of School Education">
										Meghalaya Board of School Education
									</option>
									<option value="Mizoram Board of School Education">
										Mizoram Board of School Education
									</option>
									<option value="North East National Board Of School Education (NENBSE)">
										North East National Board Of School Education (NENBSE)
									</option>
									<option value="Northwest Accreditation Commission">
										Northwest Accreditation Commission
									</option>
									<option value="Nagaland Board of School Education">
										Nagaland Board of School Education
									</option>
									<option value="National Institute of Open Schooling">
										National Institute of Open Schooling
									</option>
									<option value="Orissa Board of Secondary Education">
										Orissa Board of Secondary Education
									</option>
									<option value="Orissa Council of Higher Secondary Education">
										Orissa Council of Higher Secondary Education
									</option>
									<option value="Punjab School Education Board">
										Punjab School Education Board
									</option>
									<option value="Rajasthan Board of Secondary Education">
										Rajasthan Board of Secondary Education
									</option>
									<option value="Tamil Nadu Board of Higher Secondary Education">
										Tamil Nadu Board of Higher Secondary Education
									</option>
									<option value="Tamil Nadu Board of Secondary Education">
										Tamil Nadu Board of Secondary Education
									</option>
									<option value="Tamilnadu Council for Open and Distance Learning">
										Tamilnadu Council for Open and Distance Learning
									</option>
									<option value="Tripura Board of Secondary Education">
										Tripura Board of Secondary Education
									</option>
									<option value="Telangana Board of Intermediate Education">
										Telangana Board of Intermediate Education
									</option>
									<option value="Telangana Board of Secondary Education">
										Telangana Board of Secondary Education
									</option>
									<option value="Uttar Pradesh Board of High School and Intermediate Education">
										Uttar Pradesh Board of High School and Intermediate Education
									</option>
									<option value="Sampurnanand Sanskrit Vishwavidyalaya Varanasi Uttar Pradesh">
										Sampurnanand Sanskrit Vishwavidyalaya Varanasi Uttar Pradesh
									</option>
									<option value="Uttarakhand Board of School Education">
										Uttarakhand Board of School Education
									</option>
									<option value="West Bengal Board of Secondary Education">
										West Bengal Board of Secondary Education
									</option>
									<option value="West Bengal Council of Higher Secondary Education">
										West Bengal Council of Higher Secondary Education
									</option>
									<option value="West Bengal State Council of Vocational Education and Training">
										West Bengal State Council of Vocational Education and Training
									</option>
									<option value="Board of Secondary Education Kant Shahjahanpur Uttar Pradesh">
										Board of Secondary Education Kant Shahjahanpur Uttar Pradesh
									</option>
									<option value="The West Bengal Council of Rabindra Open Schooling">
										The West Bengal Council of Rabindra Open Schooling
									</option>
  				</select>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="xiimarks">XIIth Marks(%)*:</label>
  			<div class="col-sm-5">
      			<input class="numberinput form-control"  max="100.0" min="0.0" name="xiimarks" step="any" type="number">
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="xiiyear">Year Of Passing XIIth:*</label>
  			<div class="col-sm-5">
      			<select class="form-control" name="xiiyear">
      			<option value="2013">2013</option><option value="2012">2012</option><option value="2011">2011</option><option value="2010">2010</option><option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option><option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option><option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option><option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option><option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option><option value="1990">1990</option><option value="1989">1989</option><option value="1988">1988</option><option value="1987">1987</option><option value="1986">1986</option><option value="1985">1985</option><option value="1984">1984</option><option value="1983">1983</option><option value="1982">1982</option><option value="1981">1981</option><option value="1980">1980</option><option value="1979">1979</option><option value="1978">1978</option><option value="1977">1977</option><option value="1976">1976</option><option value="1975">1975</option><option value="1974">1974</option><option value="1973">1973</option><option value="1972">1972</option><option value="1971">1971</option><option value="1970">1970</option><option value="1969">1969</option><option value="1968">1968</option><option value="1967">1967</option><option value="1966">1966</option><option value="1965">1965</option><option value="1964">1964</option><option value="1963">1963</option><option value="1962">1962</option><option value="1961">1961</option><option value="1960">1960</option><option value="1959">1959</option><option value="1958">1958</option><option value="1957">1957</option><option value="1956">1956</option><option value="1955">1955</option><option value="1954">1954</option><option value="1953">1953</option><option value="1952">1952</option><option value="1951">1951</option>      			
  				</select>
    		</div>
    	</div>
    	<h4>Graduation Information</h4>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="degree">Degree*</label>
  			<div class="col-sm-5">
      			<input class="form-control" name="degree" type="text">
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="department">Department/Discipline*</label>
  			<div class="col-sm-5">
      			<input class="form-control" name="department" type="text">
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="college">Name of College*</label>
  			<div class="col-sm-5">
      			<input class="form-control" name="college" type="text">
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="university">Name of University*</label>
  			<div class="col-sm-5">
      			<input class="form-control" name="university" type="text">
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="city">City*</label>
  			<div class="col-sm-5">
      			<input class="form-control" name="city" type="text">
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="state">State*</label>
  			<div class="col-sm-5">
      			<select class="form-control" name="state">
      								<option value=""></option>
									<option value="Andaman and Nicobar Islands">Andaman and Nicobar Islands</option>
									<option value="Andhra Pradesh">Andhra Pradesh</option>
									<option value="Arunachal Pradesh">Arunachal Pradesh</option>
									<option value="Assam">Assam</option>
									<option value="Bihar">Bihar</option>
									<option value="Chandigarh">Chandigarh</option>
									<option value="Chhatisgarh">Chhatisgarh</option>
									<option value="Dadra and Nagar Haveli">Dadra and Nagar Haveli</option>
									<option value="Daman and Diu">Daman and Diu</option>
									<option value="Delhi">Delhi</option>
									<option value="Goa">Goa</option>
									<option value="Gujarat">Gujarat</option>
									<option value="Haryana">Haryana</option>
									<option value="Himachal Pradesh">Himachal Pradesh</option>
									<option value="Jammu and Kashmir">Jammu and Kashmir</option>
									<option value="Jharkhand">Jharkhand</option>
									<option value="Karnataka">Karnataka</option>
									<option value="Kerala">Kerala</option>
									<option value="Lakshadweep">Lakshadweep</option>
									<option value="Madhya Pradesh">Madhya Pradesh</option>
									<option value="Maharashtra">Maharashtra</option>
									<option value="Manipur">Manipur</option>
									<option value="Meghalaya">Meghalaya</option>
									<option value="Mizoram">Mizoram</option>
									<option value="Nagaland">Nagaland</option>
									<option value="Orissa">Orissa</option>
									<option value="Pondicherry">Pondicherry</option>
									<option value="Punjab">Punjab</option>
									<option value="Rajasthan">Rajasthan</option>
									<option value="Sikkim">Sikkim</option>
									<option value="Tamil Nadu">Tamil Nadu</option>
									<option value="Tripura">Tripura</option>
									<option value="Uttaranchal">Uttaranchal</option>
									<option value="Uttar Pradesh">Uttar Pradesh</option>
									<option value="West Bengal">West Bengal</option>
									<option value="Other">Other</option>      			
  				</select>
    		</div>
    	</div>
    	<div class="form-group">
  			<label class="control-label col-sm-2" for="gradyear">Year Of Graduation*</label>
  			<div class="col-sm-5">
      			<select class="form-control" name="gradyear">
      			<option value="2015">2015</option><option value="2014">2014</option><option value="2013">2013</option><option value="2012">2012</option><option value="2011">2011</option><option value="2010">2010</option><option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option><option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option><option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option><option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option><option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option><option value="1990">1990</option><option value="1989">1989</option><option value="1988">1988</option><option value="1987">1987</option><option value="1986">1986</option><option value="1985">1985</option><option value="1984">1984</option><option value="1983">1983</option><option value="1982">1982</option><option value="1981">1981</option><option value="1980">1980</option><option value="1979">1979</option><option value="1978">1978</option><option value="1977">1977</option><option value="1976">1976</option><option value="1975">1975</option><option value="1974">1974</option><option value="1973">1973</option><option value="1972">1972</option><option value="1971">1971</option><option value="1970">1970</option><option value="1969">1969</option><option value="1968">1968</option><option value="1967">1967</option><option value="1966">1966</option><option value="1965">1965</option><option value="1964">1964</option><option value="1963">1963</option><option value="1962">1962</option><option value="1961">1961</option><option value="1960">1960</option><option value="1959">1959</option><option value="1958">1958</option><option value="1957">1957</option><option value="1956">1956</option><option value="1955">1955</option><option value="1954">1954</option><option value="1953">1953</option><option value="1952">1952</option><option value="1951">1951</option>      			
  				</select>
    		</div>
    	</div>
                                
      	<div class="form-group">
  			<label class="control-label col-sm-2" >CGPA or Marks?</label>
  			<div class="col-sm-10">
      			<div class="radio" name= "comon" >
  					<label><input type="radio" name="gradmarks" value="CGPA">CGPA</label>
				</div>
			</div>
			<span class="col-sm-2"></span>
	    	<div class="col-sm-2">
      			<input class="form-control" value = "0" name="gradcgpa1" type="text">
    		</div>
    		<label class="col-sm-1">/</label>
	    	<div class="col-sm-2">
      			<select class="form-control" name="gradcgpa2">
      			<option>4</option>
      			<option>10</option>      			
      			</select>
    		</div>
	    	<div class="col-sm-10 col-sm-offset-2">
      			<div class="radio" name = "comon">
  					<label><input type="radio" name="gradmarks" value="Marks">Marks(%)</label>
				</div>
    		</div>
    		<div class="controls col-sm-2 col-sm-offset-2">
    			<input class="numberinput form-control" value = "0" max="100.0" min="0.0" type="number" name="gradpercent"> 
    		</div>
		</div>
		<br>
		
		<div class="form-group">		
			<h4><input type="checkbox" data-toggle="collapse" data-target="#demo1" name="ch1" onclick="if (this.checked){ document.getElementById('ecepref1').removeAttribute('disabled');document.getElementById('ecepref2').removeAttribute('disabled');document.getElementById('ecepref3').removeAttribute('disabled');}else{document.getElementById('ecepref3').setAttribute('disabled', true);document.getElementById('ecepref1').setAttribute('disabled', true);document.getElementById('ecepref2').setAttribute('disabled', true);}">Are You Applying For ECE PHD</h4>
			<div id="demo1" class="collapse">
				<h2>ECE PhD Subject Preference</h2><hr></hr>
				You are required to select 4 subjects if you are an under-graduate otherwise you need to fill 3.
				<p></p>
			<div class="form-group">
  			<label class="control-label col-sm-2" for="ecepref1">Preference 1*</label>
  			<div class="col-sm-4">
      			<select class="form-control" id="ecepref1" name="ecepref1" disabled="disabled">
				<option value="NULL"></option><option value="Advanced Signal Processing">Advanced Signal Processing</option><option value="Statistical Signal Processing">Statistical Signal Processing</option><option value="Digital VLSI Design">Digital VLSI Design</option><option value="Analog CMOS design">Analog CMOS design</option><option value="Digital Communications">Digital Communications</option><option value="Communication Networks">Communication Networks</option><option value="Linear systems">Linear systems</option><option value="Introduction to Robotics">Introduction to Robotics</option><option value="RF Circuit design">RF Circuit design</option><option value="Antennas and Propagation">Antennas and Propagation</option><option value="Embedded Systems">Embedded Systems</option>      			
  				</select>
    		</div>
    		</div>
  			<div class="form-group">
  				<label class="control-label col-sm-2" for="ecepref2" >Preference 2*</label>
  				<div class="col-sm-4">
      				<select class="form-control" id="ecepref2" name="ecepref2" disabled="disabled">
				<option value="NULL"></option><option value="Advanced Signal Processing">Advanced Signal Processing</option><option value="Statistical Signal Processing">Statistical Signal Processing</option><option value="Digital VLSI Design">Digital VLSI Design</option><option value="Analog CMOS design">Analog CMOS design</option><option value="Digital Communications">Digital Communications</option><option value="Communication Networks">Communication Networks</option><option value="Linear systems">Linear systems</option><option value="Introduction to Robotics">Introduction to Robotics</option><option value="RF Circuit design">RF Circuit design</option><option value="Antennas and Propagation">Antennas and Propagation</option><option value="Embedded Systems">Embedded Systems</option>      			
  					</select>
    			</div>
    		</div>
  			<div class="form-group">
  				<label class="control-label col-sm-2" for="ecepref3">Preference 3*</label>
  				<div class="col-sm-4">
      				<select class="form-control" name="ecepref3" id="ecepref3" disabled="disabled">
				<option value="NULL"></option><option value="Advanced Signal Processing">Advanced Signal Processing</option><option value="Statistical Signal Processing">Statistical Signal Processing</option><option value="Digital VLSI Design">Digital VLSI Design</option><option value="Analog CMOS design">Analog CMOS design</option><option value="Digital Communications">Digital Communications</option><option value="Communication Networks">Communication Networks</option><option value="Linear systems">Linear systems</option><option value="Introduction to Robotics">Introduction to Robotics</option><option value="RF Circuit design">RF Circuit design</option><option value="Antennas and Propagation">Antennas and Propagation</option><option value="Embedded Systems">Embedded Systems</option>      			
  					</select>
    			</div>
    		</div>	
    		<div class="form-group">
  				<label class="control-label col-sm-2" for="ecepref4">Preference 4*</label>
  				<div class="col-sm-4">
      				<select class="form-control" name="ecepref4">
				<option value="NULL"></option><option value="Advanced Signal Processing">Advanced Signal Processing</option><option value="Statistical Signal Processing">Statistical Signal Processing</option><option value="Digital VLSI Design">Digital VLSI Design</option><option value="Analog CMOS design">Analog CMOS design</option><option value="Digital Communications">Digital Communications</option><option value="Communication Networks">Communication Networks</option><option value="Linear systems">Linear systems</option><option value="Introduction to Robotics">Introduction to Robotics</option><option value="RF Circuit design">RF Circuit design</option><option value="Antennas and Propagation">Antennas and Propagation</option><option value="Embedded Systems">Embedded Systems</option>      			
  					</select>
    			</div>
    		</div>	
			</div>
		</div>	
		
		<div class="form-group">		
			<h4><input type="checkbox" data-toggle="collapse" data-target="#demo2" name= "ch2" >Have You Completed Your Post Graduation</h4>
			<div id="demo2" class="collapse">
				<h2>Post Graduation Information</h2><hr></hr>
				    <div class="form-group">
  						<label class="control-label col-sm-2" for="pgcollegename">Name Of College</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="pgcollegename" type="text">
    					</div>
    				</div>
    			    <div class="form-group">
  						<label class="control-label col-sm-2" for="pgcity">City</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="pgcity" type="text">
    					</div>
    				</div>
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="pgstate">State</label>
  						<div class="col-sm-4">
      						<select class="form-control" name="pgstate">
      								<option value=""></option>
									<option value="Andaman and Nicobar Islands">Andaman and Nicobar Islands</option>
									<option value="Andhra Pradesh">Andhra Pradesh</option>
									<option value="Arunachal Pradesh">Arunachal Pradesh</option>
									<option value="Assam">Assam</option>
									<option value="Bihar">Bihar</option>
									<option value="Chandigarh">Chandigarh</option>
									<option value="Chhatisgarh">Chhatisgarh</option>
									<option value="Dadra and Nagar Haveli">Dadra and Nagar Haveli</option>
									<option value="Daman and Diu">Daman and Diu</option>
									<option value="Delhi">Delhi</option>
									<option value="Goa">Goa</option>
									<option value="Gujarat">Gujarat</option>
									<option value="Haryana">Haryana</option>
									<option value="Himachal Pradesh">Himachal Pradesh</option>
									<option value="Jammu and Kashmir">Jammu and Kashmir</option>
									<option value="Jharkhand">Jharkhand</option>
									<option value="Karnataka">Karnataka</option>
									<option value="Kerala">Kerala</option>
									<option value="Lakshadweep">Lakshadweep</option>
									<option value="Madhya Pradesh">Madhya Pradesh</option>
									<option value="Maharashtra">Maharashtra</option>
									<option value="Manipur">Manipur</option>
									<option value="Meghalaya">Meghalaya</option>
									<option value="Mizoram">Mizoram</option>
									<option value="Nagaland">Nagaland</option>
									<option value="Orissa">Orissa</option>
									<option value="Pondicherry">Pondicherry</option>
									<option value="Punjab">Punjab</option>
									<option value="Rajasthan">Rajasthan</option>
									<option value="Sikkim">Sikkim</option>
									<option value="Tamil Nadu">Tamil Nadu</option>
									<option value="Tripura">Tripura</option>
									<option value="Uttaranchal">Uttaranchal</option>
									<option value="Uttar Pradesh">Uttar Pradesh</option>
									<option value="West Bengal">West Bengal</option>
									<option value="Other">Other</option>      			

  							</select>
    					</div>
    				</div>	
    			    <div class="form-group">
  						<label class="control-label col-sm-2" for="pgdepartment">Department/Discipline</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="pgdepartment" type="text">
    					</div>
    				</div>
    			    <div class="form-group">
  						<label class="control-label col-sm-2" for="pgdegree">Degree</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="pgdegree" type="text">
    					</div>
    				</div>
    			    <div class="form-group">
  						<label class="control-label col-sm-2" for="thesis">Thesis</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="thesis" type="text">
    					</div>
    				</div> 
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="pgyear">Year of Post Graduation*</label>
  						<div class="col-sm-4">
      						<select class="form-control" name="pgyear">
      						<option value="2015">2015</option><option value="2014">2014</option><option value="2013">2013</option><option value="2012">2012</option><option value="2011">2011</option><option value="2010">2010</option><option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option><option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option><option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option><option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option><option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option><option value="1990">1990</option><option value="1989">1989</option><option value="1988">1988</option><option value="1987">1987</option><option value="1986">1986</option><option value="1985">1985</option><option value="1984">1984</option><option value="1983">1983</option><option value="1982">1982</option><option value="1981">1981</option><option value="1980">1980</option><option value="1979">1979</option><option value="1978">1978</option><option value="1977">1977</option><option value="1976">1976</option><option value="1975">1975</option><option value="1974">1974</option><option value="1973">1973</option><option value="1972">1972</option><option value="1971">1971</option><option value="1970">1970</option><option value="1969">1969</option><option value="1968">1968</option><option value="1967">1967</option><option value="1966">1966</option><option value="1965">1965</option><option value="1964">1964</option><option value="1963">1963</option><option value="1962">1962</option><option value="1961">1961</option><option value="1960">1960</option><option value="1959">1959</option><option value="1958">1958</option><option value="1957">1957</option><option value="1956">1956</option><option value="1955">1955</option><option value="1954">1954</option><option value="1953">1953</option><option value="1952">1952</option><option value="1951">1951</option>      			
  							</select>
    					</div>
    				</div>
    					    				
      	<div class="form-group">
  			<label class="control-label col-sm-2">CGPA or Marks?</label>
  			<div class="col-sm-10">
      			<div class="radio">
  					<label><input type="radio" name="pgmarks" value="CGPA">CGPA</label>
				</div>
			</div>
			<span class="col-sm-2"></span>
	    	<div class="col-sm-2">
      			<input class="form-control" value = "0" name="pgcgpa1" type="text">
    		</div>
    		<label class="col-sm-1">/</label>
	    	<div class="col-sm-2">
      			<select class="form-control" name="pgcgpa2">
      			<option>4</option>
      			<option>10</option>      			
      			</select>
    		</div>
	    	<div class="col-sm-10 col-sm-offset-2">
      			<div class="radio">
  					<label><input type="radio" name="pgmarks" value="Marks">Marks(%)</label>
				</div>
    		</div>
    		<div class="controls col-sm-2 col-sm-offset-2">
    			<input class="numberinput form-control" max="100.0" min="0.0" name="pgpercent" value = "0" type="number" > 
    		</div>
		</div>    				
    	</div>
		</div>
		

		<div class="form-group">		
			<h4><input type="checkbox" data-toggle="collapse" data-target="#demo3" name= "ch3" >Other Academic Degrees?</h4>
			<div id="demo3" class="collapse">
				<h2>Other Academic Degrees?</h2><hr></hr>
				    <div class="form-group">
  						<label class="control-label col-sm-2" for="examname">Exam Name</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="examname" type="text">
    					</div>
    				</div>
    			    <div class="form-group">
  						<label class="control-label col-sm-2" for="subject">Subject</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="subject" type="text">
    					</div>
    				</div>
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="otheryear">Year</label>
  						<div class="col-sm-4">
      						<select class="form-control" name="otheryear">
			      			<option value="2015">2015</option><option value="2014">2014</option><option value="2013">2013</option><option value="2012">2012</option><option value="2011">2011</option><option value="2010">2010</option><option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option><option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option><option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option><option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option><option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option><option value="1990">1990</option><option value="1989">1989</option><option value="1988">1988</option><option value="1987">1987</option><option value="1986">1986</option><option value="1985">1985</option><option value="1984">1984</option><option value="1983">1983</option><option value="1982">1982</option><option value="1981">1981</option><option value="1980">1980</option><option value="1979">1979</option><option value="1978">1978</option><option value="1977">1977</option><option value="1976">1976</option><option value="1975">1975</option><option value="1974">1974</option><option value="1973">1973</option><option value="1972">1972</option><option value="1971">1971</option><option value="1970">1970</option><option value="1969">1969</option><option value="1968">1968</option><option value="1967">1967</option><option value="1966">1966</option><option value="1965">1965</option><option value="1964">1964</option><option value="1963">1963</option><option value="1962">1962</option><option value="1961">1961</option><option value="1960">1960</option><option value="1959">1959</option><option value="1958">1958</option><option value="1957">1957</option><option value="1956">1956</option><option value="1955">1955</option><option value="1954">1954</option><option value="1953">1953</option><option value="1952">1952</option><option value="1951">1951</option>      			
  							</select>
    					</div>
    				</div>
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="otherscore">Score</label>
    					<div class="controls col-sm-4">
    						<input class="numberinput form-control"  max="100.0" min="0.0" name="otherscore" type="number"> 
    					</div>
    				</div>
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="otherrrank">Rank</label>
    					<div class="controls col-sm-4">
    						<input class="numberinput form-control"  max="100.0" min="0.0" name="otherrank" type="number">  
    					</div>
    				</div>    					
    		</div>
    	</div>
    	
    	
		<div class="form-group">		
			<h4><input type="checkbox" data-toggle="collapse" data-target="#demo4" name= "ch4" >Taken Gate Exam?</h4>
			<div id="demo4" class="collapse">
				<h2>Gate</h2><hr></hr>
				    <div class="form-group">
  						<label class="control-label col-sm-2" for="area">Area</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="area" type="text">
    					</div>
    				</div>
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="gateyear">Year of Graduation*</label>
  						<div class="col-sm-4">
      						<select class="form-control" name="gateyear">
			      			<option value="2015">2015</option><option value="2014">2014</option><option value="2013">2013</option><option value="2012">2012</option><option value="2011">2011</option><option value="2010">2010</option><option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option><option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option><option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option><option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option><option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option><option value="1990">1990</option><option value="1989">1989</option><option value="1988">1988</option><option value="1987">1987</option><option value="1986">1986</option><option value="1985">1985</option><option value="1984">1984</option><option value="1983">1983</option><option value="1982">1982</option><option value="1981">1981</option><option value="1980">1980</option><option value="1979">1979</option><option value="1978">1978</option><option value="1977">1977</option><option value="1976">1976</option><option value="1975">1975</option><option value="1974">1974</option><option value="1973">1973</option><option value="1972">1972</option><option value="1971">1971</option><option value="1970">1970</option><option value="1969">1969</option><option value="1968">1968</option><option value="1967">1967</option><option value="1966">1966</option><option value="1965">1965</option><option value="1964">1964</option><option value="1963">1963</option><option value="1962">1962</option><option value="1961">1961</option><option value="1960">1960</option><option value="1959">1959</option><option value="1958">1958</option><option value="1957">1957</option><option value="1956">1956</option><option value="1955">1955</option><option value="1954">1954</option><option value="1953">1953</option><option value="1952">1952</option><option value="1951">1951</option>      			
  							</select>
    					</div>
    				</div>    				
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="gatemarks">Marks(Out of 100)</label>
    					<div class="controls col-sm-4">
							<input class="numberinput form-control"  max="100.0" min="0.0" name="gatemarks" type="number">     					
						</div>
    				</div>
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="gatescore">Score</label>
    					<div class="controls col-sm-4">
							<input class="numberinput form-control"  max="100.0" min="0.0" name="gatescore" type="number"> 
    					</div>
    				</div>    				
    				<div class="form-group">
  						<label class="control-label col-sm-2" for="gaterank">Rank</label>
    					<div class="controls col-sm-4">
							<input class="numberinput form-control"  max="100.0" min="0.0" name="gaterank" type="number"> 
    					</div>
    				</div>      				
    	</div>
		</div>    	
		
    </div>			

    <div id="tab5" class="tab-pane fade">
    	<br>
    	<p>Please complete and save all the fields before you click the submit button. Once you have submitted you will not be able to edit any field.</p>
		<button type="submit" name="submitButton" class="btn btn-default">Submit</button>
    		<div class="form-group">
        	<div class="col-md-9">
        	<div id="messages"></div>
        	</div>
        	</div>
    </div>
    <div id="tab6" class="tab-pane fade">
    	<h4>Achievements, CV and Statement of Purpose</h4><hr>
				    <div class="form-group">
  						<label class="control-label col-sm-2" for="ach">Achievements (Other information like ranks, medals etc.)</label>
  						<div class="col-sm-5">
      						<input class="form-control" name="ach" type="text">
    					</div>
    				</div>    	
					<div class="form-group">
						<label class="control-label col-lg-2 requiredField">CV/Resume*</label>
						<div class="controls col-lg-4">
						<input type="file" name="CV" size="60" form ="fform2">					
						</div>
					</div>	   	
 					<div class="form-group">
						<label class="control-label col-lg-2 requiredField">SOP*</label>
						<div class="controls col-lg-4">
						<input type="file" name="SOP" size="60" form ="fform2">					
						</div>
					</div>	   
	</div>
    </div> 
    </form>   
    </div>
    </div>
</body>
</html>
