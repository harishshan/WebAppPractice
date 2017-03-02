<html>
	<head>
		<title>AngularJS Application</title>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
		<script src="js/app.js"></script>
		<script src="js/controllers/student_controller.js"></script>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div ng-app = "mainApp" ng-init="currency=60;" ng-controller = "studentController">
			FirstName:{{student.firstName}} LastName:{{student.lastName}}	
			<form name = "subjectForm" novalidate>
				Search Subject: <input type="text" ng-model="subjectName" name="subjectName" required><br/>
				<span style = "color:red" ng-show = "subjectForm.subjectName.$dirty && subjectForm.subjectName.$invalid">
					<span ng-show = "subjectForm.subjectName.$error.required">Subject Name is required.</span>
					<!-- <span ng-show = "subjectForm.subjectName.$error.email">Invalid email address.</span> -->
				</span>
				<button ng-disabled = "subjectForm.subjectName.$dirty && subjectForm.subjectName.$invalid" ng-click="submit()">Submit</button>		
			</form>
			Search Keywords <span ng-bind="subjectName"></span> or  {{subjectName | uppercase}}  or {{subjectName | lowercase}} <br/>
			
			Subjects:
			<table>
				<tr>
					<th>Name</th>.
					<th>Marks</th>
				</tr>
			  	<tr ng-repeat = "subject in student.subjects | orderBy:'mark' | filter: subjectName">
					<td>{{subject.name + "(" + subject.code + ")"}}</td> 
					<td>{{subject.mark}}</td> 
				</tr>
				<tr>
					<th>Total</th>.
					<th>{{student.total()}}</th>
				</tr>
			</table>
			Fees : {{student.fees * currency | currency}}<br/>			
        	<input type = "checkbox" ng-model = "enableDisableButton">Disable Button <button ng-disabled = "enableDisableButton" ng-click = "clickCounter = clickCounter + 1">Click Me!</button>Total click: {{ clickCounter }} 
        	<input type = "checkbox" ng-model = "showHide1">Show Button	<button ng-show = "showHide1">Click Me!</button>
        	<input type = "checkbox" ng-model = "showHide2">Hide Button	<button ng-hide = "showHide2">Click Me!</button>
        	<div ng-include = "'include.html'"></div>
        	
        </div>
	</body>
</html>